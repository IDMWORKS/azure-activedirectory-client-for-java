package com.identityforge.aad.adcl4j;

import com.identityforge.aad.adcl4j.io.ExcludeTransformer;
import com.identityforge.aad.adcl4j.model.entity.*;
import com.microsoft.aad.adal4j.AuthenticationContext;
import com.microsoft.aad.adal4j.AuthenticationResult;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.AbstractHttpMessage;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by nwoolls on 3/29/15.
 * AzureAdRepositoryImpl talks to the Azure Active Directory Graph API
 * via simple REST calls. It should know nothing of LDAP or being an
 * LDAP adapter. Ideally this class and namespace could / should be
 * moved to a reusable library once mature
 */
public class AzureAdRepositoryImpl implements AzureAdRepository {

    private final String clientId;
    private final String username;
    private final String password;
    private final String tenantDomain;

    private AuthenticationResult authenticationResult = null;

    public AzureAdRepositoryImpl(
            String clientId,
            String username,
            String password,
            String tenantDomain) {

        this.clientId = clientId;
        this.username = username;
        this.password = password;
        this.tenantDomain = tenantDomain;

    }

    private void ensureAuthenticated() throws AuthenticationException {

        // we do not have a valid token
        Boolean valid_token = false;

        // have we previously authenticated?
        if (authenticationResult != null) {
            Date expiresDate = authenticationResult.getExpiresOnDate();
            Date now = new Date();
            // has our token expired?
            if (expiresDate.before(now)) {

                // get a new token using the stored refresh token
                authenticationResult = getAccessTokenFromRefreshToken();
                valid_token = true;

            } else
                valid_token = true;
        }

        // we do not have a valid token - authenticate
        if (!valid_token) {
            authenticationResult = getAccessTokenFromUserCredentials(
                    this.clientId, this.username, this.password);
        }
    }

    private AuthenticationResult getAccessTokenFromRefreshToken() throws AuthenticationException {

        AuthenticationContext context = null;
        AuthenticationResult result = null;
        ExecutorService service = null;
        try {
            try {
                service = Executors.newFixedThreadPool(1);
                context = new AuthenticationContext("https://login.windows.net/common", false, service);
                Future<AuthenticationResult> future = context.acquireTokenByRefreshToken(authenticationResult.getRefreshToken(),
                        clientId, null);
                result = future.get();
            } catch (InterruptedException |
                    MalformedURLException |
                    ExecutionException e) {
                e.printStackTrace();
                throw new AuthenticationException(e.getLocalizedMessage());
            }
        } finally {
            service.shutdown();
        }

        if (result == null) {
            throw new AuthenticationException("authentication result was null");
        }
        return result;
    }

    public Boolean validateCredentials(String clientId, String username, String password) {

        Boolean result = false;

        try {
            AuthenticationResult response = getAccessTokenFromUserCredentials(clientId, username, password);
            result = !StringUtils.isEmpty(response.getAccessToken());
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        return result;
    }

    private AuthenticationResult getAccessTokenFromUserCredentials(
            String clientId, String username, String password)
            throws AuthenticationException {
        AuthenticationContext context = null;
        AuthenticationResult result = null;
        ExecutorService service = null;
        try {
            try {
                service = Executors.newFixedThreadPool(1);
                context = new AuthenticationContext("https://login.windows.net/common", false, service);
                Future<AuthenticationResult> future = context.acquireToken(
                        "https://graph.windows.net", clientId, username, password, null);
                result = future.get();
            } catch (InterruptedException |
                    MalformedURLException |
                    ExecutionException e) {
                e.printStackTrace();
                throw new AuthenticationException(e.getLocalizedMessage());
            }
        } finally {
            service.shutdown();
        }

        if (result == null) {
            throw new AuthenticationException("authentication result was null");
        }
        return result;
    }

    public Collection<Application> getAllApplications()
            throws IOException, URISyntaxException, AuthenticationException {
        return getAllEntries(Application.class, Application.PLURAL_NAME);
    }

    public Application getApplication(String oid)
            throws IOException, URISyntaxException, AuthenticationException {
        return getEntry(Application.class, Application.PLURAL_NAME, oid);
    }

    /* TODO - Fails with HTTP 400 "Property identifierUris value is required but is empty or missing."
              Docs indicate identifierUris is optional
              Existing entries contain an empty array e.g. "identifierUris":[] */
    public String createApplication(Application entry)
            throws IOException, URISyntaxException, AuthenticationException {

        Application result = createEntry(Application.class, Application.PLURAL_NAME, entry);
        return result.getObjectId();
    }

    public void updateApplication(String oid, Application entry)
            throws IOException, URISyntaxException, AuthenticationException {
        updateEntry(Application.PLURAL_NAME, oid, entry);
    }

    public void deleteApplication(String oid)
            throws IOException, URISyntaxException, AuthenticationException {
        deleteEntry(Application.PLURAL_NAME, oid);
    }

    public Collection<Contact> getAllContacts()
            throws IOException, URISyntaxException, AuthenticationException {
        return getAllEntries(Contact.class, Contact.PLURAL_NAME);
    }

    public Contact getContact(String oid)
            throws IOException, URISyntaxException, AuthenticationException {
        return getEntry(Contact.class, Contact.PLURAL_NAME, oid);
    }

    public void updateContact(String oid, Contact entry)
            throws IOException, URISyntaxException, AuthenticationException {
        updateEntry(Contact.PLURAL_NAME, oid, entry);
    }

    public void deleteContact(String oid)
            throws IOException, URISyntaxException, AuthenticationException {
        deleteEntry(Contact.PLURAL_NAME, oid);
    }

    public Collection<Device> getAllDevices()
            throws IOException, URISyntaxException, AuthenticationException {
        return getAllEntries(Device.class, Device.PLURAL_NAME);
    }

    public Device getDevice(String oid)
            throws IOException, URISyntaxException, AuthenticationException {
        return getEntry(Device.class, Device.PLURAL_NAME, oid);
    }

    public String createDevice(Device entry)
            throws IOException, URISyntaxException, AuthenticationException {

        Device result = createEntry(Device.class, Device.PLURAL_NAME, entry);
        return result.getObjectId();
    }

    public void updateDevice(String oid, Device entry)
            throws IOException, URISyntaxException, AuthenticationException {
        updateEntry(Device.PLURAL_NAME, oid, entry);
    }

    public void deleteDevice(String oid)
            throws IOException, URISyntaxException, AuthenticationException {
        deleteEntry(Device.PLURAL_NAME, oid);
    }

    public Collection<DirectoryRole> getAllDirectoryRoles()
            throws IOException, URISyntaxException, AuthenticationException {
        return getAllEntries(DirectoryRole.class, DirectoryRole.PLURAL_NAME);
    }

    public DirectoryRole getDirectoryRole(String oid)
            throws IOException, URISyntaxException, AuthenticationException {
        return getEntry(DirectoryRole.class, DirectoryRole.PLURAL_NAME, oid);
    }

    public Collection<Group> getAllGroups()
            throws IOException, URISyntaxException, AuthenticationException {
        return getAllEntries(Group.class, Group.PLURAL_NAME);
    }

    public Group getGroup(String oid)
            throws IOException, URISyntaxException, AuthenticationException {
        return getEntry(Group.class, Group.PLURAL_NAME, oid);
    }

    public String createGroup(Group entry)
            throws IOException, URISyntaxException, AuthenticationException {

        Group result = createEntry(Group.class, Group.PLURAL_NAME, entry);
        return result.getObjectId();
    }

    public void updateGroup(String oid, Group entry)
            throws IOException, URISyntaxException, AuthenticationException {
        updateEntry(Group.PLURAL_NAME, oid, entry);
    }

    public void deleteGroup(String oid)
            throws IOException, URISyntaxException, AuthenticationException {
        deleteEntry(Group.PLURAL_NAME, oid);
    }

    public Collection<OAuth2PermissionGrant> getAllOAuth2PermissionGrants()
            throws IOException, URISyntaxException, AuthenticationException {
        return getAllEntries(OAuth2PermissionGrant.class, OAuth2PermissionGrant.PLURAL_NAME);
    }

    public OAuth2PermissionGrant getOAuth2PermissionGrant(String oid)
            throws IOException, URISyntaxException, AuthenticationException {
        return getEntry(OAuth2PermissionGrant.class, OAuth2PermissionGrant.PLURAL_NAME, oid);
    }

    public String createOAuth2PermissionGrant(OAuth2PermissionGrant entry)
            throws IOException, URISyntaxException, AuthenticationException {

        OAuth2PermissionGrant result = createEntry(OAuth2PermissionGrant.class, OAuth2PermissionGrant.PLURAL_NAME, entry);
        return result.getObjectId();
    }

    public void updateOAuth2PermissionGrant(String oid, OAuth2PermissionGrant entry)
            throws IOException, URISyntaxException, AuthenticationException {
        updateEntry(OAuth2PermissionGrant.PLURAL_NAME, oid, entry);
    }

    public void deleteOAuth2PermissionGrant(String oid)
            throws IOException, URISyntaxException, AuthenticationException {
        deleteEntry(OAuth2PermissionGrant.PLURAL_NAME, oid);
    }

    public Collection<ServicePrincipal> getAllServicePrincipals()
            throws IOException, URISyntaxException, AuthenticationException {
        return getAllEntries(ServicePrincipal.class, ServicePrincipal.PLURAL_NAME);
    }

    public ServicePrincipal getServicePrincipal(String oid)
            throws IOException, URISyntaxException, AuthenticationException {
        return getEntry(ServicePrincipal.class, ServicePrincipal.PLURAL_NAME, oid);
    }

    public String createServicePrincipal(ServicePrincipal entry)
            throws IOException, URISyntaxException, AuthenticationException {

        ServicePrincipal result = createEntry(ServicePrincipal.class, ServicePrincipal.PLURAL_NAME, entry);
        return result.getObjectId();
    }

    public void updateServicePrincipal(String oid, ServicePrincipal entry)
            throws IOException, URISyntaxException, AuthenticationException {
        updateEntry(ServicePrincipal.PLURAL_NAME, oid, entry);
    }

    public void deleteServicePrincipal(String oid)
            throws IOException, URISyntaxException, AuthenticationException {
        deleteEntry(ServicePrincipal.PLURAL_NAME, oid);
    }

    public Collection<SubscribedSku> getAllSubscribedSkus()
            throws IOException, URISyntaxException, AuthenticationException {
        return getAllEntries(SubscribedSku.class, SubscribedSku.PLURAL_NAME);
    }

    public SubscribedSku getSubscribedSku(String oid)
            throws IOException, URISyntaxException, AuthenticationException {
        return getEntry(SubscribedSku.class, SubscribedSku.PLURAL_NAME, oid);
    }

    public Collection<TenantDetail> getAllTenantDetails()
            throws IOException, URISyntaxException, AuthenticationException {
        return getAllEntries(TenantDetail.class, TenantDetail.PLURAL_NAME);
    }

    public TenantDetail getTenantDetail(String oid)
            throws IOException, URISyntaxException, AuthenticationException {
        return getEntry(TenantDetail.class, TenantDetail.PLURAL_NAME, oid);
    }

    public void updateTenantDetail(String oid, TenantDetail entry)
            throws IOException, URISyntaxException, AuthenticationException {
        updateEntry(TenantDetail.PLURAL_NAME, oid, entry);
    }

    public Collection<User> getAllUsers()
            throws IOException, URISyntaxException, AuthenticationException {
        return getAllEntries(User.class, User.PLURAL_NAME);
    }

    public User getUser(String oid)
            throws IOException, URISyntaxException, AuthenticationException {
        return getEntry(User.class, User.PLURAL_NAME, oid);
    }

    public String createUser(User entry)
            throws IOException, URISyntaxException, AuthenticationException {

        User result = createEntry(User.class, User.PLURAL_NAME, entry);
        return result.getObjectId();
    }

    public void updateUser(String oid, User entry)
            throws IOException, URISyntaxException, AuthenticationException {
        updateEntry(User.PLURAL_NAME, oid, entry);
    }

    public void deleteUser(String oid)
            throws IOException, URISyntaxException, AuthenticationException {
        deleteEntry(User.PLURAL_NAME, oid);
    }

    // 2015-09-28T11:34:46.6036707
    private static final DateTransformer DATE_TRANSFORMER = new DateTransformer("yyyy-MM-dd'T'HH:mm:ss");

    private <T> T createEntry(Class<T> clazz, String noun, Object entry)
            throws IOException, URISyntaxException, AuthenticationException {

        String payload = new JSONSerializer()
                // exclude class info
                .exclude("*.class")
                // exclude properties with NULL values
                .transform(new ExcludeTransformer(), void.class)
                // set date format
                .transform(DATE_TRANSFORMER, Date.class)
                .deepSerialize(entry);

        String response = postRestEntity(noun, payload);

        T result = new JSONDeserializer<T>()
                .use(null, clazz)
                // set date format
                .use(Date.class, DATE_TRANSFORMER)
                .deserialize(response);

        return result;
    }

    private void deleteEntry(String noun, String oid)
            throws IOException, URISyntaxException, AuthenticationException {
        deleteRestEntity(noun, oid);
    }

    private void updateEntry(String noun, String oid, Object entry)
            throws IOException, URISyntaxException, AuthenticationException {

        String payload = new JSONSerializer()
                // exclude class info
                .exclude("*.class")
                // exclude properties with NULL values
                .transform(new ExcludeTransformer(), void.class)
                // set date format
                .transform(DATE_TRANSFORMER, Date.class)
                .serialize(entry);

        patchRestEntity(noun, oid, payload);
    }

    private <T> T getEntry(Class<T> clazz, String noun, String oid)
            throws IOException, URISyntaxException, AuthenticationException {

        String response = getRestEntity(noun, oid);

//        if (ServerLogger.isVerbose)
//            ServerLogger.info(response);

        T result = new JSONDeserializer<T>()
                .use(null, clazz)
                // set date format
                .use(Date.class, DATE_TRANSFORMER)
                .deserialize(response);

        return result;
    }

    private <T> Collection<T> getAllEntries(Class<T> clazz, String noun)
            throws IOException, URISyntaxException, AuthenticationException {

        String response = getRestEntity(noun, null);

//        if (ServerLogger.isVerbose)
//            ServerLogger.info(response);

        Collection<T> results = new JSONDeserializer<Map<String, Collection<T>>>()
                .use("values.values", clazz)
                // set date format
                .use(Date.class, DATE_TRANSFORMER)
                .deserialize(response)
                .get("value");

        return results;
    }

    private static final String GRAPH_API_URI = "https://graph.windows.net";

    private String getRestEntity(String noun, String oid)
            throws IOException, URISyntaxException, AuthenticationException {

        ensureAuthenticated();

        String result;
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {

            HttpGet httpGet = new HttpGet(buildRestUri(noun, oid));
            setHeaders(httpGet);

            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    result = new BasicResponseHandler().handleResponse(response);
                } else {
                    throw new HTTPException(response.getStatusLine().getStatusCode());
                }
            }
        }
        return result;
    }

    private String deleteRestEntity(String noun, String oid)
            throws IOException, URISyntaxException, AuthenticationException {

        ensureAuthenticated();

        String result;
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {

            HttpDelete httpDelete = new HttpDelete(buildRestUri(noun, oid));
            setHeaders(httpDelete);

            try (CloseableHttpResponse response = httpClient.execute(httpDelete)) {
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_NO_CONTENT) {
                    result = new BasicResponseHandler().handleResponse(response);
                } else {
                    throw new HTTPException(response.getStatusLine().getStatusCode());
                }
            }
        }
        return result;
    }

    private String postRestEntity(String noun, String payload)
            throws IOException, URISyntaxException, AuthenticationException{

        ensureAuthenticated();

        String result;
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {

            HttpPost httpPost = new HttpPost(buildRestUri(noun, null));
            setHeaders(httpPost);
            httpPost.setEntity(new StringEntity(payload));

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {
                    result = new BasicResponseHandler().handleResponse(response);
                } else {
                    throw new HTTPException(response.getStatusLine().getStatusCode());
                }
            }
        }
        return result;
    }

    // TODO - trying to clear a value (e.g. specify "" for country) returns HTTP 400
    // The JSON seems fine - {"country":""}
    private void patchRestEntity(String noun, String oid, String payload)
            throws IOException, URISyntaxException, AuthenticationException {

        ensureAuthenticated();

        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {

            HttpPatch httpPatch = new HttpPatch(buildRestUri(noun, oid));
            setHeaders(httpPatch);
            httpPatch.setEntity(new StringEntity(payload));

            try (CloseableHttpResponse response = httpClient.execute(httpPatch)) {
                if (response.getStatusLine().getStatusCode() != HttpStatus.SC_NO_CONTENT) {
                    throw new HTTPException(response.getStatusLine().getStatusCode());
                }
            }
        }
    }

    private URI buildRestUri(String noun, String oid) throws URISyntaxException {

        URIBuilder uriBuilder = new URIBuilder(GRAPH_API_URI);
        uriBuilder.setPath(String.format("/%s/%s/%s",
                this.tenantDomain,
                // not lower-case, e.g. DirectoryRoles => directoryRoles
                StringUtils.uncapitalize(noun),
                oid == null ? "" : oid));
        // oldest API version that returns immutableId
        uriBuilder.setParameter("api-version", "1.5");

        return uriBuilder.build();
    }

    private void setHeaders(AbstractHttpMessage httpMessage) {

        String bearerToken = authenticationResult.getAccessToken();
        httpMessage.setHeader("Authorization", String.format("Bearer %s", bearerToken));
        httpMessage.setHeader("Content-type", "application/json");
    }
}

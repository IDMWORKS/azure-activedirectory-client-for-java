package com.identityforge.aad.adcl4j;

import com.identityforge.aad.adcl4j.model.ObjectLink;
import com.identityforge.aad.adcl4j.model.entity.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;

/**
 * Created by nwoolls on 3/29/15.
 * AzureAdRepositoryImpl talks to the Azure Active Directory Graph API
 * via simple REST calls. It should know nothing of LDAP or being an
 * LDAP adapter. Ideally this class and namespace could / should be
 * moved to a reusable library once mature
 */
public class AzureAdRepositoryImpl implements AzureAdRepository {

    private final AuthClient authClient;
    private final RestClient restClient;
    private final String tenantDomain;

    public AzureAdRepositoryImpl(
            String clientId,
            String username,
            String password,
            String tenantDomain) {

        this(new AuthClientImpl(clientId, username, password), tenantDomain);
    }

    public AzureAdRepositoryImpl(
            AuthClient authClient,
            String tenantDomain) {

        this(authClient, new RestClientImpl(authClient, tenantDomain), tenantDomain);
    }

    public AzureAdRepositoryImpl(
            AuthClient authClient,
            RestClient restClient,
            String tenantDomain) {

        this.authClient = authClient;
        this.restClient = restClient;
        this.tenantDomain = tenantDomain;
    }

    @Override
    public Boolean validateCredentials(String clientId, String username, String password) {
        return authClient.validateCredentials(username, password);
    }

    @Override
    public Collection<Application> getAllApplications()
            throws IOException, URISyntaxException, AuthenticationException {
        return restClient.getAllEntries(Application.class, Application.PLURAL_NAME);
    }

    @Override
    public Application getApplication(String oid)
            throws IOException, URISyntaxException, AuthenticationException {
        return restClient.getEntry(Application.class, Application.PLURAL_NAME, oid);
    }

    /* TODO - Fails with HTTP 400 "Property identifierUris value is required but is empty or missing."
              Docs indicate identifierUris is optional
              Existing entries contain an empty array e.g. "identifierUris":[] */
    @Override
    public String createApplication(Application entry)
            throws IOException, URISyntaxException, AuthenticationException {

        Application result = restClient.createEntry(Application.class, Application.PLURAL_NAME, entry);
        return result.getObjectId();
    }

    @Override
    public void updateApplication(String oid, Application entry)
            throws IOException, URISyntaxException, AuthenticationException {
        restClient.updateEntry(Application.PLURAL_NAME, oid, entry);
    }

    @Override
    public void deleteApplication(String oid)
            throws IOException, URISyntaxException, AuthenticationException {
        restClient.deleteEntry(Application.PLURAL_NAME, oid);
    }

    @Override
    public Collection<Contact> getAllContacts()
            throws IOException, URISyntaxException, AuthenticationException {
        return restClient.getAllEntries(Contact.class, Contact.PLURAL_NAME);
    }

    @Override
    public Contact getContact(String oid)
            throws IOException, URISyntaxException, AuthenticationException {
        return restClient.getEntry(Contact.class, Contact.PLURAL_NAME, oid);
    }

    @Override
    public void updateContact(String oid, Contact entry)
            throws IOException, URISyntaxException, AuthenticationException {
        restClient.updateEntry(Contact.PLURAL_NAME, oid, entry);
    }

    @Override
    public void deleteContact(String oid)
            throws IOException, URISyntaxException, AuthenticationException {
        restClient.deleteEntry(Contact.PLURAL_NAME, oid);
    }

    @Override
    public Collection<Device> getAllDevices()
            throws IOException, URISyntaxException, AuthenticationException {
        return restClient.getAllEntries(Device.class, Device.PLURAL_NAME);
    }

    @Override
    public Device getDevice(String oid)
            throws IOException, URISyntaxException, AuthenticationException {
        return restClient.getEntry(Device.class, Device.PLURAL_NAME, oid);
    }

    @Override
    public String createDevice(Device entry)
            throws IOException, URISyntaxException, AuthenticationException {

        Device result = restClient.createEntry(Device.class, Device.PLURAL_NAME, entry);
        return result.getObjectId();
    }

    @Override
    public void updateDevice(String oid, Device entry)
            throws IOException, URISyntaxException, AuthenticationException {
        restClient.updateEntry(Device.PLURAL_NAME, oid, entry);
    }

    @Override
    public void deleteDevice(String oid)
            throws IOException, URISyntaxException, AuthenticationException {
        restClient.deleteEntry(Device.PLURAL_NAME, oid);
    }

    @Override
    public Collection<DirectoryRole> getAllDirectoryRoles()
            throws IOException, URISyntaxException, AuthenticationException {
        return restClient.getAllEntries(DirectoryRole.class, DirectoryRole.PLURAL_NAME);
    }

    @Override
    public DirectoryRole getDirectoryRole(String oid)
            throws IOException, URISyntaxException, AuthenticationException {
        return restClient.getEntry(DirectoryRole.class, DirectoryRole.PLURAL_NAME, oid);
    }

    @Override
    public Collection<Group> getAllGroups()
            throws IOException, URISyntaxException, AuthenticationException {
        return restClient.getAllEntries(Group.class, Group.PLURAL_NAME);
    }

    @Override
    public Group getGroup(String oid)
            throws IOException, URISyntaxException, AuthenticationException {
        return restClient.getEntry(Group.class, Group.PLURAL_NAME, oid);
    }

    @Override
    public String createGroup(Group entry)
            throws IOException, URISyntaxException, AuthenticationException {

        Group result = restClient.createEntry(Group.class, Group.PLURAL_NAME, entry);
        return result.getObjectId();
    }

    @Override
    public void updateGroup(String oid, Group entry)
            throws IOException, URISyntaxException, AuthenticationException {
        restClient.updateEntry(Group.PLURAL_NAME, oid, entry);
    }

    @Override
    public void deleteGroup(String oid)
            throws IOException, URISyntaxException, AuthenticationException {
        restClient.deleteEntry(Group.PLURAL_NAME, oid);
    }

    @Override
    public Collection<OAuth2PermissionGrant> getAllOAuth2PermissionGrants()
            throws IOException, URISyntaxException, AuthenticationException {
        return restClient.getAllEntries(OAuth2PermissionGrant.class, OAuth2PermissionGrant.PLURAL_NAME);
    }

    @Override
    public OAuth2PermissionGrant getOAuth2PermissionGrant(String oid)
            throws IOException, URISyntaxException, AuthenticationException {
        return restClient.getEntry(OAuth2PermissionGrant.class, OAuth2PermissionGrant.PLURAL_NAME, oid);
    }

    @Override
    public String createOAuth2PermissionGrant(OAuth2PermissionGrant entry)
            throws IOException, URISyntaxException, AuthenticationException {

        OAuth2PermissionGrant result = restClient.createEntry(OAuth2PermissionGrant.class, OAuth2PermissionGrant.PLURAL_NAME, entry);
        return result.getObjectId();
    }

    @Override
    public void updateOAuth2PermissionGrant(String oid, OAuth2PermissionGrant entry)
            throws IOException, URISyntaxException, AuthenticationException {
        restClient.updateEntry(OAuth2PermissionGrant.PLURAL_NAME, oid, entry);
    }

    @Override
    public void deleteOAuth2PermissionGrant(String oid)
            throws IOException, URISyntaxException, AuthenticationException {
        restClient.deleteEntry(OAuth2PermissionGrant.PLURAL_NAME, oid);
    }

    @Override
    public Collection<ServicePrincipal> getAllServicePrincipals()
            throws IOException, URISyntaxException, AuthenticationException {
        return restClient.getAllEntries(ServicePrincipal.class, ServicePrincipal.PLURAL_NAME);
    }

    @Override
    public ServicePrincipal getServicePrincipal(String oid)
            throws IOException, URISyntaxException, AuthenticationException {
        return restClient.getEntry(ServicePrincipal.class, ServicePrincipal.PLURAL_NAME, oid);
    }

    @Override
    public String createServicePrincipal(ServicePrincipal entry)
            throws IOException, URISyntaxException, AuthenticationException {

        ServicePrincipal result = restClient.createEntry(ServicePrincipal.class, ServicePrincipal.PLURAL_NAME, entry);
        return result.getObjectId();
    }

    @Override
    public void updateServicePrincipal(String oid, ServicePrincipal entry)
            throws IOException, URISyntaxException, AuthenticationException {
        restClient.updateEntry(ServicePrincipal.PLURAL_NAME, oid, entry);
    }

    @Override
    public void deleteServicePrincipal(String oid)
            throws IOException, URISyntaxException, AuthenticationException {
        restClient.deleteEntry(ServicePrincipal.PLURAL_NAME, oid);
    }

    @Override
    public Collection<SubscribedSku> getAllSubscribedSkus()
            throws IOException, URISyntaxException, AuthenticationException {
        return restClient.getAllEntries(SubscribedSku.class, SubscribedSku.PLURAL_NAME);
    }

    @Override
    public SubscribedSku getSubscribedSku(String oid)
            throws IOException, URISyntaxException, AuthenticationException {
        return restClient.getEntry(SubscribedSku.class, SubscribedSku.PLURAL_NAME, oid);
    }

    @Override
    public Collection<TenantDetail> getAllTenantDetails()
            throws IOException, URISyntaxException, AuthenticationException {
        return restClient.getAllEntries(TenantDetail.class, TenantDetail.PLURAL_NAME);
    }

    @Override
    public TenantDetail getTenantDetail(String oid)
            throws IOException, URISyntaxException, AuthenticationException {
        return restClient.getEntry(TenantDetail.class, TenantDetail.PLURAL_NAME, oid);
    }

    @Override
    public void updateTenantDetail(String oid, TenantDetail entry)
            throws IOException, URISyntaxException, AuthenticationException {
        restClient.updateEntry(TenantDetail.PLURAL_NAME, oid, entry);
    }

    @Override
    public Collection<User> getAllUsers()
            throws IOException, URISyntaxException, AuthenticationException {
        return restClient.getAllEntries(User.class, User.PLURAL_NAME);
    }

    @Override
    public User getUser(String oid)
            throws IOException, URISyntaxException, AuthenticationException {
        return restClient.getEntry(User.class, User.PLURAL_NAME, oid);
    }

    @Override
    public String createUser(User entry)
            throws IOException, URISyntaxException, AuthenticationException {

        User result = restClient.createEntry(User.class, User.PLURAL_NAME, entry);
        return result.getObjectId();
    }

    @Override
    public void updateUser(String oid, User entry)
            throws IOException, URISyntaxException, AuthenticationException {
        restClient.updateEntry(User.PLURAL_NAME, oid, entry);
    }

    @Override
    public void deleteUser(String oid)
            throws IOException, URISyntaxException, AuthenticationException {
        restClient.deleteEntry(User.PLURAL_NAME, oid);
    }

    private final String MEMBERS_PATH = "members";

    @Override
    public Collection<DirectoryObject> getAllMembers(String groupOid) throws IOException, URISyntaxException, AuthenticationException {

        String path = String.format("%s/%s/%s", Group.PLURAL_NAME, groupOid, MEMBERS_PATH);
        return restClient.getAllEntries(DirectoryObject.class, path);
    }

    @Override
    public DirectoryObject getMember(String groupOid, String oid) throws IOException, URISyntaxException, AuthenticationException {

        String path = String.format("%s/%s/%s", Group.PLURAL_NAME, groupOid, MEMBERS_PATH);
        return restClient.getEntry(DirectoryObject.class, path, oid);
    }

    @Override
    public void createMember(String groupOid, String oid) throws IOException, URISyntaxException, AuthenticationException {

        String path = String.format("%s/%s/$links/%s", Group.PLURAL_NAME, groupOid, MEMBERS_PATH);
        String memberUrl = String.format("https://graph.windows.net/%s/directoryObjects/%s", tenantDomain, oid);
        ObjectLink entryLink = new ObjectLink();
        entryLink.setUrl(memberUrl);
        restClient.createEntry(String.class, path, entryLink);
    }

    @Override
    public void deleteMember(String groupOid, String oid) throws IOException, URISyntaxException, AuthenticationException {

        String path = String.format("%s/%s/$links/%s", Group.PLURAL_NAME, groupOid, MEMBERS_PATH);
        restClient.deleteEntry(path, oid);
    }
}

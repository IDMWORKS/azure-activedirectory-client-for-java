package com.identityforge.aad.adcl4j;

import com.identityforge.aad.adcl4j.io.ExcludeTransformer;
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
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * Created by nwoolls on 5/4/15.
 */
public class RestClientImpl implements RestClient {

    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTransformer DATE_TRANSFORMER = new DateTransformer(DATE_FORMAT);
    private static final String GRAPH_API_URI = "https://graph.windows.net";
    private static final String APPLICATION_JSON = "application/json";
    private static final String JS_CLASS_MASK = "*.class";
    private static final String JS_VALUE_PATH = "value";
    private static final String JS_VALUES_PATH = "values.values";

    private final AuthClient authClient;
    private final String tenantDomain;

    public RestClientImpl(AuthClient authClient, String tenantDomain) {

        this.authClient = authClient;
        this.tenantDomain = tenantDomain;
    }

    @Override
    public <T> T createEntry(Class<T> clazz, String path, Object entry)
            throws IOException, URISyntaxException, AuthenticationException {

        T result = null;

        String payload = new JSONSerializer()
                // exclude class info
                .exclude(JS_CLASS_MASK)
                // exclude properties with NULL values
                .transform(new ExcludeTransformer(), void.class)
                // set date format
                .transform(DATE_TRANSFORMER, Date.class)
                .deepSerialize(entry);
        String response = postRestEntity(path, payload);

        if (response != null) {
            result = new JSONDeserializer<T>()
                    .use(null, clazz)
                            // set date format
                    .use(Date.class, DATE_TRANSFORMER)
                    .deserialize(response);
        }

        return result;
    }

    @Override
    public void deleteEntry(String path, String oid)
            throws IOException, URISyntaxException, AuthenticationException {
        deleteRestEntity(path, oid);
    }

    @Override
    public void updateEntry(String path, String oid, Object entry)
            throws IOException, URISyntaxException, AuthenticationException {

        String payload = new JSONSerializer()
                // exclude class info
                .exclude(JS_CLASS_MASK)
                // exclude properties with NULL values
                .transform(new ExcludeTransformer(), void.class)
                // set date format
                .transform(DATE_TRANSFORMER, Date.class)
                .serialize(entry);

        patchRestEntity(path, oid, payload);
    }

    @Override
    public <T> T getEntry(Class<T> clazz, String path, String oid)
            throws IOException, URISyntaxException, AuthenticationException {

        String response = getRestEntity(path, oid);

        T result = new JSONDeserializer<T>()
                .use(null, clazz)
                // set date format
                .use(Date.class, DATE_TRANSFORMER)
                .deserialize(response);

        return result;
    }

    @Override
    public <T> Collection<T> getAllEntries(Class<T> clazz, String path)
            throws IOException, URISyntaxException, AuthenticationException {

        String response = getRestEntity(path, null);

        Collection<T> results = new JSONDeserializer<Map<String, Collection<T>>>()
                .use(JS_VALUES_PATH, clazz)
                // set date format
                .use(Date.class, DATE_TRANSFORMER)
                .deserialize(response)
                .get(JS_VALUE_PATH);

        return results;
    }

    private String getRestEntity(String path, String oid)
            throws IOException, URISyntaxException, AuthenticationException {

        authClient.ensureAuthenticated();

        String result;
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {

            HttpGet httpGet = new HttpGet(buildRestUri(path, oid));
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

    private String deleteRestEntity(String path, String oid)
            throws IOException, URISyntaxException, AuthenticationException {

        authClient.ensureAuthenticated();

        String result;
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {

            HttpDelete httpDelete = new HttpDelete(buildRestUri(path, oid));
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

    private String postRestEntity(String path, String payload)
            throws IOException, URISyntaxException, AuthenticationException{

        authClient.ensureAuthenticated();

        String result;
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {

            HttpPost httpPost = new HttpPost(buildRestUri(path, null));
            setHeaders(httpPost);
            httpPost.setEntity(new StringEntity(payload));

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                if ((response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) ||
                        // using POST to add a member to a group returns 204
                        (response.getStatusLine().getStatusCode() == HttpStatus.SC_NO_CONTENT)) {
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
    private void patchRestEntity(String path, String oid, String payload)
            throws IOException, URISyntaxException, AuthenticationException {

        authClient.ensureAuthenticated();

        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {

            HttpPatch httpPatch = new HttpPatch(buildRestUri(path, oid));
            setHeaders(httpPatch);
            httpPatch.setEntity(new StringEntity(payload));

            try (CloseableHttpResponse response = httpClient.execute(httpPatch)) {
                if (response.getStatusLine().getStatusCode() != HttpStatus.SC_NO_CONTENT) {
                    throw new HTTPException(response.getStatusLine().getStatusCode());
                }
            }
        }
    }

    private URI buildRestUri(String path, String oid) throws URISyntaxException {

        URIBuilder uriBuilder = new URIBuilder(GRAPH_API_URI);
        uriBuilder.setPath(String.format("/%s/%s/%s",
                this.tenantDomain,
                // not lower-case, e.g. DirectoryRoles => directoryRoles
                StringUtils.uncapitalize(path),
                oid == null ? "" : oid));
        // oldest API version that returns immutableId
        uriBuilder.setParameter("api-version", "1.5");

        return uriBuilder.build();
    }

    private void setHeaders(AbstractHttpMessage httpMessage) {

        String bearerToken = authClient.getAuthenticationResult().getAccessToken();
        httpMessage.setHeader("Authorization", String.format("Bearer %s", bearerToken));
        httpMessage.setHeader("Content-type", APPLICATION_JSON);
    }
}

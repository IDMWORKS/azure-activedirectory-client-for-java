package com.identityforge.aad.adcl4j;

import com.microsoft.aad.adal4j.AuthenticationContext;
import com.microsoft.aad.adal4j.AuthenticationResult;
import org.apache.commons.lang3.StringUtils;

import java.net.MalformedURLException;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by nwoolls on 5/4/15.
 */
public class AuthClientImpl implements  AuthClient {

    private static final String GRAPH_API_URI = "https://graph.windows.net";
    private static final String AUTHORITY_URI = "https://login.windows.net/common";

    private AuthenticationResult authenticationResult = null;
    private final String clientId;
    private final String username;
    private final String password;

    public AuthClientImpl(String clientId, String username, String password) {

        this.clientId = clientId;
        this.username = username;
        this.password = password;
    }

    public AuthenticationResult getAuthenticationResult() {
        return authenticationResult;
    }

    public void ensureAuthenticated() throws AuthenticationException {

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
                    this.username, this.password);
        }
    }

    public Boolean validateCredentials(String username, String password) {

        Boolean result = false;

        try {
            AuthenticationResult response = getAccessTokenFromUserCredentials(username, password);
            result = !StringUtils.isEmpty(response.getAccessToken());
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        return result;
    }

    private AuthenticationResult getAccessTokenFromRefreshToken() throws AuthenticationException {
        return getAccessToken(clientId, null, null, authenticationResult.getRefreshToken());
    }

    private AuthenticationResult getAccessTokenFromUserCredentials(String username, String password)
            throws AuthenticationException {
        return getAccessToken(clientId, username, password, null);
    }

    private AuthenticationResult getAccessToken(
            String clientId, String username, String password, String refreshToken)
            throws AuthenticationException {

        AuthenticationContext context;
        AuthenticationResult result = null;
        ExecutorService service = Executors.newFixedThreadPool(1);
        try {
            try {
                context = new AuthenticationContext(AUTHORITY_URI, false, service);
                Future<AuthenticationResult> future;
                if (refreshToken == null) {
                    future = context.acquireToken(GRAPH_API_URI, clientId, username, password, null);
                } else {
                    future = context.acquireTokenByRefreshToken(refreshToken, clientId, null);
                }
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
}

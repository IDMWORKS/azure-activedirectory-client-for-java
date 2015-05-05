package com.identityforge.aad.adcl4j;

import com.microsoft.aad.adal4j.AuthenticationResult;

/**
 * Created by nwoolls on 5/4/15.
 */
public interface AuthClient {
    void ensureAuthenticated() throws AuthenticationException;
    AuthenticationResult getAuthenticationResult();
    Boolean validateCredentials(String username, String password);
}

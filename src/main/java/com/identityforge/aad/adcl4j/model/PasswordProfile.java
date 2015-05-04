package com.identityforge.aad.adcl4j.model;

/**
 * Created by nwoolls on 4/29/15.
 */
public class PasswordProfile {
    private String password;
    private Boolean forceChangePasswordNextLogin;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getForceChangePasswordNextLogin() {
        return forceChangePasswordNextLogin;
    }

    public void setForceChangePasswordNextLogin(Boolean forceChangePasswordNextLogin) {
        this.forceChangePasswordNextLogin = forceChangePasswordNextLogin;
    }
}

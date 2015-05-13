package com.identityforge.aad.adcl4j.model.entity;

import java.util.Collection;

/**
 * Created by nwoolls on 4/4/15.
 */
public class ServicePrincipal extends DirectoryObject {

    public static final String PLURAL_NAME = "servicePrincipals";

    private Boolean accountEnabled;
    private String appDisplayName;
    private String appId;
    private String appOwnerTenantId;
    private Boolean appRoleAssignmentRequired;
    private Collection<Object> appRoles;
    private Object authenticationPolicy;
    private String errorUrl;
    private String homepage;
    private Collection<Object> keyCredentials;
    private String logoutUrl;
    private Collection<Object> oauth2Permissions;
    private Collection<Object> passwordCredentials;
    private String preferredTokenSigningKeyThumbprint;
    private String publisherName;
    private Collection<String> replyUrls;
    private String samlMetadataUrl;
    private Collection<String> servicePrincipalNames;
    private Collection<String> tags;

    public Boolean isAccountEnabled() {
        return accountEnabled;
    }

    public void setAccountEnabled(Boolean accountEnabled) {
        this.accountEnabled = accountEnabled;
    }

    public String getAppDisplayName() {
        return appDisplayName;
    }

    public void setAppDisplayName(String appDisplayName) {
        this.appDisplayName = appDisplayName;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppOwnerTenantId() {
        return appOwnerTenantId;
    }

    public void setAppOwnerTenantId(String appOwnerTenantId) {
        this.appOwnerTenantId = appOwnerTenantId;
    }

    public Boolean isAppRoleAssignmentRequired() {
        return appRoleAssignmentRequired;
    }

    public void setAppRoleAssignmentRequired(Boolean appRoleAssignmentRequired) {
        this.appRoleAssignmentRequired = appRoleAssignmentRequired;
    }

    public Collection<Object> getAppRoles() {
        return appRoles;
    }

    public void setAppRoles(Collection<Object> appRoles) {
        this.appRoles = appRoles;
    }

    public Object getAuthenticationPolicy() {
        return authenticationPolicy;
    }

    public void setAuthenticationPolicy(Object authenticationPolicy) {
        this.authenticationPolicy = authenticationPolicy;
    }

    public String getErrorUrl() {
        return errorUrl;
    }

    public void setErrorUrl(String errorUrl) {
        this.errorUrl = errorUrl;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Collection<Object> getKeyCredentials() {
        return keyCredentials;
    }

    public void setKeyCredentials(Collection<Object> keyCredentials) {
        this.keyCredentials = keyCredentials;
    }

    public String getLogoutUrl() {
        return logoutUrl;
    }

    public void setLogoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }

    public Collection<Object> getOauth2Permissions() {
        return oauth2Permissions;
    }

    public void setOauth2Permissions(Collection<Object> oauth2Permissions) {
        this.oauth2Permissions = oauth2Permissions;
    }

    public Collection<Object> getPasswordCredentials() {
        return passwordCredentials;
    }

    public void setPasswordCredentials(Collection<Object> passwordCredentials) {
        this.passwordCredentials = passwordCredentials;
    }

    public String getPreferredTokenSigningKeyThumbprint() {
        return preferredTokenSigningKeyThumbprint;
    }

    public void setPreferredTokenSigningKeyThumbprint(String preferredTokenSigningKeyThumbprint) {
        this.preferredTokenSigningKeyThumbprint = preferredTokenSigningKeyThumbprint;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public Collection<String> getReplyUrls() {
        return replyUrls;
    }

    public void setReplyUrls(Collection<String> replyUrls) {
        this.replyUrls = replyUrls;
    }

    public String getSamlMetadataUrl() {
        return samlMetadataUrl;
    }

    public void setSamlMetadataUrl(String samlMetadataUrl) {
        this.samlMetadataUrl = samlMetadataUrl;
    }

    public Collection<String> getServicePrincipalNames() {
        return servicePrincipalNames;
    }

    public void setServicePrincipalNames(Collection<String> servicePrincipalNames) {
        this.servicePrincipalNames = servicePrincipalNames;
    }

    public Collection<String> getTags() {
        return tags;
    }

    public void setTags(Collection<String> tags) {
        this.tags = tags;
    }
}

package com.identityforge.aad.adcl4j.model.entity;

import java.util.Collection;

/**
 * Created by nwoolls on 4/2/15.
 */
public class Application extends DirectoryObject {

    public static final String PLURAL_NAME = "applications";

    private String appId;
    private Collection<Object> appRoles;
    private Boolean availableToOtherTenants;
    private String errorUrl;
    private String groupMembershipClaims;
    private String homepage;
    private Collection<String> identifierUris;
    private Collection<Object> keyCredentials;
    private Collection<String> knownClientApplications;
    private String logoutUrl;
    private Object mainLogo;
    private Boolean oauth2AllowImplicitFlow;
    private Boolean oauth2AllowUrlPathMatching;
    private Collection<Object> oauth2Permissions;
    private String oauth2RequiredPostResponse;
    private Collection<Object> passwordCredentials;
    private Boolean publicClient;
    private Collection<String> replyUrls;
    private Collection<Object> requiredResourceAccess;
    private String samlMetadataUrl;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Collection<Object> getAppRoles() {
        return appRoles;
    }

    public void setAppRoles(Collection<Object> appRoles) {
        this.appRoles = appRoles;
    }

    public Boolean isAvailableToOtherTenants() {
        return availableToOtherTenants;
    }

    public void setAvailableToOtherTenants(Boolean availableToOtherTenants) {
        this.availableToOtherTenants = availableToOtherTenants;
    }

    public String getErrorUrl() {
        return errorUrl;
    }

    public void setErrorUrl(String errorUrl) {
        this.errorUrl = errorUrl;
    }

    public String getGroupMembershipClaims() {
        return groupMembershipClaims;
    }

    public void setGroupMembershipClaims(String groupMembershipClaims) {
        this.groupMembershipClaims = groupMembershipClaims;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Collection<String> getIdentifierUris() {
        return identifierUris;
    }

    public void setIdentifierUris(Collection<String> identifierUris) {
        this.identifierUris = identifierUris;
    }

    public Collection<Object> getKeyCredentials() {
        return keyCredentials;
    }

    public void setKeyCredentials(Collection<Object> keyCredentials) {
        this.keyCredentials = keyCredentials;
    }

    public Collection<String> getKnownClientApplications() {
        return knownClientApplications;
    }

    public void setKnownClientApplications(Collection<String> knownClientApplications) {
        this.knownClientApplications = knownClientApplications;
    }

    public String getLogoutUrl() {
        return logoutUrl;
    }

    public void setLogoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }

    public Object getMainLogo() {
        return mainLogo;
    }

    public void setMainLogo(Object mainLogo) {
        this.mainLogo = mainLogo;
    }

    public Boolean isOauth2AllowImplicitFlow() {
        return oauth2AllowImplicitFlow;
    }

    public void setOauth2AllowImplicitFlow(Boolean oauth2AllowImplicitFlow) {
        this.oauth2AllowImplicitFlow = oauth2AllowImplicitFlow;
    }

    public Boolean isOauth2AllowUrlPathMatching() {
        return oauth2AllowUrlPathMatching;
    }

    public void setOauth2AllowUrlPathMatching(Boolean oauth2AllowUrlPathMatching) {
        this.oauth2AllowUrlPathMatching = oauth2AllowUrlPathMatching;
    }

    public Collection<Object> getOauth2Permissions() {
        return oauth2Permissions;
    }

    public void setOauth2Permissions(Collection<Object> oauth2Permissions) {
        this.oauth2Permissions = oauth2Permissions;
    }

    public String getOauth2RequiredPostResponse() {
        return oauth2RequiredPostResponse;
    }

    public void setOauth2RequiredPostResponse(String oauth2RequiredPostResponse) {
        this.oauth2RequiredPostResponse = oauth2RequiredPostResponse;
    }

    public Collection<Object> getPasswordCredentials() {
        return passwordCredentials;
    }

    public void setPasswordCredentials(Collection<Object> passwordCredentials) {
        this.passwordCredentials = passwordCredentials;
    }

    public Boolean isPublicClient() {
        return publicClient;
    }

    public void setPublicClient(Boolean publicClient) {
        this.publicClient = publicClient;
    }

    public Collection<String> getReplyUrls() {
        return replyUrls;
    }

    public void setReplyUrls(Collection<String> replyUrls) {
        this.replyUrls = replyUrls;
    }

    public Collection<Object> getRequiredResourceAccess() {
        return requiredResourceAccess;
    }

    public void setRequiredResourceAccess(Collection<Object> requiredResourceAccess) {
        this.requiredResourceAccess = requiredResourceAccess;
    }

    public String getSamlMetadataUrl() {
        return samlMetadataUrl;
    }

    public void setSamlMetadataUrl(String samlMetadataUrl) {
        this.samlMetadataUrl = samlMetadataUrl;
    }
}

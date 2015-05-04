package com.identityforge.aad.adcl4j.model.entity;

import java.util.Collection;
import java.util.Date;

/**
 * Created by nwoolls on 4/4/15.
 */
public class Group extends Entry {

    public static final String PLURAL_NAME = "groups";

    private String description;
    private Boolean dirSyncEnabled;
    private Date lastDirSyncTime;
    private String mail;
    private Boolean mailEnabled;
    private String mailNickName;
    private String onPremisesSecurityIdentifier;
    private Collection<Object> provisioningErrors;
    private Collection<String> proxyAddresses;
    private Boolean securityEnabled;

    public Boolean isSecurityEnabled() {
        return securityEnabled;
    }

    public void setSecurityEnabled(Boolean securityEnabled) {
        this.securityEnabled = securityEnabled;
    }

    public Collection<String> getProxyAddresses() {
        return proxyAddresses;
    }

    public void setProxyAddresses(Collection<String> proxyAddresses) {
        this.proxyAddresses = proxyAddresses;
    }

    public Collection<Object> getProvisioningErrors() {
        return provisioningErrors;
    }

    public void setProvisioningErrors(Collection<Object> provisioningErrors) {
        this.provisioningErrors = provisioningErrors;
    }

    public String getOnPremisesSecurityIdentifier() {
        return onPremisesSecurityIdentifier;
    }

    public void setOnPremisesSecurityIdentifier(String onPremisesSecurityIdentifier) {
        this.onPremisesSecurityIdentifier = onPremisesSecurityIdentifier;
    }

    public String getMailNickName() {
        return mailNickName;
    }

    public void setMailNickName(String mailNickName) {
        this.mailNickName = mailNickName;
    }

    public Boolean isMailEnabled() {
        return mailEnabled;
    }

    public void setMailEnabled(Boolean mailEnabled) {
        this.mailEnabled = mailEnabled;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getLastDirSyncTime() {
        return lastDirSyncTime;
    }

    public void setLastDirSyncTime(Date lastDirSyncTime) {
        this.lastDirSyncTime = lastDirSyncTime;
    }

    public Boolean isDirSyncEnabled() {
        return dirSyncEnabled;
    }

    public void setDirSyncEnabled(Boolean dirSyncEnabled) {
        this.dirSyncEnabled = dirSyncEnabled;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package com.identityforge.aad.adcl4j.model.entity;

import com.identityforge.aad.adcl4j.model.PasswordProfile;

import java.util.Collection;
import java.util.Date;

/**
 * Created by nwoolls on 4/1/15.
 */
public class User extends Contact {

    public static final String PLURAL_NAME = "users";

    private Boolean accountEnabled;
    private Collection<Object> assignedLicenses;
    private Collection<Object> assignedPlans;
    private Date deletionTimeStamp;
    private String immutableId;
    private String onPremisesSecurityIdentifier;
    private Collection<String> otherMails;
    private String passwordPolicies;
    private PasswordProfile passwordProfile;
    private String preferredLanguage;
    private Collection<Object> provisionedPlans;
    private String usageLocation;
    private String userPrincipalName;
    private String userType;

    public Boolean isAccountEnabled() {
        return accountEnabled;
    }

    public void setAccountEnabled(Boolean accountEnabled) {
        this.accountEnabled = accountEnabled;
    }

    public Collection<Object> getAssignedLicenses() {
        return assignedLicenses;
    }

    public void setAssignedLicenses(Collection<Object> assignedLicenses) {
        this.assignedLicenses = assignedLicenses;
    }

    public Collection<Object> getAssignedPlans() {
        return assignedPlans;
    }

    public void setAssignedPlans(Collection<Object> assignedPlans) {
        this.assignedPlans = assignedPlans;
    }

    public Date getDeletionTimeStamp() {
        return deletionTimeStamp;
    }

    public void setDeletionTimeStamp(Date deletionTimeStamp) {
        this.deletionTimeStamp = deletionTimeStamp;
    }

    public String getImmutableId() {
        return immutableId;
    }

    public void setImmutableId(String immutableId) {
        this.immutableId = immutableId;
    }

    public String getOnPremisesSecurityIdentifier() {
        return onPremisesSecurityIdentifier;
    }

    public void setOnPremisesSecurityIdentifier(String onPremisesSecurityIdentifier) {
        this.onPremisesSecurityIdentifier = onPremisesSecurityIdentifier;
    }

    public Collection<String> getOtherMails() {
        return otherMails;
    }

    public void setOtherMails(Collection<String> otherMails) {
        this.otherMails = otherMails;
    }

    public String getPasswordPolicies() {
        return passwordPolicies;
    }

    public void setPasswordPolicies(String passwordPolicies) {
        this.passwordPolicies = passwordPolicies;
    }

    public PasswordProfile getPasswordProfile() {
        return passwordProfile;
    }

    public void setPasswordProfile(PasswordProfile passwordProfile) {
        this.passwordProfile = passwordProfile;
    }

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public Collection<Object> getProvisionedPlans() {
        return provisionedPlans;
    }

    public void setProvisionedPlans(Collection<Object> provisionedPlans) {
        this.provisionedPlans = provisionedPlans;
    }

    public String getUsageLocation() {
        return usageLocation;
    }

    public void setUsageLocation(String usageLocation) {
        this.usageLocation = usageLocation;
    }

    public String getUserPrincipalName() {
        return userPrincipalName;
    }

    public void setUserPrincipalName(String userPrincipalName) {
        this.userPrincipalName = userPrincipalName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}

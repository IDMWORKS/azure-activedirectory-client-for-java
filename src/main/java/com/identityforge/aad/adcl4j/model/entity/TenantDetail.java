package com.identityforge.aad.adcl4j.model.entity;

import java.util.Collection;
import java.util.Date;

/**
 * Created by nwoolls on 4/4/15.
 */
public class TenantDetail extends DirectoryObject {

    public static final String PLURAL_NAME = "tenantDetails";

    private Collection<Object> assignedPlans;
    private String city;
    private Date companyLastDirSyncTime;
    private String country;
    private String countryLetterCode;
    private Boolean dirSyncEnabled;
    private Collection<String> marketingNotificationEmails;
    private String postalCode;
    private String preferredLanguage;
    private Collection<Object> provisionedPlans;
    private Collection<Object> provisioningErrors;
    private String state;
    private String street;
    private Collection<String> technicalNotificationMails;
    private String telephoneNumber;
    private String tenantType;
    private Collection<String> verifiedDomains;

    public Collection<Object> getAssignedPlans() {
        return assignedPlans;
    }

    public void setAssignedPlans(Collection<Object> assignedPlans) {
        this.assignedPlans = assignedPlans;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getCompanyLastDirSyncTime() {
        return companyLastDirSyncTime;
    }

    public void setCompanyLastDirSyncTime(Date companyLastDirSyncTime) {
        this.companyLastDirSyncTime = companyLastDirSyncTime;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryLetterCode() {
        return countryLetterCode;
    }

    public void setCountryLetterCode(String countryLetterCode) {
        this.countryLetterCode = countryLetterCode;
    }

    public Boolean isDirSyncEnabled() {
        return dirSyncEnabled;
    }

    public void setDirSyncEnabled(Boolean dirSyncEnabled) {
        this.dirSyncEnabled = dirSyncEnabled;
    }

    public Collection<String> getMarketingNotificationEmails() {
        return marketingNotificationEmails;
    }

    public void setMarketingNotificationEmails(Collection<String> marketingNotificationEmails) {
        this.marketingNotificationEmails = marketingNotificationEmails;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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

    public Collection<Object> getProvisioningErrors() {
        return provisioningErrors;
    }

    public void setProvisioningErrors(Collection<Object> provisioningErrors) {
        this.provisioningErrors = provisioningErrors;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Collection<String> getTechnicalNotificationMails() {
        return technicalNotificationMails;
    }

    public void setTechnicalNotificationMails(Collection<String> technicalNotificationMails) {
        this.technicalNotificationMails = technicalNotificationMails;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getTenantType() {
        return tenantType;
    }

    public void setTenantType(String tenantType) {
        this.tenantType = tenantType;
    }

    public Collection<String> getVerifiedDomains() {
        return verifiedDomains;
    }

    public void setVerifiedDomains(Collection<String> verifiedDomains) {
        this.verifiedDomains = verifiedDomains;
    }
}

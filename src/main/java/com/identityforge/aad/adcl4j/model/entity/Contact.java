package com.identityforge.aad.adcl4j.model.entity;

import java.util.Collection;
import java.util.Date;

/**
 * Created by nwoolls on 4/2/15.
 */
public class Contact extends DirectoryObject {

    public static final String PLURAL_NAME = "contacts";

    private String city;
    private String country;
    private String department;
    private Boolean dirSyncEnabled;
    private String facsimileTelephoneNumber;
    private String givenName;
    private String jobTitle;
    private Date lastDirSyncTime;
    private String mail;
    private String mailNickname;
    private String mobile;
    private String physicalDeliveryOfficeName;
    private String postalCode;
    private Collection<Object> provisioningErrors;
    private Collection<String> proxyAddresses;
    private String sipProxyAddress;
    private String state;
    private String streetAddress;
    private String surname;
    private String telephoneNumber;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Boolean isDirSyncEnabled() {
        return dirSyncEnabled;
    }

    public void setDirSyncEnabled(Boolean dirSyncEnabled) {
        this.dirSyncEnabled = dirSyncEnabled;
    }

    public String getFacsimileTelephoneNumber() {
        return facsimileTelephoneNumber;
    }

    public void setFacsimileTelephoneNumber(String facsimileTelephoneNumber) {
        this.facsimileTelephoneNumber = facsimileTelephoneNumber;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Date getLastDirSyncTime() {
        return lastDirSyncTime;
    }

    public void setLastDirSyncTime(Date lastDirSyncTime) {
        this.lastDirSyncTime = lastDirSyncTime;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMailNickname() {
        return mailNickname;
    }

    public void setMailNickname(String mailNickname) {
        this.mailNickname = mailNickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhysicalDeliveryOfficeName() {
        return physicalDeliveryOfficeName;
    }

    public void setPhysicalDeliveryOfficeName(String physicalDeliveryOfficeName) {
        this.physicalDeliveryOfficeName = physicalDeliveryOfficeName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Collection<Object> getProvisioningErrors() {
        return provisioningErrors;
    }

    public void setProvisioningErrors(Collection<Object> provisioningErrors) {
        this.provisioningErrors = provisioningErrors;
    }

    public Collection<String> getProxyAddresses() {
        return proxyAddresses;
    }

    public void setProxyAddresses(Collection<String> proxyAddresses) {
        this.proxyAddresses = proxyAddresses;
    }

    public String getSipProxyAddress() {
        return sipProxyAddress;
    }

    public void setSipProxyAddress(String sipProxyAddress) {
        this.sipProxyAddress = sipProxyAddress;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

}

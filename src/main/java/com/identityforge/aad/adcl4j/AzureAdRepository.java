package com.identityforge.aad.adcl4j;

import com.identityforge.aad.adcl4j.model.entity.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;

/**
 * Created by nwoolls on 5/2/15.
 */
public interface AzureAdRepository {

    Boolean validateCredentials(String clientId, String username, String password);

    Collection<Application> getAllApplications() throws IOException, URISyntaxException, AuthenticationException;
    Application getApplication(String oid) throws IOException, URISyntaxException, AuthenticationException;
    String createApplication(Application entry) throws IOException, URISyntaxException, AuthenticationException;
    void updateApplication(String oid, Application entry) throws IOException, URISyntaxException, AuthenticationException;
    void deleteApplication(String oid) throws IOException, URISyntaxException, AuthenticationException;

    Collection<Contact> getAllContacts() throws IOException, URISyntaxException, AuthenticationException;
    Contact getContact(String oid) throws IOException, URISyntaxException, AuthenticationException;
    void updateContact(String oid, Contact entry) throws IOException, URISyntaxException, AuthenticationException;
    void deleteContact(String oid) throws IOException, URISyntaxException, AuthenticationException;

    Collection<Device> getAllDevices() throws IOException, URISyntaxException, AuthenticationException;
    Device getDevice(String oid) throws IOException, URISyntaxException, AuthenticationException;
    String createDevice(Device entry) throws IOException, URISyntaxException, AuthenticationException;
    void updateDevice(String oid, Device entry) throws IOException, URISyntaxException, AuthenticationException;
    void deleteDevice(String oid) throws IOException, URISyntaxException, AuthenticationException;

    Collection<DirectoryRole> getAllDirectoryRoles() throws IOException, URISyntaxException, AuthenticationException;
    DirectoryRole getDirectoryRole(String oid) throws IOException, URISyntaxException, AuthenticationException;

    Collection<Group> getAllGroups() throws IOException, URISyntaxException, AuthenticationException;
    Group getGroup(String oid) throws IOException, URISyntaxException, AuthenticationException;
    String createGroup(Group entry) throws IOException, URISyntaxException, AuthenticationException;
    void updateGroup(String oid, Group entry) throws IOException, URISyntaxException, AuthenticationException;
    void deleteGroup(String oid) throws IOException, URISyntaxException, AuthenticationException;

    Collection<OAuth2PermissionGrant> getAllOAuth2PermissionGrants() throws IOException, URISyntaxException, AuthenticationException;
    OAuth2PermissionGrant getOAuth2PermissionGrant(String oid) throws IOException, URISyntaxException, AuthenticationException;
    String createOAuth2PermissionGrant(OAuth2PermissionGrant entry) throws IOException, URISyntaxException, AuthenticationException;
    void updateOAuth2PermissionGrant(String oid, OAuth2PermissionGrant entry) throws IOException, URISyntaxException, AuthenticationException;
    void deleteOAuth2PermissionGrant(String oid) throws IOException, URISyntaxException, AuthenticationException;

    Collection<ServicePrincipal> getAllServicePrincipals() throws IOException, URISyntaxException, AuthenticationException;
    ServicePrincipal getServicePrincipal(String oid) throws IOException, URISyntaxException, AuthenticationException;
    String createServicePrincipal(ServicePrincipal entry) throws IOException, URISyntaxException, AuthenticationException;
    void updateServicePrincipal(String oid, ServicePrincipal entry) throws IOException, URISyntaxException, AuthenticationException;
    void deleteServicePrincipal(String oid) throws IOException, URISyntaxException, AuthenticationException;

    Collection<SubscribedSku> getAllSubscribedSkus() throws IOException, URISyntaxException, AuthenticationException;
    SubscribedSku getSubscribedSku(String oid) throws IOException, URISyntaxException, AuthenticationException;

    Collection<TenantDetail> getAllTenantDetails() throws IOException, URISyntaxException, AuthenticationException;
    TenantDetail getTenantDetail(String oid) throws IOException, URISyntaxException, AuthenticationException;
    void updateTenantDetail(String oid, TenantDetail entry) throws IOException, URISyntaxException, AuthenticationException;

    Collection<User> getAllUsers() throws IOException, URISyntaxException, AuthenticationException;
    User getUser(String oid) throws IOException, URISyntaxException, AuthenticationException;
    String createUser(User entry) throws IOException, URISyntaxException, AuthenticationException;
    void updateUser(String oid, User entry) throws IOException, URISyntaxException, AuthenticationException;
    void deleteUser(String oid) throws IOException, URISyntaxException, AuthenticationException;
}

package com.identityforge.aad.adcl4j;

import com.identityforge.aad.adcl4j.model.entity.*;
import com.identityforge.categories.IntegrationTest;
import com.identityforge.categories.UnitTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Properties;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by nwoolls on 5/4/15.
 */
public class AzureAdRepositoryImplTest {

    public static final String TENANT_DETAILS = "tenantDetails";
    public static final String APPLICATIONS = "applications";
    public static final String CONTACTS = "contacts";
    public static final String DEVICES = "devices";
    public static final String DIRECTORY_ROLES = "directoryRoles";
    public static final String GROUPS = "groups";
    public static final String OAUTH_2_PERMISSION_GRANTS = "oauth2PermissionGrants";
    public static final String SERVICE_PRINCIPALS = "servicePrincipals";
    public static final String SUBSCRIBED_SKUS = "subscribedSkus";
    public static final String USERS = "users";

    private AzureAdRepository realRepo;
    private AzureAdRepository mockRepo;
    private Properties props;
    private RestClient mockRestClient;
    private AuthClient mockAuthClient;

    /* Setup test data */

    @Before
    public void setupTestData() {

        String path = new File(System.getProperty("user.dir"),
                "/src/test/config/creds.properties").toString();

        props = new Properties();
        try {
            props.load(new FileInputStream(path));
        } catch (IOException e) {
            fail(e.getLocalizedMessage());
        }

        setupRealRepository();

        setupMockRepository();
    }

    private void setupRealRepository() {

        realRepo = new AzureAdRepositoryImpl(
                props.getProperty("clientId"),
                props.getProperty("username"),
                props.getProperty("password"),
                props.getProperty("tenantDomain"));
    }

    private void setupMockRepository() {

        mockAuthClient = mock(AuthClient.class);
        mockRestClient = mock(RestClient.class);
        mockRepo = new AzureAdRepositoryImpl(mockAuthClient, mockRestClient);

        // setup authentication response for mock Repository
        when(mockAuthClient.validateCredentials(
                        props.getProperty("username"),
                        props.getProperty("password"))
        ).thenReturn(true);
    }

    /* Unit Tests */

    @Test
    @Category(UnitTest.class)
    public void mockRepoShouldValidateCredentials() {

        Boolean valid = mockRepo.validateCredentials(
                props.getProperty("clientId"),
                props.getProperty("username"),
                props.getProperty("password"));

        assertTrue(valid);
    }

    @Test
    @Category(UnitTest.class)
    public void mockRepoShouldGetAllApplications() {
        try {
            mockRepo.getAllApplications();
            verify(mockRestClient).getAllEntries(Application.class, APPLICATIONS);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void mockRepoShouldGetApplication() {
        final String oid = UUID.randomUUID().toString();
        try {
            mockRepo.getApplication(oid);
            verify(mockRestClient).getEntry(Application.class, APPLICATIONS, oid);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void mockRepoShouldDeleteApplication() {
        final String oid = UUID.randomUUID().toString();
        try {
            mockRepo.deleteApplication(oid);
            verify(mockRestClient).deleteEntry(APPLICATIONS, oid);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void mockRepoShouldGetAllContacts() {
        try {
            mockRepo.getAllContacts();
            verify(mockRestClient).getAllEntries(Contact.class, CONTACTS);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void mockRepoShouldGetContact() {
        final String oid = UUID.randomUUID().toString();
        try {
            mockRepo.getContact(oid);
            verify(mockRestClient).getEntry(Contact.class, CONTACTS, oid);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void mockRepoShouldDeleteContact() {
        final String oid = UUID.randomUUID().toString();
        try {
            mockRepo.deleteContact(oid);
            verify(mockRestClient).deleteEntry(CONTACTS, oid);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void mockRepoShouldGetAllDevices() {
        try {
            mockRepo.getAllDevices();
            verify(mockRestClient).getAllEntries(Device.class, DEVICES);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void mockRepoShouldGetDevice() {
        final String oid = UUID.randomUUID().toString();
        try {
            mockRepo.getDevice(oid);
            verify(mockRestClient).getEntry(Device.class, DEVICES, oid);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void mockRepoShouldDeleteDevice() {
        final String oid = UUID.randomUUID().toString();
        try {
            mockRepo.deleteDevice(oid);
            verify(mockRestClient).deleteEntry(DEVICES, oid);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void mockRepoShouldGetAllDirectoryRoles() {
        try {
            mockRepo.getAllDirectoryRoles();
            verify(mockRestClient).getAllEntries(DirectoryRole.class, DIRECTORY_ROLES);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void mockRepoShouldGetDirectoryRole() {
        final String oid = UUID.randomUUID().toString();
        try {
            mockRepo.getDirectoryRole(oid);
            verify(mockRestClient).getEntry(DirectoryRole.class, DIRECTORY_ROLES, oid);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void mockRepoShouldGetAllGroups() {
        try {
            mockRepo.getAllGroups();
            verify(mockRestClient).getAllEntries(Group.class, GROUPS);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void mockRepoShouldGetGroup() {
        final String oid = UUID.randomUUID().toString();
        try {
            mockRepo.getGroup(oid);
            verify(mockRestClient).getEntry(Group.class, GROUPS, oid);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void mockRepoShouldDeleteGroup() {
        final String oid = UUID.randomUUID().toString();
        try {
            mockRepo.deleteGroup(oid);
            verify(mockRestClient).deleteEntry(GROUPS, oid);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void mockRepoShouldGetAllOAuth2PermissionGrants() {
        try {
            mockRepo.getAllOAuth2PermissionGrants();
            verify(mockRestClient).getAllEntries(OAuth2PermissionGrant.class, OAUTH_2_PERMISSION_GRANTS);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void mockRepoShouldGetOAuth2PermissionGrant() {
        final String oid = UUID.randomUUID().toString();
        try {
            mockRepo.getOAuth2PermissionGrant(oid);
            verify(mockRestClient).getEntry(OAuth2PermissionGrant.class, OAUTH_2_PERMISSION_GRANTS, oid);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void mockRepoShouldDeleteOAuth2PermissionGrant() {
        final String oid = UUID.randomUUID().toString();
        try {
            mockRepo.deleteOAuth2PermissionGrant(oid);
            verify(mockRestClient).deleteEntry(OAUTH_2_PERMISSION_GRANTS, oid);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void mockRepoShouldGetAllServicePrincipals() {
        try {
            mockRepo.getAllServicePrincipals();
            verify(mockRestClient).getAllEntries(ServicePrincipal.class, SERVICE_PRINCIPALS);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void mockRepoShouldGetServicePrincipal() {
        final String oid = UUID.randomUUID().toString();
        try {
            mockRepo.getServicePrincipal(oid);
            verify(mockRestClient).getEntry(ServicePrincipal.class, SERVICE_PRINCIPALS, oid);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void mockRepoShouldDeleteServicePrincipal() {
        final String oid = UUID.randomUUID().toString();
        try {
            mockRepo.deleteServicePrincipal(oid);
            verify(mockRestClient).deleteEntry(SERVICE_PRINCIPALS, oid);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void mockRepoShouldGetAllSubscribedSkus() {
        try {
            mockRepo.getAllSubscribedSkus();
            verify(mockRestClient).getAllEntries(SubscribedSku.class, SUBSCRIBED_SKUS);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void mockRepoShouldGetSubscribedSku() {
        final String oid = UUID.randomUUID().toString();
        try {
            mockRepo.getSubscribedSku(oid);
            verify(mockRestClient).getEntry(SubscribedSku.class, SUBSCRIBED_SKUS, oid);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void mockRepoShouldGetAllTenantDetails() {
        try {
            mockRepo.getAllTenantDetails();
            verify(mockRestClient).getAllEntries(TenantDetail.class, TENANT_DETAILS);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void mockRepoShouldGetTenantDetail() {
        final String oid = UUID.randomUUID().toString();
        try {
            mockRepo.getTenantDetail(oid);
            verify(mockRestClient).getEntry(TenantDetail.class, TENANT_DETAILS, oid);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void mockRepoShouldGetAllUsers() {
        try {
            mockRepo.getAllUsers();
            verify(mockRestClient).getAllEntries(User.class, USERS);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void mockRepoShouldGetUser() {
        final String oid = UUID.randomUUID().toString();
        try {
            mockRepo.getUser(oid);
            verify(mockRestClient).getEntry(User.class, USERS, oid);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void mockRepoShouldDeleteUser() {
        final String oid = UUID.randomUUID().toString();
        try {
            mockRepo.deleteUser(oid);
            verify(mockRestClient).deleteEntry(USERS, oid);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    /* Integration Tests */

    @Test
    @Category(IntegrationTest.class)
    public void realRepoShouldManageApplications() {
        try {
            // AzureAdRepository.getAllApplications()
            Collection<Application> entities = realRepo.getAllApplications();
            String entityId = null;
            if (entities.size() > 0) {
                entityId = entities.iterator().next().getObjectId();
            }
            assertTrue(entities.size() > 0);

            // AzureAdRepository.getApplication()
            Application entity = null;
            if (entityId != null) {
                entity = realRepo.getApplication(entityId);
            }
            assertNotNull(entity);
            assertEquals(entity.getObjectId(), entityId);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void realRepoShouldManageDirectoryRoles() {
        try {
            // AzureAdRepository.getAllDirectoryRoles()
            Collection<DirectoryRole> entities = realRepo.getAllDirectoryRoles();
            String entityId = null;
            if (entities.size() > 0) {
                entityId = entities.iterator().next().getObjectId();
            }
            assertTrue(entities.size() > 0);

            // AzureAdRepository.getDirectoryRole()
            DirectoryRole entity = null;
            if (entityId != null) {
                entity = realRepo.getDirectoryRole(entityId);
            }
            assertNotNull(entity);
            assertEquals(entity.getObjectId(), entityId);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void realRepoShouldManageGroups() {
        try {
            // AzureAdRepository.getAllGroups()
            Collection<Group> entities = realRepo.getAllGroups();
            String entityId = null;
            if (entities.size() > 0) {
                entityId = entities.iterator().next().getObjectId();
            }
            assertTrue(entities.size() > 0);

            // AzureAdRepository.getGroup()
            Group entity = null;
            if (entityId != null) {
                entity = realRepo.getGroup(entityId);
            }
            assertNotNull(entity);
            assertEquals(entity.getObjectId(), entityId);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void realRepoShouldManageOAuth2PermissionGrants() {
        try {
            // AzureAdRepository.getAllOAuth2PermissionGrants()
            Collection<OAuth2PermissionGrant> entities = realRepo.getAllOAuth2PermissionGrants();
            String entityId = null;
            if (entities.size() > 0) {
                entityId = entities.iterator().next().getObjectId();
            }
            assertTrue(entities.size() > 0);

            // AzureAdRepository.getOAuth2PermissionGrant()
            OAuth2PermissionGrant entity = null;
            if (entityId != null) {
                entity = realRepo.getOAuth2PermissionGrant(entityId);
            }
            assertNotNull(entity);
            assertEquals(entity.getObjectId(), entityId);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void realRepoShouldManageServicePrincipals() {
        try {
            // AzureAdRepository.getAllServicePrincipals()
            Collection<ServicePrincipal> entities = realRepo.getAllServicePrincipals();
            String entityId = null;
            if (entities.size() > 0) {
                entityId = entities.iterator().next().getObjectId();
            }
            assertTrue(entities.size() > 0);

            // AzureAdRepository.getServicePrincipal()
            ServicePrincipal entity = null;
            if (entityId != null) {
                entity = realRepo.getServicePrincipal(entityId);
            }
            assertNotNull(entity);
            assertEquals(entity.getObjectId(), entityId);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void realRepoShouldManageTenantDetails() {
        try {
            // AzureAdRepository.getAllTenantDetails()
            Collection<TenantDetail> entities = realRepo.getAllTenantDetails();
            String entityId = null;
            if (entities.size() > 0) {
                entityId = entities.iterator().next().getObjectId();
            }
            assertTrue(entities.size() > 0);

            // AzureAdRepository.getTenantDetail()
            TenantDetail entity = null;
            if (entityId != null) {
                entity = realRepo.getTenantDetail(entityId);
            }
            assertNotNull(entity);
            assertEquals(entity.getObjectId(), entityId);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void realRepoShouldManageUsers() {
        try {
            // AzureAdRepository.getAllUsers()
            Collection<User> entities = realRepo.getAllUsers();
            String entityId = null;
            boolean found = false;
            String currentUserName = props.getProperty("username");
            for (User entity : entities) {
                if (entity.getUserPrincipalName().equals(currentUserName)) {
                    found = true;
                    entityId = entity.getObjectId();
                    break;
                }
            }
            assertTrue(entities.size() > 0);
            assertTrue(found);

            // AzureAdRepository.getUser()
            User entity = null;
            if (entityId != null) {
                entity = realRepo.getUser(entityId);
            }
            assertNotNull(entity);
            assertEquals(entity.getObjectId(), entityId);
            assertEquals(entity.getUserPrincipalName(), currentUserName);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

}

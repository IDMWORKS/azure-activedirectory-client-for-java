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
    private Application fakeApplication;
    private Device fakeDevice;
    private Group fakeGroup;
    private OAuth2PermissionGrant fakePermissionGrant;
    private ServicePrincipal fakeServicePrincipal;
    private User fakeUser;
    private Contact fakeContact;
    private TenantDetail fakeTenantDetail;

    /* Setup test data */

    @Before
    public void setupTestData()
            throws URISyntaxException,
            IOException,
            AuthenticationException {

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

    private void setupMockRepository()
            throws AuthenticationException,
            IOException,
            URISyntaxException {

        AuthClient mockAuthClient = mock(AuthClient.class);
        mockRestClient = mock(RestClient.class);
        mockRepo = new AzureAdRepositoryImpl(mockAuthClient, mockRestClient);

        // setup authentication response for mock Repository
        when(mockAuthClient.validateCredentials(
                        props.getProperty("username"),
                        props.getProperty("password"))
        ).thenReturn(true);

        // set create responses for mock Repository

        // Applications
        fakeApplication = new Application();
        fakeApplication.setObjectId(UUID.randomUUID().toString());

        when(mockRestClient.createEntry(
                        eq(Application.class),
                        eq(APPLICATIONS),
                        any(Application.class))
        ).thenReturn(fakeApplication);

        // Devices
        fakeDevice = new Device();
        fakeDevice.setObjectId(UUID.randomUUID().toString());

        when(mockRestClient.createEntry(
                        eq(Device.class),
                        eq(DEVICES),
                        any(Device.class))
        ).thenReturn(fakeDevice);

        // Groups
        fakeGroup = new Group();
        fakeGroup.setObjectId(UUID.randomUUID().toString());

        when(mockRestClient.createEntry(
                        eq(Group.class),
                        eq(GROUPS),
                        any(Group.class))
        ).thenReturn(fakeGroup);

        // OAuth2PermissionGrants
        fakePermissionGrant = new OAuth2PermissionGrant();
        fakePermissionGrant.setObjectId(UUID.randomUUID().toString());

        when(mockRestClient.createEntry(
                        eq(OAuth2PermissionGrant.class),
                        eq(OAUTH_2_PERMISSION_GRANTS),
                        any(OAuth2PermissionGrant.class))
        ).thenReturn(fakePermissionGrant);

        // ServicePrincipals
        fakeServicePrincipal = new ServicePrincipal();
        fakeServicePrincipal.setObjectId(UUID.randomUUID().toString());

        when(mockRestClient.createEntry(
                        eq(ServicePrincipal.class),
                        eq(SERVICE_PRINCIPALS),
                        any(ServicePrincipal.class))
        ).thenReturn(fakeServicePrincipal);

        // Users
        fakeUser = new User();
        fakeUser.setObjectId(UUID.randomUUID().toString());

        when(mockRestClient.createEntry(
                        eq(User.class),
                        eq(USERS),
                        any(User.class))
        ).thenReturn(fakeUser);

        // create additional fake data

        // Contacts
        fakeContact = new Contact();
        fakeContact.setObjectId(UUID.randomUUID().toString());

        // TenantDetails
        fakeTenantDetail = new TenantDetail();
        fakeTenantDetail.setObjectId(UUID.randomUUID().toString());
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
    public void mockRepoShouldCreateApplication() {
        Application entry = new Application();
        try {
            String oid = mockRepo.createApplication(entry);
            assertEquals(oid, fakeApplication.getObjectId());
            verify(mockRestClient).createEntry(Application.class, APPLICATIONS, entry);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void mockRepoShouldUpdateApplication() {
        try {
            mockRepo.updateApplication(fakeApplication.getObjectId(), fakeApplication);
            verify(mockRestClient).updateEntry(APPLICATIONS, fakeApplication.getObjectId(), fakeApplication);
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
    public void mockRepoShouldUpdateContact() {
        try {
            mockRepo.updateContact(fakeContact.getObjectId(), fakeContact);
            verify(mockRestClient).updateEntry(CONTACTS, fakeContact.getObjectId(), fakeContact);
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
    public void mockRepoShouldCreateDevice() {
        Device entry = new Device();
        try {
            String oid = mockRepo.createDevice(entry);
            assertEquals(oid, fakeDevice.getObjectId());
            verify(mockRestClient).createEntry(Device.class, DEVICES, entry);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void mockRepoShouldUpdateDevice() {
        try {
            mockRepo.updateDevice(fakeDevice.getObjectId(), fakeDevice);
            verify(mockRestClient).updateEntry(DEVICES, fakeDevice.getObjectId(), fakeDevice);
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
    public void mockRepoShouldCreateGroup() {
        Group entry = new Group();
        try {
            String oid = mockRepo.createGroup(entry);
            assertEquals(oid, fakeGroup.getObjectId());
            verify(mockRestClient).createEntry(Group.class, GROUPS, entry);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void mockRepoShouldUpdateGroup() {
        try {
            mockRepo.updateGroup(fakeGroup.getObjectId(), fakeGroup);
            verify(mockRestClient).updateEntry(GROUPS, fakeGroup.getObjectId(), fakeGroup);
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
    public void mockRepoShouldCreateOAuth2PermissionGrant() {
        OAuth2PermissionGrant entry = new OAuth2PermissionGrant();
        try {
            String oid = mockRepo.createOAuth2PermissionGrant(entry);
            assertEquals(oid, fakePermissionGrant.getObjectId());
            verify(mockRestClient).createEntry(OAuth2PermissionGrant.class, OAUTH_2_PERMISSION_GRANTS, entry);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void mockRepoShouldUpdateOAuth2PermissionGrant() {
        try {
            mockRepo.updateOAuth2PermissionGrant(fakePermissionGrant.getObjectId(), fakePermissionGrant);
            verify(mockRestClient).updateEntry(OAUTH_2_PERMISSION_GRANTS, fakePermissionGrant.getObjectId(), fakePermissionGrant);
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
    public void mockRepoShouldCreateServicePrincipal() {
        ServicePrincipal entry = new ServicePrincipal();
        try {
            String oid = mockRepo.createServicePrincipal(entry);
            assertEquals(oid, fakeServicePrincipal.getObjectId());
            verify(mockRestClient).createEntry(ServicePrincipal.class, SERVICE_PRINCIPALS, entry);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void mockRepoShouldUpdateServicePrincipal() {
        try {
            mockRepo.updateServicePrincipal(fakeServicePrincipal.getObjectId(), fakeServicePrincipal);
            verify(mockRestClient).updateEntry(SERVICE_PRINCIPALS, fakeServicePrincipal.getObjectId(), fakeServicePrincipal);
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
    public void mockRepoShouldUpdateTenantDetail() {
        try {
            mockRepo.updateTenantDetail(fakeTenantDetail.getObjectId(), fakeTenantDetail);
            verify(mockRestClient).updateEntry(TENANT_DETAILS, fakeTenantDetail.getObjectId(), fakeTenantDetail);
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

    @Test
    @Category(UnitTest.class)
    public void mockRepoShouldCreateUser() {
        User entry = new User();
        try {
            String oid = mockRepo.createUser(entry);
            assertEquals(oid, fakeUser.getObjectId());
            verify(mockRestClient).createEntry(User.class, USERS, entry);
        } catch (IOException |
                URISyntaxException |
                AuthenticationException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void mockRepoShouldUpdateUser() {
        try {
            mockRepo.updateUser(fakeUser.getObjectId(), fakeUser);
            verify(mockRestClient).updateEntry(USERS, fakeUser.getObjectId(), fakeUser);
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

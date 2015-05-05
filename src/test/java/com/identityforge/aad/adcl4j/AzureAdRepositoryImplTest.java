package com.identityforge.aad.adcl4j;

import com.identityforge.aad.adcl4j.model.entity.*;
import com.identityforge.categories.IntegrationTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Properties;

import static org.junit.Assert.*;

/**
 * Created by nwoolls on 5/4/15.
 */
public class AzureAdRepositoryImplTest {

    private AzureAdRepository realRepo;
    private Properties props;

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

        realRepo = new AzureAdRepositoryImpl(
                props.getProperty("clientId"),
                props.getProperty("username"),
                props.getProperty("password"),
                props.getProperty("tenantDomain"));
    }

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

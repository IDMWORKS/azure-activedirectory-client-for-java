package com.identityforge.aad.adcl4j.model.entity;

/**
 * Created by nwoolls on 4/2/15.
 */
public class DirectoryRole extends Entry {

    public static final String PLURAL_NAME = "directoryRoles";

    public String description;
    public Boolean isSystem;
    public Boolean roleDisabled;
    public String roleTemplateId;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isSystem() {
        return isSystem;
    }

    public void setIsSystem(Boolean isSystem) {
        this.isSystem = isSystem;
    }

    public Boolean isRoleDisabled() {
        return roleDisabled;
    }

    public void setRoleDisabled(Boolean roleDisabled) {
        this.roleDisabled = roleDisabled;
    }

    public String getRoleTemplateId() {
        return roleTemplateId;
    }

    public void setRoleTemplateId(String roleTemplateId) {
        this.roleTemplateId = roleTemplateId;
    }
}

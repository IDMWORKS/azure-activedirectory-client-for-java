package com.identityforge.aad.adcl4j.model.entity;

/**
 * Created by nwoolls on 4/2/15.
 */
public class Entry {
    private String displayName;
    private String objectId;
    private String objectType;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }
}

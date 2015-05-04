package com.identityforge.aad.adcl4j.model.entity;

import java.util.Collection;
import java.util.Date;

/**
 * Created by nwoolls on 4/2/15.
 */
public class Device extends Entry {

    public static final String PLURAL_NAME = "devices";

    public Boolean accountEnabled;
    public Collection<Object> alternativeSecurityIds;
    public Date approximateLastLogonTimeStamp;
    public String deviceId;
    public Integer deviceObjectVersion;
    public String deviceOSType;
    public String deviceOSVersion;
    public Collection<String> devicePhysicalIds;
    public Boolean dirSyncEnabled;
    public Date lastDirSyncTime;

    public Boolean isAccountEnabled() {
        return accountEnabled;/**/
    }

    public void setAccountEnabled(Boolean accountEnabled) {
        this.accountEnabled = accountEnabled;
    }

    public Collection<Object> getAlternativeSecurityIds() {
        return alternativeSecurityIds;
    }

    public void setAlternativeSecurityIds(Collection<Object> alternativeSecurityIds) {
        this.alternativeSecurityIds = alternativeSecurityIds;
    }

    public Date getApproximateLastLogonTimeStamp() {
        return approximateLastLogonTimeStamp;
    }

    public void setApproximateLastLogonTimeStamp(Date approximateLastLogonTimeStamp) {
        this.approximateLastLogonTimeStamp = approximateLastLogonTimeStamp;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getDeviceObjectVersion() {
        return deviceObjectVersion;
    }

    public void setDeviceObjectVersion(Integer deviceObjectVersion) {
        this.deviceObjectVersion = deviceObjectVersion;
    }

    public String getDeviceOSType() {
        return deviceOSType;
    }

    public void setDeviceOSType(String deviceOSType) {
        this.deviceOSType = deviceOSType;
    }

    public String getDeviceOSVersion() {
        return deviceOSVersion;
    }

    public void setDeviceOSVersion(String deviceOSVersion) {
        this.deviceOSVersion = deviceOSVersion;
    }

    public Collection<String> getDevicePhysicalIds() {
        return devicePhysicalIds;
    }

    public void setDevicePhysicalIds(Collection<String> devicePhysicalIds) {
        this.devicePhysicalIds = devicePhysicalIds;
    }

    public Boolean isDirSyncEnabled() {
        return dirSyncEnabled;
    }

    public void setDirSyncEnabled(Boolean dirSyncEnabled) {
        this.dirSyncEnabled = dirSyncEnabled;
    }

    public Date getLastDirSyncTime() {
        return lastDirSyncTime;
    }

    public void setLastDirSyncTime(Date lastDirSyncTime) {
        this.lastDirSyncTime = lastDirSyncTime;
    }
}

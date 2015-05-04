package com.identityforge.aad.adcl4j.model.entity;

import java.util.Collection;

/**
 * Created by nwoolls on 4/4/15.
 */
public class SubscribedSku {

    public static final String PLURAL_NAME = "subscribedSkus";

    private String capabilityStatus;
    private Integer consumedUnits;
    private String objectId;
    private Object prepaidUnits;
    private Collection<Object> servicePlans;
    private String skuId;
    private String skuPartNumber;

    public String getCapabilityStatus() {
        return capabilityStatus;
    }

    public void setCapabilityStatus(String capabilityStatus) {
        this.capabilityStatus = capabilityStatus;
    }

    public Integer getConsumedUnits() {
        return consumedUnits;
    }

    public void setConsumedUnits(Integer consumedUnits) {
        this.consumedUnits = consumedUnits;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Object getPrepaidUnits() {
        return prepaidUnits;
    }

    public void setPrepaidUnits(Object prepaidUnits) {
        this.prepaidUnits = prepaidUnits;
    }

    public Collection<Object> getServicePlans() {
        return servicePlans;
    }

    public void setServicePlans(Collection<Object> servicePlans) {
        this.servicePlans = servicePlans;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSkuPartNumber() {
        return skuPartNumber;
    }

    public void setSkuPartNumber(String skuPartNumber) {
        this.skuPartNumber = skuPartNumber;
    }
}

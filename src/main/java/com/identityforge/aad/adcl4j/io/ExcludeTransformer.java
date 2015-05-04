package com.identityforge.aad.adcl4j.io;

import flexjson.transformer.AbstractTransformer;

/**
 * Created by nwoolls on 4/30/15.
 */
public class ExcludeTransformer extends AbstractTransformer {

    @Override
    public Boolean isInline() {
        return true;
    }

    @Override
    public void transform(Object object) {
        // Do nothing, null objects are not serialized.
        return;
    }
}

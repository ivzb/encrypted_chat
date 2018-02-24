package com.ivzb.encrypted_chat._base.data.seed;

import com.ivzb.encrypted_chat._base.data._contracts.generators.BaseGeneratorConfig;
import com.ivzb.encrypted_chat._base.data._contracts.seed.BaseSeed;
import com.ivzb.encrypted_chat._base.data.generators.DefaultGeneratorConfig;

public class DefaultSeed implements BaseSeed {

    private final BaseGeneratorConfig mConfig;

    public DefaultSeed(BaseGeneratorConfig config) {
        mConfig = DefaultGeneratorConfig.getInstance();
    }

    @Override
    public void seed() {
        // todo
    }
}
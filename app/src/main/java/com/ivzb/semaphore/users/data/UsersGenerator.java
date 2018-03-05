package com.ivzb.semaphore.users.data;

import com.ivzb.semaphore._base.data._contracts.generators.BaseGeneratorConfig;
import com.ivzb.semaphore._base.data.generators.DefaultGenerator;

public class UsersGenerator
        extends DefaultGenerator<UserEntity> {

    public UsersGenerator(BaseGeneratorConfig config) {
        super(config);
    }

    @Override
    public UserEntity instantiate() {
        String id = mConfig.getId();
        String email = mConfig.getEmail();
        boolean isFriend = mConfig.getBoolean();

        return new UserEntity(id, email, isFriend);
    }
}

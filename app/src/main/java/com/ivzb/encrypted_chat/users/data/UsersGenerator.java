package com.ivzb.encrypted_chat.users.data;

import com.ivzb.encrypted_chat._base.data._contracts.generators.BaseGeneratorConfig;
import com.ivzb.encrypted_chat._base.data.generators.DefaultGenerator;

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
        boolean isPending = isFriend && mConfig.getBoolean();

        return new UserEntity(id, email, isFriend, isPending);
    }
}

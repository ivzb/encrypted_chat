package com.ivzb.encrypted_chat.users.data;

import com.ivzb.encrypted_chat._base.data.generators.DefaultGeneratorConfig;
import com.ivzb.encrypted_chat._base.data.sources.mocks.MockReceiveDataSource;

public class UsersMockDataSource
        extends MockReceiveDataSource<UserEntity>
        implements UsersDataSource {

    private static UsersDataSource sINSTANCE;

    public static UsersDataSource getInstance() {
        if (sINSTANCE == null) {
            sINSTANCE = new UsersMockDataSource();
        }

        return sINSTANCE;
    }

    public static void destroyInstance() {
        sINSTANCE = null;
    }

    private UsersMockDataSource() {
        super(new UsersGenerator(DefaultGeneratorConfig.getInstance()));
    }
}
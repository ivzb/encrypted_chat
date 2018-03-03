package com.ivzb.encrypted_chat.users.data;

import com.ivzb.encrypted_chat._base.data.Result;
import com.ivzb.encrypted_chat._base.data._contracts.generators.BaseGeneratorConfig;
import com.ivzb.encrypted_chat._base.data.callbacks.GetCallback;
import com.ivzb.encrypted_chat._base.data.callbacks.SaveCallback;
import com.ivzb.encrypted_chat._base.data.generators.DefaultGeneratorConfig;
import com.ivzb.encrypted_chat._base.data.sources.mocks.MockReceiveDataSource;

import static com.ivzb.encrypted_chat.utils.Preconditions.checkNotNull;

public class UsersMockDataSource
        extends MockReceiveDataSource<UserEntity>
        implements UsersDataSource {

    private static UsersDataSource sINSTANCE;
    private BaseGeneratorConfig mGeneratorConfig;

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

        mGeneratorConfig = DefaultGeneratorConfig.getInstance();
    }

    @Override
    public void add(String id, SaveCallback<Boolean> callback) {
        checkNotNull(callback);

        Boolean added = mGeneratorConfig.getBoolean();
        String message = added ? "User added." : "Couldn't add user.";

        Result<Boolean> result = new Result<>(added, message);
        callback.onSuccess(result);
    }

    @Override
    public void remove(String id, SaveCallback<Boolean> callback) {
        checkNotNull(callback);

        Boolean added = mGeneratorConfig.getBoolean();
        String message = added ? "User removed." : "Couldn't remove user.";

        Result<Boolean> result = new Result<>(added, message);
        callback.onSuccess(result);
    }
}
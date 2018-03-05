package com.ivzb.semaphore.users.data;

import com.ivzb.semaphore._base.data.Result;
import com.ivzb.semaphore._base.data._contracts.generators.BaseGeneratorConfig;
import com.ivzb.semaphore._base.data.callbacks.SaveCallback;
import com.ivzb.semaphore._base.data.generators.DefaultGeneratorConfig;
import com.ivzb.semaphore._base.data.sources.mocks.MockLoadDataSource;

import static com.ivzb.semaphore.utils.Preconditions.checkNotNull;

public class UsersMockDataSource
        extends MockLoadDataSource<UserEntity>
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
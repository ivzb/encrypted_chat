package com.ivzb.encrypted_chat.auth.data;

import com.ivzb.encrypted_chat._base.data.Result;
import com.ivzb.encrypted_chat._base.data._contracts.generators.BaseGeneratorConfig;
import com.ivzb.encrypted_chat._base.data.callbacks.SaveCallback;
import com.ivzb.encrypted_chat._base.data.generators.DefaultGeneratorConfig;

import static com.ivzb.encrypted_chat.utils.Preconditions.checkNotNull;

public class AuthMockDataSource implements AuthDataSource {

    private static AuthDataSource sINSTANCE;
    private BaseGeneratorConfig mConfig;

    public static AuthDataSource getInstance() {
        if (sINSTANCE == null) {
            sINSTANCE = new AuthMockDataSource();
        }

        return sINSTANCE;
    }

    public static void destroyInstance() {
        sINSTANCE = null;
    }

    private AuthMockDataSource() {
        mConfig = DefaultGeneratorConfig.getInstance();
    }

    @Override
    public void login(
            AuthEntity auth,
            SaveCallback<String> callback) {

        checkNotNull(callback);

        boolean emailMatches = mConfig.getEmail().equals(auth.getEmail());
        boolean passwordMatches = mConfig.getPassword().equals(auth.getPassword());

        if (emailMatches && passwordMatches) {
            String token = mConfig.getAuthenticationToken();
            String message = "auth_token created";

            Result<String> result = new Result<>(token, message);
            callback.onSuccess(result);
            return;
        }

        callback.onFailure("Wrong email or password.");
    }

    @Override
    public void register(AuthEntity auth, SaveCallback<String> callback) {
        String token = mConfig.getAuthenticationToken();
        String message = "user created";

        Result<String> result = new Result<>(token, message);
        callback.onSuccess(result);
    }
}

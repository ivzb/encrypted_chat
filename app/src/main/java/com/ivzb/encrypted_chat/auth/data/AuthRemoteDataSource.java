package com.ivzb.encrypted_chat.auth.data;

import android.support.annotation.NonNull;

import com.ivzb.encrypted_chat._base.data.Result;
import com.ivzb.encrypted_chat._base.data.callbacks.SaveCallback;
import com.ivzb.encrypted_chat._base.data.sources.DefaultRemoteDataSource;

import retrofit2.Call;

public class AuthRemoteDataSource
        extends DefaultRemoteDataSource<AuthEntity, AuthAPI>
        implements AuthDataSource {

    private static AuthDataSource sINSTANCE;

    public static AuthDataSource getInstance() {
        if (sINSTANCE == null) {
            sINSTANCE = new AuthRemoteDataSource();
        }

        return sINSTANCE;
    }

    public static void destroyInstance() {
        sINSTANCE = null;
    }

    // Prevent direct instantiation.
    private AuthRemoteDataSource() {
        super(AuthAPI.class);
    }

    @Override
    public void login(
            final @NonNull AuthEntity auth,
            final @NonNull SaveCallback<String> callback) {

        final Call<Result<String>> call = mApiService.auth(auth);
        call.enqueue(saveCallback(callback));
    }

    @Override
    public void register(
            final @NonNull AuthEntity auth,
            final @NonNull SaveCallback<String> callback) {

        final Call<Result<String>> call = mApiService.create(auth);
        call.enqueue(saveCallback(callback));
    }
}
package com.ivzb.semaphore.auth.data;

import com.ivzb.semaphore._base.data.callbacks.SaveCallback;

public interface AuthDataSource {

    void login(
            AuthEntity auth,
            SaveCallback<String> callback);

    void register(
            AuthEntity auth,
            SaveCallback<String> callback);
}
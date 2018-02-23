package com.ivzb.encrypted_chat.auth.data;

import com.ivzb.encrypted_chat._base.data.callbacks.SaveCallback;

public interface AuthDataSource {

    void login(
            AuthEntity auth,
            SaveCallback<String> callback);

    void register(
            AuthEntity auth,
            SaveCallback<String> callback);
}
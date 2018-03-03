package com.ivzb.encrypted_chat.users.data;

import com.ivzb.encrypted_chat._base.data._contracts.sources.ReceiveDataSource;
import com.ivzb.encrypted_chat._base.data.callbacks.GetCallback;
import com.ivzb.encrypted_chat._base.data.callbacks.SaveCallback;

public interface UsersDataSource extends ReceiveDataSource<UserEntity> {

    void add(String id, SaveCallback<Boolean> callback);
    void remove(String id, SaveCallback<Boolean> callback);
}
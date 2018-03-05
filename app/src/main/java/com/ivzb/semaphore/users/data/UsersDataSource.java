package com.ivzb.semaphore.users.data;

import com.ivzb.semaphore._base.data._contracts.sources.ReceiveDataSource;
import com.ivzb.semaphore._base.data.callbacks.SaveCallback;

public interface UsersDataSource extends ReceiveDataSource<UserEntity> {

    void add(String id, SaveCallback<Boolean> callback);
    void remove(String id, SaveCallback<Boolean> callback);
}
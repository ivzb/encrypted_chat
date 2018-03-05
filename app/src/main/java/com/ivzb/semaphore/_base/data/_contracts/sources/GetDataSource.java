package com.ivzb.semaphore._base.data._contracts.sources;

import com.ivzb.semaphore._base.data.callbacks.GetCallback;

public interface GetDataSource<T> {

    void get(String id, GetCallback<T> callback);
}
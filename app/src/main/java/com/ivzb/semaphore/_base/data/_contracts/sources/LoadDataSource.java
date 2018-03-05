package com.ivzb.semaphore._base.data._contracts.sources;

import com.ivzb.semaphore._base.data.callbacks.LoadCallback;

public interface LoadDataSource<T> {

    void load(String id, int page, LoadCallback<T> callback);
}
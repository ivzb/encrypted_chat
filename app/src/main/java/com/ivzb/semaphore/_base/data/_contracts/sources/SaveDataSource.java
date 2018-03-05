package com.ivzb.semaphore._base.data._contracts.sources;

import com.ivzb.semaphore._base.data.callbacks.SaveCallback;

public interface SaveDataSource<T> {

    void save(T entity, final SaveCallback<String> callback);
}
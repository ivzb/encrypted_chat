package com.ivzb.encrypted_chat._base.data._contracts.sources;

import com.ivzb.encrypted_chat._base.data.callbacks.SaveCallback;

public interface SaveDataSource<T> {

    void save(T entity, final SaveCallback<String> callback);
}
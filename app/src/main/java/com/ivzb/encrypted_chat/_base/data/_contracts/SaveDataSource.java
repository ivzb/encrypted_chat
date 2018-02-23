package com.ivzb.encrypted_chat._base.data._contracts;

import com.ivzb.encrypted_chat._base.data.callbacks.SaveCallback;

public interface SaveDataSource<T> {

    void save(T entity, final SaveCallback<String> callback);
}
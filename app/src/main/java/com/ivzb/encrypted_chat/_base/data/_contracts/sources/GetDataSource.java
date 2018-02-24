package com.ivzb.encrypted_chat._base.data._contracts.sources;

import com.ivzb.encrypted_chat._base.data.callbacks.GetCallback;

public interface GetDataSource<T> {

    void get(String id, GetCallback<T> callback);
}
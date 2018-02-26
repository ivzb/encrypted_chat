package com.ivzb.encrypted_chat._base.data._contracts.sources;

import com.ivzb.encrypted_chat._base.data.callbacks.LoadCallback;

public interface LoadDataSource<T> {

    void load(String id, int page, LoadCallback<T> callback);
}
package com.ivzb.encrypted_chat._base.data.callbacks;

import com.ivzb.encrypted_chat._base.data.Result;

import java.util.List;

public interface LoadCallback<T> {

    void onSuccess(Result<List<T>> data, int page);
    void onNoMore();
    void onFailure(String message);
}

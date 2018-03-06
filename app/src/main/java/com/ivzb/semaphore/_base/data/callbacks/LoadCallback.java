package com.ivzb.semaphore._base.data.callbacks;

import com.ivzb.semaphore._base.data.Result;

import java.util.List;

public interface LoadCallback<T> {

    void onSuccess(Result<List<T>> data);
    void onNoMore();
    void onFailure(String message);
}

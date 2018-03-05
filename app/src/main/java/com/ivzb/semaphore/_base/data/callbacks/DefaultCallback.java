package com.ivzb.semaphore._base.data.callbacks;

public interface DefaultCallback<T> {

    void onSuccess(T data);
    void onFailure(String message);
}

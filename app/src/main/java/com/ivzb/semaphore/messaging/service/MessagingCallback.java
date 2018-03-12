package com.ivzb.semaphore.messaging.service;

import okhttp3.Response;

public interface MessagingCallback {

    void onMessage(String message);
    void onClosing(int code, String reason);
    void onFailure(Throwable t, Response response);
}

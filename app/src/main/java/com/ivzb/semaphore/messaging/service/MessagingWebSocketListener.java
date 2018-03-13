package com.ivzb.semaphore.messaging.service;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.ivzb.semaphore._base.data._contracts.generators.BaseGeneratorConfig;
import com.ivzb.semaphore._base.data.generators.DefaultGeneratorConfig;

import java.util.concurrent.atomic.AtomicInteger;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

class MessagingWebSocketListener extends WebSocketListener {

    private static final int NORMAL_CLOSURE_STATUS = 1000;

    private final Context mContext;
    private final MessagingCallback mCallback;

    public MessagingWebSocketListener(Context context, MessagingCallback callback) {
        mContext = context;
        mCallback = callback;
    }

    @Override
    public void onOpen(final WebSocket webSocket, Response response) {
        final BaseGeneratorConfig config = DefaultGeneratorConfig.getInstance();
        final AtomicInteger ai = new AtomicInteger();

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    while (ai.incrementAndGet() < 30) {
                        String message = TextUtils.join(" ", config.getWords(config.getNumber(15)));
                        webSocket.send(message);
                        int timeout = config.getNumber(1500);
                        sleep(timeout);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Toast.makeText(mContext, "messaging web socket error", Toast.LENGTH_LONG).show();
                }
            }
        };

        thread.start();
//        webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye !");
    }

    @Override
    public void onMessage(WebSocket webSocket, String message) {
        mCallback.onMessage(message);
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        mCallback.onMessage(bytes.toString());
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        webSocket.close(NORMAL_CLOSURE_STATUS, null);
        mCallback.onClosing(code, reason);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        mCallback.onFailure(t, response);
    }
}

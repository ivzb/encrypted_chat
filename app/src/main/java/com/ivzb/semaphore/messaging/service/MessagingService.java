package com.ivzb.semaphore.messaging.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.ivzb.semaphore.R;
import com.ivzb.semaphore.conversation.ui.ConversationActivity;
import com.ivzb.semaphore.home.ui.HomeActivity;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;

public class MessagingService extends Service {

    public static String MESSAGE_ACTION = "com.ivzb.semaphore.conversations.service.action.main";
    public static String EXTRA_MESSAGE = "extra_message";

    public static int MESSAGE_NOTIFICATION = 101;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        listenForMessages();

        Log.i("Semaphore", "In onStartCommand");
        Toast.makeText(this, "Service Started!", Toast.LENGTH_SHORT).show();

        return START_STICKY;
    }

    // todo: https://developer.android.com/training/notify-user/build-notification.html#Actions
    private void showNotification(String title, String message) {
        if (true) return;

        Intent notificationIntent = new Intent(this, HomeActivity.class);
        notificationIntent.setAction(MESSAGE_ACTION);
        notificationIntent.setFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                notificationIntent,
                0);

        Intent replyIntent = new Intent(this, ConversationActivity.class);
        PendingIntent pendingReplyIntent = PendingIntent.getService(
                this,
                0,
                replyIntent,
                0);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle(getResources().getString(R.string.app_name))
                .setTicker(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_message)
                .setContentIntent(pendingIntent)
                .setOngoing(true)
                .addAction(R.drawable.ic_reply, "Reply", pendingReplyIntent)
                .setAutoCancel(true)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

// notificationId is a unique int for each notification that you must define
        notificationManager.notify(MESSAGE_NOTIFICATION, notification);
    }

    private void listenForMessages() {
        Request request = new Request.Builder().url("ws://echo.websocket.org").build();
        OkHttpClient client = new OkHttpClient();
        MessagingWebSocketListener listener = new MessagingWebSocketListener(this, mCallback);

        WebSocket ws = client.newWebSocket(request, listener);
        client.dispatcher().executorService().shutdown();
    }

    private MessagingCallback mCallback = new MessagingCallback() {
        @Override
        public void onMessage(String message) {
            showNotification("some title here", message);

            Intent intent = new Intent(MESSAGE_ACTION);
            // You can also include some extra data.
            intent.putExtra(EXTRA_MESSAGE, message);
            LocalBroadcastManager.getInstance(MessagingService.this).sendBroadcast(intent);
        }

        @Override
        public void onClosing(int code, String reason) {
            showNotification("close here", "closing");
        }

        @Override
        public void onFailure(Throwable t, Response response) {
            showNotification("couldn't send message", "error here");
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Semaphore", "In onDestroy");
        Toast.makeText(this, "Service Detroyed!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Used only in case if services are bound (Bound Services).
        return null;
    }
}

package com.ivzb.semaphore.splash.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ivzb.semaphore.conversation.ui.ConversationActivity;
import com.ivzb.semaphore.messaging.service.MessagingService;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent serviceIntent = new Intent(this, MessagingService.class);
        startService(serviceIntent);

        Intent activityIntent = new Intent(this, ConversationActivity.class);
        startActivity(activityIntent);
        finish();
    }
}
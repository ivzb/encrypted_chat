package com.ivzb.semaphore.splash.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ivzb.semaphore.conversation.ui.ConversationActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Intent intent = new Intent(this, HomeActivity.class);
        Intent intent = new Intent(this, ConversationActivity.class);
        startActivity(intent);
        finish();
    }
}
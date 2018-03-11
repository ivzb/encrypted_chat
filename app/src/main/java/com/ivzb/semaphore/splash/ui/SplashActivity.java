package com.ivzb.semaphore.splash.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ivzb.semaphore.auth.ui.AuthActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, AuthActivity.class);
//        Intent intent = new Intent(this, ConversationActivity.class);
        startActivity(intent);
        finish();
    }
}
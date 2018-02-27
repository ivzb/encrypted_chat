package com.ivzb.encrypted_chat.splash.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ivzb.encrypted_chat.auth.ui.AuthActivity;
import com.ivzb.encrypted_chat.user_search.ui.UserSearchActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, UserSearchActivity.class);
        startActivity(intent);
        finish();
    }
}
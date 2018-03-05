package com.ivzb.encrypted_chat.conversation.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.ivzb.encrypted_chat.R;
import com.ivzb.encrypted_chat._base.data.DataSources;
import com.ivzb.encrypted_chat._base.ui.DefaultActivity;
import com.ivzb.encrypted_chat.utils.ActivityUtils;

public class ConversationActivity extends DefaultActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation_act);

        // Set up the toolbar.
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle(R.string.search_user);
        }

        ConversationView view =
                (ConversationView) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (view == null) {
            view = new ConversationView();

            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(),
                    view,
                    R.id.contentFrame);
        }

        view.setViewModel(new ConversationViewModel());
        view.setPresenter(new ConversationPresenter(
                this,
                view,
                DataSources.getInstance().conversations()));
    }
}


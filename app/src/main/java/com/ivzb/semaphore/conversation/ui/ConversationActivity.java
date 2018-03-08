package com.ivzb.semaphore.conversation.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.ivzb.semaphore.R;
import com.ivzb.semaphore._base.data.DataSources;
import com.ivzb.semaphore._base.ui.DefaultActivity;
import com.ivzb.semaphore.utils.ActivityUtils;

import static com.ivzb.semaphore._base.data.config.DefaultConfig.NO_ID;

public class ConversationActivity extends DefaultActivity {

    public static final String EXTRA_CONVERSATION_ID = "EXTRA_CONVERSATION_ID";

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
            actionBar.setTitle(R.string.conversation);
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

        Bundle extras = getIntent().getExtras();
        String conversationId = NO_ID;

        if (extras != null && extras.containsKey(EXTRA_CONVERSATION_ID)) {
            conversationId = extras.getString(EXTRA_CONVERSATION_ID, NO_ID);
        }

        view.setViewModel(new ConversationViewModel(conversationId));
        view.setPresenter(new ConversationPresenter(
                this,
                view,
                DataSources.getInstance().messages()));
    }
}


package com.ivzb.semaphore.user_search.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.ivzb.semaphore.R;
import com.ivzb.semaphore._base.data.DataSources;
import com.ivzb.semaphore._base.ui.DefaultActivity;
import com.ivzb.semaphore.utils.ActivityUtils;

public class UserSearchActivity extends DefaultActivity {

    public static final int REQUEST_ADD_USER = 128;
    public static final String MESSAGE_EXTRA = "message_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_search_act);

        // Set up the toolbar.
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle(R.string.search_user);
        }

        UserSearchView view =
                (UserSearchView) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (view == null) {
            view = new UserSearchView();

            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(),
                    view,
                    R.id.contentFrame);
        }

        view.setViewModel(new UserSearchViewModel());
        view.setPresenter(new UserSearchPresenter(
                this,
                view,
                DataSources.getInstance().users()));
    }
}
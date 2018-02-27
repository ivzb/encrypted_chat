package com.ivzb.encrypted_chat.user_search.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.ivzb.encrypted_chat.R;
import com.ivzb.encrypted_chat._base.data.DataSources;
import com.ivzb.encrypted_chat._base.ui.DefaultActivity;
import com.ivzb.encrypted_chat.utils.ActivityUtils;

public class UserSearchActivity extends DefaultActivity {

    public static final int REQUEST_ADD_USER = 156;

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

        SearchUserView view =
                (SearchUserView) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (view == null) {
            view = new SearchUserView();

            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(),
                    view,
                    R.id.contentFrame);
        }

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            view.setArguments(extras);
        }

        view.setViewModel(new SearchUserViewModel());
        view.setPresenter(new SearchUserPresenter(
                this,
                view,
                DataSources.getInstance().users()));
    }
}
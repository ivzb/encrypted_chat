package com.ivzb.encrypted_chat.add_user.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.ivzb.encrypted_chat.R;
import com.ivzb.encrypted_chat._base.data.DataSources;
import com.ivzb.encrypted_chat._base.ui.DefaultActivity;
import com.ivzb.encrypted_chat.utils.ActivityUtils;

public class AddUserActivity extends DefaultActivity {

    public static final int REQUEST_ADD_USER = 156;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user_act);

        // Set up the toolbar.
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle(R.string.add_user);
        }

        AddUserView view =
                (AddUserView) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (view == null) {
            view = new AddUserView();

            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(),
                    view,
                    R.id.contentFrame);
        }

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            view.setArguments(extras);
        }

        view.setViewModel(new AddUserViewModel());
        view.setPresenter(new AddUserPresenter(
                this,
                view,
                DataSources.getInstance().users()));
    }
}
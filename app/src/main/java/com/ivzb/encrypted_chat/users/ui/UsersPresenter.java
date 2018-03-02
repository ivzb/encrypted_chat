package com.ivzb.encrypted_chat.users.ui;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ivzb.encrypted_chat._base.ui.endless.DefaultEndlessScrollPresenter;
import com.ivzb.encrypted_chat.users.data.UserEntity;
import com.ivzb.encrypted_chat.users.data.UsersDataSource;

public class UsersPresenter
        extends DefaultEndlessScrollPresenter<UserEntity, UsersContract.View, UsersDataSource>
        implements UsersContract.Presenter {

    UsersPresenter(
            @NonNull Context context,
            @NonNull UsersContract.View view,
            @NonNull UsersDataSource dataSource) {

        super(context, view, dataSource);
    }

    public void result(int requestCode, int resultCode) {
        // todo
    }
}
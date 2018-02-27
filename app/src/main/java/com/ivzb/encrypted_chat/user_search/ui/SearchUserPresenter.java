package com.ivzb.encrypted_chat.user_search.ui;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ivzb.encrypted_chat._base.ui.DefaultPresenter;
import com.ivzb.encrypted_chat._base.ui.presenters.DefaultEndlessAdapterPresenter;
import com.ivzb.encrypted_chat.users.data.UserEntity;
import com.ivzb.encrypted_chat.users.data.UsersDataSource;

import static com.ivzb.encrypted_chat.utils.Preconditions.checkNotNull;

public class SearchUserPresenter
        extends DefaultEndlessAdapterPresenter<UserEntity, UserSearchContract.View, UsersDataSource>
        implements UserSearchContract.Presenter {

    SearchUserPresenter(
            @NonNull Context context,
            @NonNull UserSearchContract.View view,
            @NonNull UsersDataSource dataSource) {

        super(context, view, dataSource);
    }

    @Override
    public void start() {

    }

    @Override
    public void clickUser(UserEntity user) {
        // todo
    }
}

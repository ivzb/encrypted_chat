package com.ivzb.encrypted_chat.add_user.ui;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ivzb.encrypted_chat._base.ui.DefaultPresenter;
import com.ivzb.encrypted_chat.users.data.UsersDataSource;

import static com.ivzb.encrypted_chat.utils.Preconditions.checkNotNull;

public class AddUserPresenter
        extends DefaultPresenter<AddUserContract.View>
        implements AddUserContract.Presenter {

    private UsersDataSource mDataSource;

    AddUserPresenter(
            @NonNull Context context,
            @NonNull AddUserContract.View view,
            @NonNull UsersDataSource dataSource) {

        mContext = checkNotNull(context, "context cannot be null");
        mView = checkNotNull(view, "view cannot be null");
        mDataSource = checkNotNull(dataSource, "dataSource cannot be null");
    }

    @Override
    public void start() {

    }

    @Override
    public void saveUser(String email) {
        // todo
    }
}

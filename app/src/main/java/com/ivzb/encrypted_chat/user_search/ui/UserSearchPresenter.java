package com.ivzb.encrypted_chat.user_search.ui;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ivzb.encrypted_chat._base.data.Result;
import com.ivzb.encrypted_chat._base.data.callbacks.SaveCallback;
import com.ivzb.encrypted_chat._base.ui.endless.DefaultEndlessScrollPresenter;
import com.ivzb.encrypted_chat.users.data.UserEntity;
import com.ivzb.encrypted_chat.users.data.UsersDataSource;

public class UserSearchPresenter
        extends DefaultEndlessScrollPresenter<UserEntity, UserSearchContract.View, UsersDataSource>
        implements UserSearchContract.Presenter {

    UserSearchPresenter(
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

    @Override
    public void clickAddUser(UserEntity user) {
        if (!mView.isActive()) return;

        mView.setLoadingIndicator(true);

        mDataSource.add(user.getId(), mUserSaveCallback);
    }

    @Override
    public void clickRemoveUser(UserEntity user) {
        if (!mView.isActive()) return;

        mView.setLoadingIndicator(true);

        mDataSource.remove(user.getId(), mUserSaveCallback);
    }

    @Override
    public void clickErrorMessage() {
        if (!mView.isActive()) return;

        mView.showErrorMessage(false);
        mView.setErrorMessage("");
    }

    @Override
    public void searchUser(String email) {
        if (!mView.isActive()) return;

        refresh(email);
    }

    private SaveCallback<Boolean> mUserSaveCallback = new SaveCallback<Boolean>() {
        @Override
        public void onSuccess(Result<Boolean> result) {
            if (!mView.isActive()) return;

            mView.setLoadingIndicator(false);

            Boolean added = result.getResults();

            if (!added) {
                mView.setErrorMessage(result.getMessage());
                mView.showErrorMessage(true);

                return;
            }

            mView.finish(result.getMessage());
        }

        @Override
        public void onFailure(String message) {
            if (!mView.isActive()) return;

            mView.setLoadingIndicator(false);

            mView.setErrorMessage(message);
            mView.showErrorMessage(true);
        }
    };
}

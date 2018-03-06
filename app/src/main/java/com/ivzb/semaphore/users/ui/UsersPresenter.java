package com.ivzb.semaphore.users.ui;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ivzb.semaphore._base.data.Result;
import com.ivzb.semaphore._base.data.callbacks.SaveCallback;
import com.ivzb.semaphore._base.ui.endless.DefaultEndlessScrollPresenter;
import com.ivzb.semaphore.users.data.UserEntity;
import com.ivzb.semaphore.users.data.UsersDataSource;

public class UsersPresenter
        extends DefaultEndlessScrollPresenter<UserEntity, UsersContract.View, UsersDataSource>
        implements UsersContract.Presenter {

    protected static final int REQUEST_USER_SAVE = 301;
    protected static final int RESULT_OK = -1;

    public UsersPresenter(
            @NonNull Context context,
            @NonNull UsersContract.View view,
            @NonNull UsersDataSource dataSource) {

        super(context, view, dataSource);
    }

    @Override
    public void result(int requestCode, int resultCode, String message) {
        if (requestCode == REQUEST_USER_SAVE && resultCode == RESULT_OK) {
            mView.setSuccessMessage(message);
            mView.showSuccessMessage(true);
        }
    }

    @Override
    public void clickUser(UserEntity user) {
        if (!mView.isActive()) return;

        if (user == null) {
            mView.setErrorMessage("Missing user.");
            mView.showErrorMessage(true);
            return;
        }

        mView.openConversation(user);
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

            result(REQUEST_USER_SAVE, RESULT_OK, result.getMessage());
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
package com.ivzb.semaphore.auth.ui;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ivzb.semaphore._base.data.Result;
import com.ivzb.semaphore._base.data.callbacks.SaveCallback;
import com.ivzb.semaphore._base.ui.DefaultPresenter;
import com.ivzb.semaphore.auth.data.AuthDataSource;
import com.ivzb.semaphore.auth.data.AuthEntity;

import static com.ivzb.semaphore.utils.Preconditions.checkNotNull;

public class AuthPresenter
        extends DefaultPresenter<AuthContract.View>
        implements AuthContract.Presenter {

    private final AuthDataSource mDataSource;

    AuthPresenter(
            @NonNull Context context,
            @NonNull AuthContract.View view,
            @NonNull AuthDataSource dataSource) {

        super(context, view);
        mDataSource = checkNotNull(dataSource, "dataSource cannot be null");
    }

    @Override
    public void start() {

    }

    @Override
    public void login(AuthEntity auth) {
        if (!mView.isActive()) return;

        mView.hideErrorMessage();
        mView.showLoading(true);

        // todo: append validation

        mDataSource.login(auth, mAuthCallback);
    }

    @Override
    public void register(AuthEntity auth) {
        if (!mView.isActive()) return;

        mView.hideErrorMessage();
        mView.showLoading(true);

        // todo: append validation

        mDataSource.register(auth, mAuthCallback);
    }

    private SaveCallback<String> mAuthCallback = new SaveCallback<String>() {
        @Override
        public void onSuccess(Result<String> data) {
            if (!mView.isActive()) return;

            mView.navigateToHome();
            mView.showLoading(false);
        }

        @Override
        public void onFailure(String message) {
            if (!mView.isActive()) return;

            mView.showLoading(false);
            mView.setErrorMessage(message);
            mView.showErrorMessage(true);
        }
    };
}
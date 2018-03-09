package com.ivzb.semaphore.auth.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ivzb.semaphore.R;
import com.ivzb.semaphore._base.ui.DefaultViewModel;
import com.ivzb.semaphore._base.ui._contracts.BaseViewModel;

import static com.ivzb.semaphore.utils.Preconditions.checkNotNull;

public class AuthViewModel
        extends DefaultViewModel
        implements AuthContract.ViewModel {

    private static final String EMAIL_STATE = "email_state";

    private EditText mEtEmail;
    private EditText mEtPassword;

    private CardView mCvError;
    private TextView mTvError;

    private Button mBtnLogin;
    private Button mBtnRegister;

    private ProgressBar mPbLoading;

    private String mEmail;
    private String mPassword;

    @Override
    public Builder builder(Context context) {
        return new AuthViewModel.Builder(context);
    }

    private void initViews(View view) {
        checkNotNull(view);

        mEtEmail = view.findViewById(R.id.etEmail);
        mEtPassword = view.findViewById(R.id.etPassword);

        mCvError = view.findViewById(R.id.cvError);
        mTvError = view.findViewById(R.id.tvError);

        mBtnLogin = view.findViewById(R.id.btnLogin);
        mBtnRegister = view.findViewById(R.id.btnRegister);

        mPbLoading = view.findViewById(R.id.pbLoading);
    }

    @Override
    public void saveInstanceState(Bundle savedInstanceState) {
//        outState.putString(EMAIL_STATE, mEtEmail.getText().toString());
    }

    private void restoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState == null) return;

        // todo: restore
    }

    @Override
    public String getEmail() {
        return mEmail;
    }

    @Override
    public void setEmail(String email) {
        mEmail = email;
    }

    @Override
    public String getPassword() {
        return mPassword;
    }

    @Override
    public void setPassword(String password) {
        mPassword = password;
    }

    public class Builder
            extends DefaultViewModel.Builder
            implements BaseViewModel.Builder {

        public Builder(Context context) {
            super(context);
        }

        @Override
        public void build() {
            AuthViewModel viewModel = AuthViewModel.this;
            viewModel.initViews(mView);
            viewModel.restoreInstanceState(mSavedInstanceState);
        }
    }
}
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
    private static final String PASSWORD_STATE = "password_state";

    private EditText mEtEmail;
    private EditText mEtPassword;

    private ProgressBar mPbLoading;

    private Button mBtnLogin;
    private Button mBtnRegister;

    @Override
    public Builder builder(Context context) {
        return new AuthViewModel.Builder(context);
    }

    @Override
    protected void initViews(View view) {
        checkNotNull(view);

        super.initViews(view);

        mEtEmail = view.findViewById(R.id.etEmail);
        mEtPassword = view.findViewById(R.id.etPassword);

        mBtnLogin = view.findViewById(R.id.btnLogin);
        mBtnRegister = view.findViewById(R.id.btnRegister);

        mPbLoading = view.findViewById(R.id.pbLoading);
    }

    @Override
    public void saveInstanceState(Bundle savedInstanceState) {
        super.saveInstanceState(savedInstanceState);

        saveEmailState(savedInstanceState);
        savePasswordState(savedInstanceState);
    }

    private void saveEmailState(Bundle outState) {
        outState.putString(EMAIL_STATE, mEtEmail.getText().toString());
    }

    private void savePasswordState(Bundle outState) {
        outState.putString(PASSWORD_STATE, mEtPassword.getText().toString());
    }

    protected void restoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState == null) return;

        super.restoreInstanceState(savedInstanceState);

        restoreEmailState(savedInstanceState);
        restorePasswordState(savedInstanceState);
    }

    private void restoreEmailState(Bundle state) {
        if (state.containsKey(EMAIL_STATE)) {
            String email = state.getString(EMAIL_STATE);
            mEtEmail.setText(email);
        }
    }

    private void restorePasswordState(Bundle state) {
        if (state.containsKey(PASSWORD_STATE)) {
            String password = state.getString(PASSWORD_STATE);
            mEtPassword.setText(password);
        }
    }

    @Override
    public EditText getEtEmail() {
        return mEtEmail;
    }

    @Override
    public EditText getEtPassword() {
        return mEtPassword;
    }

    @Override
    public ProgressBar getPbLoading() {
        return mPbLoading;
    }

    @Override
    public Button getBtnLogin() {
        return mBtnLogin;
    }

    @Override
    public Button getBtnRegister() {
        return mBtnRegister;
    }

    private void setLoginClickListener(View.OnClickListener listener) {
        if (mBtnLogin == null) return;

        mBtnLogin.setOnClickListener(listener);
    }

    private void setRegisterClickListener(View.OnClickListener listener) {
        if (mBtnRegister == null) return;

        mBtnRegister.setOnClickListener(listener);
    }

    public class Builder
            extends DefaultViewModel.Builder
            implements AuthContract.ViewModel.Builder {

        private View.OnClickListener mLoginClickListener;
        private View.OnClickListener mRegisterClickListener;

        public Builder(Context context) {
            super(context);
        }

        @Override
        public AuthContract.ViewModel.Builder setLoginClickListener(View.OnClickListener listener) {
            mLoginClickListener = listener;
            return this;
        }

        @Override
        public AuthContract.ViewModel.Builder setRegisterClickListener(View.OnClickListener listener) {
            mRegisterClickListener = listener;
            return this;
        }

        @Override
        public void build() {
            AuthViewModel viewModel = AuthViewModel.this;

            viewModel.initViews(mView);
            viewModel.setErrorClickListener(mErrorClickListener);
            viewModel.setLoginClickListener(mLoginClickListener);
            viewModel.setRegisterClickListener(mRegisterClickListener);
            viewModel.restoreInstanceState(mSavedInstanceState);
        }
    }
}
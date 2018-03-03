package com.ivzb.encrypted_chat.auth.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ivzb.encrypted_chat.R;
import com.ivzb.encrypted_chat._base.ui.DefaultView;
import com.ivzb.encrypted_chat.home.ui.HomeActivity;

public class AuthView
        extends DefaultView<AuthContract.Presenter, AuthContract.ViewModel>
        implements AuthContract.View {

    private static final String EMAIL_STATE = "email_state";

    private EditText mEtEmail;
    private EditText mEtPassword;

    private CardView mCvError;
    private TextView mTvError;

    private Button mBtnLogin;
    private Button mBtnRegister;

    private ProgressBar mPbLoading;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflateFragment(inflater, container);

        mEtEmail = view.findViewById(R.id.etEmail);
        mEtPassword = view.findViewById(R.id.etPassword);

        mCvError = view.findViewById(R.id.cvError);
        mTvError = view.findViewById(R.id.tvError);

        mBtnLogin = view.findViewById(R.id.btnLogin);
        mBtnRegister = view.findViewById(R.id.btnRegister);

        mPbLoading = view.findViewById(R.id.pbLoading);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(EMAIL_STATE)) {
                String email = savedInstanceState.getString(EMAIL_STATE);
                mEtEmail.setText(email);
            }
        }

        mCvError.setOnClickListener(mErrorListener);
        mBtnLogin.setOnClickListener(mLoginListener);
        mBtnRegister.setOnClickListener(mRegisterListener);

        return view;
    }

    @Override
    public View inflateFragment(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.auth_frag, container, false);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mViewModel != null) {
            outState.putString(EMAIL_STATE, mEtEmail.getText().toString());
        }
    }

    @Override
    public void showLoading(boolean loading) {
        if (!isActive()) return;

        mPbLoading.setVisibility(loading ? View.VISIBLE : View.GONE);
        mBtnLogin.setVisibility(loading ? View.GONE : View.VISIBLE);
        mBtnRegister.setVisibility(loading ? View.GONE : View.VISIBLE);
    }

    @Override
    public void navigateToHome() {
        Intent intent = new Intent(getContext(), HomeActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void setErrorMessage(String message) {
        mTvError.setText(message);
    }

    @Override
    public void showErrorMessage(boolean show) {
        mCvError.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void hideErrorMessage() {
        mTvError.setText("");
        mCvError.setVisibility(View.GONE);
    }

    private View.OnClickListener mErrorListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            hideErrorMessage();
        }
    };

    private View.OnClickListener mLoginListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            bindViewModel();

            mPresenter.login(
                    mViewModel.getEmail(),
                    mViewModel.getPassword());
        }
    };

    private View.OnClickListener mRegisterListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            bindViewModel();

            mPresenter.register(
                    mViewModel.getEmail(),
                    mViewModel.getPassword());
        }
    };

    private void bindViewModel() {
        mViewModel.setEmail(mEtEmail.getText().toString());
        mViewModel.setPassword(mEtPassword.getText().toString());
    }
}
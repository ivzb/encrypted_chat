package com.ivzb.encrypted_chat.auth.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.ivzb.encrypted_chat.R;
import com.ivzb.encrypted_chat._base.ui.DefaultView;
import com.ivzb.encrypted_chat.home.ui.HomeActivity;

public class AuthView
        extends DefaultView<AuthContract.Presenter, AuthContract.ViewModel>
        implements AuthContract.View {

    private static final String EMAIL_STATE = "email_state";

    private Button mBtnLogin;
    private Button mBtnRegister;
    private ProgressBar mPbLoading;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.auth_frag, container, false);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(EMAIL_STATE)) {
                String email = savedInstanceState.getString(EMAIL_STATE);
                mViewModel.setEmail(email);
            }
        }

        mBtnLogin = view.findViewById(R.id.btnLogin);
        mBtnRegister = view.findViewById(R.id.btnRegister);
        mPbLoading = view.findViewById(R.id.pbLoading);

        mBtnLogin.setOnClickListener(mLoginListener);
        mBtnRegister.setOnClickListener(mRegisterListener);

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mViewModel != null) {
            outState.putString(EMAIL_STATE, mViewModel.getEmail());
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

    private View.OnClickListener mLoginListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mPresenter.login(
                    mViewModel.getEmail(),
                    mViewModel.getPassword());
        }
    };

    private View.OnClickListener mRegisterListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mPresenter.register(
                    mViewModel.getEmail(),
                    mViewModel.getPassword());
        }
    };
}
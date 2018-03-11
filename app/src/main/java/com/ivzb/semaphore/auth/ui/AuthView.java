package com.ivzb.semaphore.auth.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ivzb.semaphore.R;
import com.ivzb.semaphore._base.ui.DefaultView;
import com.ivzb.semaphore.auth.data.AuthEntity;
import com.ivzb.semaphore.home.ui.HomeActivity;

public class AuthView
        extends DefaultView<AuthContract.Presenter, AuthContract.ViewModel>
        implements AuthContract.View {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflateFragment(inflater, container);

        AuthContract.ViewModel.Builder builder = (AuthContract.ViewModel.Builder) mViewModel.builder(mContext);
        builder.setView(view);
        builder.setErrorClickListener(mErrorClickListener);
        builder.setLoginClickListener(mLoginListener);
        builder.setRegisterClickListener(mRegisterListener);
        builder.setSavedInstanceState(savedInstanceState);
        builder.build();

        return view;
    }

    @Override
    public View inflateFragment(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.auth_frag, container, false);
    }

    @Override
    public void showLoading(boolean loading) {
        mViewModel.getPbLoading().setVisibility(loading ? View.VISIBLE : View.GONE);
        mViewModel.getBtnLogin().setVisibility(loading ? View.GONE : View.VISIBLE);
        mViewModel.getBtnRegister().setVisibility(loading ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onClickLogin(AuthEntity auth) {
        mPresenter.login(auth);
    }

    @Override
    public void onClickRegister(AuthEntity auth) {
        mPresenter.register(auth);
    }

    @Override
    public void navigateToHome() {
        Intent intent = new Intent(mContext, HomeActivity.class);
        mContext.startActivity(intent);

        if (mContext instanceof Activity) {
            ((Activity) mContext).finish();
        }
    }

    private View.OnClickListener mLoginListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AuthEntity auth = new AuthEntity(
                    mViewModel.getEtEmail().getText().toString(),
                    mViewModel.getEtPassword().getText().toString());

            onClickLogin(auth);
        }
    };

    private View.OnClickListener mRegisterListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AuthEntity auth = new AuthEntity(
                    mViewModel.getEtEmail().getText().toString(),
                    mViewModel.getEtPassword().getText().toString());

            onClickRegister(auth);
        }
    };
}
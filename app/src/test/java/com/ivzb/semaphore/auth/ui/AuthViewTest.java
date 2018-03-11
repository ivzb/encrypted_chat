package com.ivzb.semaphore.auth.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.ivzb.semaphore._base.ui.DefaultViewTest;
import com.ivzb.semaphore._base.ui._contracts.BaseViewModel;
import com.ivzb.semaphore.auth.data.AuthEntity;

import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthViewTest
        extends DefaultViewTest<AuthEntity, AuthContract.View, AuthContract.Presenter, AuthContract.ViewModel> {

    private @Mock AuthContract.Presenter mPresenter;
    private @Mock AuthContract.ViewModel mViewModel;

    private @Mock ProgressBar mPbLoading;
    private @Mock Button mBtnLogin;
    private @Mock Button mBtnRegister;

    @Override
    public AuthView getView() {
        return (AuthView) mFragment;
    }

    @Override
    public AuthContract.Presenter getPresenter() {
        return mPresenter;
    }

    @Override
    public AuthContract.ViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    protected void setupViewModel(BaseViewModel vm) {
        super.setupViewModel(vm);

        AuthContract.ViewModel viewModel = (AuthContract.ViewModel) vm;

        when(viewModel.getPbLoading()).thenReturn(mPbLoading);
        when(viewModel.getBtnLogin()).thenReturn(mBtnLogin);
        when(viewModel.getBtnRegister()).thenReturn(mBtnRegister);
    }

    @Override
    public AuthEntity initEntity(String _) {
        return new AuthEntity();
    }

    @Override
    public AuthView initView() {
        return new AuthView();
    }

    @Override
    public BaseViewModel.Builder initViewModelBuilder() {
        return mock(AuthViewModel.Builder.class);
    }

    @Test
    public void showLoading_true() {
        showLoading(true);
    }

    @Test
    public void showLoading_false() {
        showLoading(false);
    }

    private void showLoading(boolean loading) {
        // act
        getView().showLoading(loading);

        // assert
        verify(getViewModel()).getPbLoading();
        verify(getViewModel()).getBtnLogin();
        verify(getViewModel()).getBtnRegister();

        int loadingVisibility = loading ? View.VISIBLE : View.GONE;
        int buttonsVisibility = loading ? View.GONE : View.VISIBLE;

        verify(mPbLoading).setVisibility(eq(loadingVisibility));
        verify(mBtnLogin).setVisibility(eq(buttonsVisibility));
        verify(mBtnRegister).setVisibility(eq(buttonsVisibility));
    }

    @Test
    public void navigateToHome() {
        // act
        getView().navigateToHome();

        // assert
        verify(mContext).startActivity(isA(Intent.class));
    }

    @Test
    public void onClickLogin() {
        // arrange
        AuthEntity entity = mock(AuthEntity.class);

        // act
        getView().onClickLogin(entity);

        // assert
        verify(getPresenter()).login(eq(entity));
    }

    @Test
    public void onClickRegister() {
        // arrange
        AuthEntity entity = mock(AuthEntity.class);

        // act
        getView().onClickRegister(entity);

        // assert
        verify(getPresenter()).register(eq(entity));
    }
}


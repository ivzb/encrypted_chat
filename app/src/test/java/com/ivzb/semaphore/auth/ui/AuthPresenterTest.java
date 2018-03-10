package com.ivzb.semaphore.auth.ui;

import android.content.Context;

import com.ivzb.semaphore._base.ui.DefaultPresenterTest;
import com.ivzb.semaphore.auth.data.AuthDataSource;
import com.ivzb.semaphore.auth.data.AuthEntity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthPresenterTest
        extends DefaultPresenterTest<AuthContract.Presenter, AuthContract.View, AuthDataSource> {

    @Mock
    protected AuthContract.View mView;

    @Mock
    protected AuthDataSource mDataSource;

    @Override
    public AuthContract.View getView() {
        return mView;
    }

    @Override
    public AuthDataSource getDataSource() {
        return mDataSource;
    }

    @Override
    public AuthContract.Presenter initPresenter(
            Context context,
            AuthContract.View view,
            AuthDataSource dataSource) {

        return new AuthPresenter(context, view, dataSource);
    }

    @Test
    public void testLogin_inactiveView() {
        when(getView().isActive()).thenReturn(false);

        mPresenter.login("some_email", "some_password");

        verify(getView()).isActive();
    }


}


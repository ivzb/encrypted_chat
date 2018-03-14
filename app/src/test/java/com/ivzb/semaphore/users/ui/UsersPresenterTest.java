package com.ivzb.semaphore.users.ui;

import android.content.Context;

import com.ivzb.semaphore._base.data.generators.DefaultGeneratorConfig;
import com.ivzb.semaphore._base.ui.endless_scroll.DefaultEndlessScrollPresenterTest;
import com.ivzb.semaphore.users.data.UserEntity;
import com.ivzb.semaphore.users.data.UsersDataSource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Random;

import io.bloco.faker.Faker;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UsersPresenterTest
        extends DefaultEndlessScrollPresenterTest<UserEntity, UsersContract.Presenter, UsersContract.View, UsersDataSource> {

    @Mock protected Context mContext;
    @Mock protected UsersContract.View mView;
    @Mock protected UsersDataSource mDataSource;

    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public UsersContract.View getView() {
        return mView;
    }

    @Override
    public UsersDataSource getDataSource() {
        return mDataSource;
    }

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        DefaultGeneratorConfig.destroyInstance();
        DefaultGeneratorConfig.initialize(new Random(), new Faker());

        mPresenter = new UsersPresenter(mContext, mView, mDataSource);
    }

    @Override
    public UserEntity initEntity(String id) {
        return new UserEntity(id);
    }

    @Override
    public UsersContract.Presenter initPresenter(
            Context context,
            UsersContract.View view,
            UsersDataSource dataSource) {

        return new UsersPresenter(context, view, dataSource);
    }

    @Test
    public void testClickUser_inactiveView() {
        when(getView().isActive()).thenReturn(false);

        mPresenter.clickUser(null);

        verify(getView()).isActive();
    }

    @Test
    public void testClickUser_nullUser() {
        when(getView().isActive()).thenReturn(true);

        mPresenter.clickUser(null);

        verify(getView()).isActive();
        verify(getView()).setErrorMessage(anyString());
        verify(getView()).showErrorMessage(true);
    }

    @Test
    public void testClickUser() {
        when(getView().isActive()).thenReturn(true);

        UserEntity expected = new UserEntity();

        mPresenter.clickUser(expected);

        verify(getView()).isActive();
        verify(getView()).openConversation(eq(expected));
    }
}


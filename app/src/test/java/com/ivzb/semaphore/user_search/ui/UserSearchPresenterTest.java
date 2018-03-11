package com.ivzb.semaphore.user_search.ui;

import com.ivzb.semaphore._base.data.callbacks.LoadCallback;
import com.ivzb.semaphore._base.data.generators.DefaultGeneratorConfig;
import com.ivzb.semaphore.users.ui.UsersPresenterTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.Random;

import io.bloco.faker.Faker;

import static com.ivzb.semaphore._base.data.config.DefaultConfig.INITIAL_PAGE;
import static com.ivzb.semaphore._base.data.config.DefaultConfig.NO_ID;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserSearchPresenterTest extends UsersPresenterTest {

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        DefaultGeneratorConfig.destroyInstance();
        DefaultGeneratorConfig.initialize(new Random(), new Faker());

        UserSearchContract.View view = mock(UserSearchContract.View.class);
        super.mView = view;
        mId = NO_ID;

        mPresenter = new UserSearchPresenter(mContext, view, mDataSource);
    }

    @Test
    public void testSearchUser_inactiveView() {
        when(getView().isActive()).thenReturn(false);

        ((UserSearchContract.Presenter) mPresenter).searchUser(null);

        verify(getView()).isActive();
    }

    @Test
    public void testSearchUser_activeView() {
        when(getView().isActive()).thenReturn(true);

        String expected = "email";
        ((UserSearchContract.Presenter) mPresenter).searchUser(expected);

        verify(getView(), times(2)).isActive();
        verify(mView).clearEntities();
        verify(mView).setLoadingIndicator(eq(true));
        verify(mView).setMore(eq(true));
        verify(mView).setPage(eq(INITIAL_PAGE));

        verify(mDataSource).load(eq(expected), eq(INITIAL_PAGE), isA(LoadCallback.class));
    }

    @Test
    @Override
    public void refresh() {
        mId = "some_id";
        super.refresh();
    }

    @Test
    public void refresh_emptyId() {
        // arrange
        when(getView().isActive()).thenReturn(true);

        // act
        mPresenter.refresh(NO_ID);

        // assert
        verify(getView()).showEntities(eq(false));
        verify(getView()).showErrorMessage(eq(false));
        verify(getView()).showNoEntities(eq(false));
    }
}

package com.ivzb.semaphore.conversations.ui;

import android.content.Context;

import com.ivzb.semaphore._base.data.callbacks.SaveCallback;
import com.ivzb.semaphore._base.data.generators.DefaultGeneratorConfig;
import com.ivzb.semaphore._base.ui.endless_scroll.DefaultEndlessScrollPresenterTest;
import com.ivzb.semaphore.conversations.data.ConversationEntity;
import com.ivzb.semaphore.conversations.data.ConversationsDataSource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Random;

import io.bloco.faker.Faker;

import static com.ivzb.semaphore.conversations.ui.ConversationsPresenter.REQUEST_USER_SAVE;
import static com.ivzb.semaphore.conversations.ui.ConversationsPresenter.RESULT_OK;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ConversationsPresenterTest
        extends DefaultEndlessScrollPresenterTest<ConversationEntity, ConversationsContract.Presenter, ConversationsContract.View, ConversationsDataSource> {

    @Mock protected Context mContext;
    @Mock protected ConversationsContract.View mView;
    @Mock protected ConversationsDataSource mDataSource;

    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public ConversationsContract.View getView() {
        return mView;
    }

    @Override
    public ConversationsDataSource getDataSource() {
        return mDataSource;
    }

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        DefaultGeneratorConfig.destroyInstance();
        DefaultGeneratorConfig.initialize(new Random(), new Faker());

        mPresenter = new ConversationsPresenter(mContext, mView, mDataSource);
    }

    @Override
    public ConversationEntity initEntity(String id) {
        return new ConversationEntity(id);
    }

    @Override
    public ConversationsContract.Presenter initPresenter(
            Context context,
            ConversationsContract.View view,
            ConversationsDataSource dataSource) {

        return new ConversationsPresenter(context, view, dataSource);
    }

    @Test
    public void testResult_inactiveView() {
        // act
        mPresenter.result(REQUEST_USER_SAVE, RESULT_OK, "message_here");

        // assert
        verify(mView).isActive();
    }

    @Test
    public void testResult() {
        // arrange
        when(mView.isActive()).thenReturn(true);
        String expectedMessage = "message_here";

        // act
        mPresenter.result(REQUEST_USER_SAVE, RESULT_OK, expectedMessage);

        // assert
        verify(mView).isActive();
        verify(mView).setSuccessMessage(eq(expectedMessage));
        verify(mView).showSuccessMessage(eq(true));
    }

    @Test
    public void testClickConversation_inactiveView() {
        when(getView().isActive()).thenReturn(false);

        mPresenter.openConversation(null);

        verify(getView()).isActive();
    }

    @Test
    public void testClickConversation_nullConversation() {
        when(getView().isActive()).thenReturn(true);

        mPresenter.openConversation(null);

        verify(getView()).isActive();
        verify(getView()).setErrorMessage(anyString());
        verify(getView()).showErrorMessage(true);
    }

    @Test
    public void testClickConversation() {
        when(getView().isActive()).thenReturn(true);

        ConversationEntity expected = new ConversationEntity();

        mPresenter.openConversation(expected);

        verify(getView()).isActive();
        verify(getView()).openConversation(eq(expected));
    }

    @Test
    public void testClickRemoveConversation_inactiveView() {
        when(getView().isActive()).thenReturn(false);

        mPresenter.removeConversation(null);

        verify(getView()).isActive();
    }

    @Test
    public void testClickRemoveConversation_nullConversation() {
        when(getView().isActive()).thenReturn(true);

        mPresenter.removeConversation(null);

        verify(getView()).isActive();
        verify(getView()).setErrorMessage(anyString());
        verify(getView()).showErrorMessage(true);
    }

    @Test
    public void testClickRemoveConversation() {
        when(getView().isActive()).thenReturn(true);

        String expectedId = "some_id";
        ConversationEntity expected = new ConversationEntity(expectedId);

        mPresenter.removeConversation(expected);

        verify(getView()).isActive();
        verify(getView()).setLoadingIndicator(eq(true));
        verify(getDataSource()).remove(eq(expectedId), isA(SaveCallback.class));
    }
}

package com.ivzb.semaphore.conversation.ui;

import android.content.Context;

import com.ivzb.semaphore._base.data.Result;
import com.ivzb.semaphore._base.data.callbacks.SaveCallback;
import com.ivzb.semaphore._base.data.generators.DefaultGeneratorConfig;
import com.ivzb.semaphore._base.ui.endless.DefaultEndlessScrollPresenterTest;
import com.ivzb.semaphore.conversation.data.MessageEntity;
import com.ivzb.semaphore.conversation.data.MessagesDataSource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Random;

import io.bloco.faker.Faker;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ConversationPresenterTest
        extends DefaultEndlessScrollPresenterTest<MessageEntity, ConversationContract.Presenter, ConversationContract.View, MessagesDataSource> {

    @Mock protected Context mContext;
    @Mock protected ConversationContract.View mView;
    @Mock protected MessagesDataSource mDataSource;

    @Captor protected ArgumentCaptor<String> mSaveMessageCaptor;

    private String mExpectedResultId;

    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public ConversationContract.View getView() {
        return mView;
    }

    @Override
    public MessagesDataSource getDataSource() {
        return mDataSource;
    }

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        DefaultGeneratorConfig.destroyInstance();
        DefaultGeneratorConfig.initialize(new Random(), new Faker());

        mPresenter = new ConversationPresenter(mContext, mView, mDataSource);
    }

    @Override
    public MessageEntity initEntity(String id) {
        return new MessageEntity(id);
    }

    @Override
    public ConversationContract.Presenter initPresenter(
            Context context,
            ConversationContract.View view,
            MessagesDataSource dataSource) {

        return new ConversationPresenter(context, view, dataSource);
    }

    @Test
    public void testClickSendMessage_inactiveView() {
        when(getView().isActive()).thenReturn(false);

        mPresenter.sendMessage(null);

        verify(getView()).isActive();
    }

    @Test
    public void testClickSendMessage_nullMessage() {
        when(getView().isActive()).thenReturn(true);

        mPresenter.sendMessage(null);

        verify(getView()).isActive();
    }

    @Test
    public void testClickSendMessage_emptyMessage() {
        when(getView().isActive()).thenReturn(true);

        MessageEntity entity = new MessageEntity();
        mPresenter.sendMessage(entity);

        verify(getView()).isActive();
    }

    @Test
    public void testClickSendMessage_initiallyInactiveView() {
        // arrange
        MessageEntity entity = new MessageEntity("some message here");

        arrangeSaveMessage(
                entity,
                true,
                false,
                true);

        // act
        mPresenter.sendMessage(entity);

        // assert
        verify(getView()).isActive();
    }

    @Test
    public void testClickSendMessage_successCallbackInactiveView() {
        // arrange
        MessageEntity entity = new MessageEntity("some message here");

        arrangeSaveMessage(
                entity,
                true,
                true,
               false);

        // act
        mPresenter.sendMessage(entity);

        // assert
        verify(getDataSource()).save(eq(entity), isA(SaveCallback.class));
        verify(getView(), times(2)).isActive();
        verify(getView()).setLoadingIndicator(eq(true));
    }

    @Test
    public void testClickSendMessage_failureCallbackInactiveView() {
        // arrange
        MessageEntity entity = new MessageEntity("some message here");

        arrangeSaveMessage(
                entity,
                false,
                true,
                false);

        // act
        mPresenter.sendMessage(entity);

        // assert
        verify(getDataSource()).save(eq(entity), isA(SaveCallback.class));
        verify(getView(), times(2)).isActive();
        verify(getView()).setLoadingIndicator(eq(true));
    }

    @Test
    public void testClickSendMessage_failure() {
        // arrange
        MessageEntity entity = new MessageEntity("some message here");

        arrangeSaveMessage(
                entity,
                false,
                true,
                true);

        // act
        mPresenter.sendMessage(entity);

        // assert
        verify(getDataSource()).save(eq(entity), isA(SaveCallback.class));
        verify(getView(), times(2)).isActive();
        verify(getView()).setLoadingIndicator(eq(true));
        verify(getView()).setLoadingIndicator(eq(false));
        verify(getView()).setErrorMessage(anyString());
        verify(getView()).showErrorMessage(eq(true));
    }

    @Test
    public void testClickSendMessage() {
        // arrange
        MessageEntity entity = new MessageEntity("some message here");

        arrangeSaveMessage(
                entity,
                true,
                true,
                true
        );

        // act
        mPresenter.sendMessage(entity);

        // assert
        verify(getDataSource()).save(eq(entity), isA(SaveCallback.class));
        verify(getView(), times(2)).isActive();
        verify(getView()).setLoadingIndicator(eq(true));
        verify(getView()).setLoadingIndicator(eq(false));
    }

    protected void arrangeSaveMessage(
            final MessageEntity entity,
            final Boolean isSuccessful,
            final Boolean initiallyInactiveView,
            final Boolean callbackInactiveView) {

        mSaveMessageCaptor = ArgumentCaptor.forClass(String.class);

        when(getView().isActive()).thenReturn(initiallyInactiveView);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                SaveCallback<String> callback = (SaveCallback<String>) invocation.getArguments()[1];

                when(getView().isActive()).thenReturn(callbackInactiveView);

                if (isSuccessful) {
                    Result<String> result = new Result<>("some_id", "message here");
                    callback.onSuccess(result);

                    mExpectedShowEntities = true;
                    return null;
                }

                callback.onFailure(sLoadFailure);
                return null;
            }
        }).when(getDataSource()).save(
                eq(entity),
                any(SaveCallback.class));
    }
}


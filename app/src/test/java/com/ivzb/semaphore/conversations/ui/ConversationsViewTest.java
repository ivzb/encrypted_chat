package com.ivzb.semaphore.conversations.ui;

import android.content.Intent;

import com.ivzb.semaphore._base.ui.endless.DefaultEndlessScrollViewTest;
import com.ivzb.semaphore.conversations.data.ConversationEntity;

import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ConversationsViewTest
        extends DefaultEndlessScrollViewTest<ConversationEntity, ConversationsContract.View, ConversationsContract.Presenter, ConversationsContract.ViewModel> {

    private @Mock ConversationsContract.Presenter mPresenter;
    private @Mock ConversationsContract.ViewModel mViewModel;

    @Override
    public ConversationsView getView() {
        return (ConversationsView) mFragment;
    }

    @Override
    public ConversationsContract.Presenter getPresenter() {
        return mPresenter;
    }

    @Override
    public ConversationsContract.ViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public ConversationEntity initEntity(String id) {
        return new ConversationEntity(id);
    }

    @Override
    public ConversationsView initView() {
        return new ConversationsView();
    }

    @Test
    public void openConversation() {
        // arrange
        ConversationEntity model = initEntity("5");

        // act
        getView().openConversation(model);

        // assert
        verify(mContext).startActivity(isA(Intent.class));
    }

    @Test
    public void onClickOpenConversation() {
        // arrange
        ConversationEntity entity = mock(ConversationEntity.class);

        // act
        getView().onClickOpenConversation(entity);

        // assert
        verify(getPresenter()).openConversation(eq(entity));
    }

    @Test
    public void onClickRemoveConversation() {
        // arrange
        ConversationEntity entity = mock(ConversationEntity.class);

        // act
        getView().onClickRemoveConversation(entity);

        // assert
        verify(getPresenter()).removeConversation(eq(entity));
    }
}


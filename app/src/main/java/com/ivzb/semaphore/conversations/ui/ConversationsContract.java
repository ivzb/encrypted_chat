package com.ivzb.semaphore.conversations.ui;

import com.ivzb.semaphore._base.ui._contracts.endless_scroll.BaseEndlessScrollPresenter;
import com.ivzb.semaphore._base.ui._contracts.endless_scroll.BaseEndlessScrollView;
import com.ivzb.semaphore._base.ui._contracts.endless_scroll.BaseEndlessScrollViewModel;
import com.ivzb.semaphore.conversations.data.ConversationEntity;

public class ConversationsContract {

    public interface View extends BaseEndlessScrollView<ConversationEntity, Presenter, ViewModel> {

        void onClickOpenConversation(ConversationEntity conversation);
        void onClickRemoveConversation(ConversationEntity conversation);

        void openConversation(ConversationEntity conversation);
    }

    public interface Presenter extends BaseEndlessScrollPresenter<ConversationEntity> {

        void openConversation(ConversationEntity conversation);
        void removeConversation(ConversationEntity conversation);
    }

    public interface ViewModel extends BaseEndlessScrollViewModel<ConversationEntity> {

    }
}

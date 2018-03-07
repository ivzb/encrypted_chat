package com.ivzb.semaphore.conversations.ui;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivzb.semaphore._base.ui._contracts.BaseAdapter;
import com.ivzb.semaphore._base.ui._contracts.endless.BaseEndlessScrollPresenter;
import com.ivzb.semaphore._base.ui._contracts.endless.BaseEndlessScrollView;
import com.ivzb.semaphore._base.ui._contracts.endless.BaseEndlessScrollViewModel;
import com.ivzb.semaphore.conversations.data.ConversationEntity;
import com.ivzb.semaphore.utils.ui.ScrollChildSwipeRefreshLayout;

public class ConversationsContract {

    public interface View extends BaseEndlessScrollView<ConversationEntity, Presenter, ViewModel> {

        void onConversationClick(ConversationEntity conversation);
        void onRemoveConversationClick(ConversationEntity conversation);

        void openConversation(ConversationEntity conversation);
    }

    public interface Presenter extends BaseEndlessScrollPresenter<ConversationEntity> {

        void clickConversation(ConversationEntity conversation);
        void clickRemoveConversation(ConversationEntity conversation);
    }

    public interface ViewModel extends BaseEndlessScrollViewModel<ConversationEntity> {

    }
}

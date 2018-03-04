package com.ivzb.encrypted_chat.conversations.ui;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivzb.encrypted_chat._base.ui._contracts.BaseAdapter;
import com.ivzb.encrypted_chat._base.ui._contracts.endless.BaseEndlessScrollPresenter;
import com.ivzb.encrypted_chat._base.ui._contracts.endless.BaseEndlessScrollView;
import com.ivzb.encrypted_chat._base.ui._contracts.endless.BaseEndlessScrollViewModel;
import com.ivzb.encrypted_chat.conversations.data.ConversationEntity;
import com.ivzb.encrypted_chat.utils.ui.ScrollChildSwipeRefreshLayout;

public class ConversationsContract {

    public interface View extends BaseEndlessScrollView<ConversationEntity, Presenter, ViewModel> {

        void onConversationClick(ConversationEntity conversation);
        void onRemoveConversationClick(ConversationEntity conversation);
        void onErrorClick();
    }

    public interface Presenter extends BaseEndlessScrollPresenter<ConversationEntity> {

        void clickConversation(ConversationEntity conversation);
        void clickRemoveConversation(ConversationEntity conversation);

        void clickErrorMessage();
    }

    public interface ViewModel extends BaseEndlessScrollViewModel<ConversationEntity> {

        void init(android.view.View view);

        void saveInstanceState(Bundle savedInstanceState);
        void restoreInstanceState(Bundle savedInstanceState);

        void setErrorClickListener(android.view.View.OnClickListener listener);

        void setAdapter(BaseAdapter<ConversationEntity> adapter);

        Parcelable getConversationsState();
        void setConversationsState(Parcelable state);

        Parcelable getLayoutManagerState();
        void setLayoutManagerState(Parcelable state);

        ScrollChildSwipeRefreshLayout getRefreshLayout();
        RecyclerView getRvConversations();

        TextView getTvNoConversations();
        ImageView getIvNoConversations();

        CardView getCvError();
        TextView getTvError();
    }
}

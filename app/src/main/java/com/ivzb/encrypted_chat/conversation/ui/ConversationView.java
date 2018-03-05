package com.ivzb.encrypted_chat.conversation.ui;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ivzb.encrypted_chat.R;
import com.ivzb.encrypted_chat._base.data.config.DefaultConfig;
import com.ivzb.encrypted_chat._base.ui._contracts.BaseEntityActionHandler;
import com.ivzb.encrypted_chat._base.ui.endless.DefaultEndlessScrollView;
import com.ivzb.encrypted_chat._base.ui.prompt.DialogListener;
import com.ivzb.encrypted_chat._base.ui.prompt.PromptDialogFragment;
import com.ivzb.encrypted_chat.conversation.data.MessageEntity;
import com.ivzb.encrypted_chat.conversations.ui.ConversationsContract;
import com.ivzb.encrypted_chat.utils.ui.SwipeRefreshLayoutUtils;

import static com.ivzb.encrypted_chat._base.data.config.DefaultConfig.INITIAL_PAGE;

public class ConversationView
        extends DefaultEndlessScrollView<MessageEntity, ConversationContract.Presenter, ConversationContract.ViewModel>
        implements ConversationContract.View {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        // todo: get conversation id from bundle
        mPresenter.refresh(DefaultConfig.NO_ID);

        return view;
    }

    @Override
    public View inflateFragment(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.users_frag, container, false);
    }

    @Override
    public void initEndlessAdapter() {
        initEndlessAdapter(
                getContext(),
                new MessagesAdapter(getContext()),
                mViewModel.getRecyclerView());
    }

    @Override
    public void openUi(MessageEntity user) {
        // todo: show message details
    }

    @Override
    public void onRefresh() {
        mPresenter.refresh(DefaultConfig.NO_ID);
    }

    @Override
    public void onSendMessage(MessageEntity message) {
        mPresenter.sendMessage(message);
    }
}

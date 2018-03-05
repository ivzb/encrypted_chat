package com.ivzb.semaphore.conversation.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ivzb.semaphore.R;
import com.ivzb.semaphore._base.data.config.DefaultConfig;
import com.ivzb.semaphore._base.ui._contracts.BaseEntityActionHandler;
import com.ivzb.semaphore._base.ui.endless.DefaultEndlessScrollView;
import com.ivzb.semaphore.conversation.data.MessageEntity;

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
                new MessagesAdapter(getContext(), mMessageClickListener),
                mViewModel.getRecyclerView());
    }

    @Override
    public void onRefresh() {
        mPresenter.refresh(DefaultConfig.NO_ID);
    }

    @Override
    public void onSendMessage(MessageEntity message) {
        mPresenter.sendMessage(message);
    }

    @Override
    public void onClickMessage(MessageEntity message) {
        // todo: show message details
    }

    private BaseEntityActionHandler<MessageEntity> mMessageClickListener = new BaseEntityActionHandler<MessageEntity>() {
        @Override
        public void onAdapterEntityClick(MessageEntity message) {
            onClickMessage(message);
        }
    };
}

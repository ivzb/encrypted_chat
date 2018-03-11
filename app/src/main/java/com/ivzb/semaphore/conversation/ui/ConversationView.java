package com.ivzb.semaphore.conversation.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ivzb.semaphore.R;
import com.ivzb.semaphore._base.ui._contracts.BaseAdapter;
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

        mPresenter.refresh(mViewModel.getContainerId());

        mViewModel.getIvSendMessage().setOnClickListener(mSendMessageClickListener);

        return view;
    }

    @Override
    public View inflateFragment(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.conversation_frag, container, false);
    }

    @Override
    public BaseAdapter<MessageEntity> initEndlessAdapter() {
        return new MessagesAdapter(mContext, mMessageClickListener);
    }

    @Override
    public LinearLayoutManager initLayoutManager(Context context) {
        LinearLayoutManager layoutManager = super.initLayoutManager(context);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        return layoutManager;
    }

    @Override
    public void onClickSendMessage(MessageEntity message) {
        mPresenter.sendMessage(message);
    }

    private View.OnClickListener mSendMessageClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MessageEntity message = new MessageEntity(mViewModel.getEtMessage().getText().toString());
            onClickSendMessage(message);
        }
    };

    @Override
    public void onClickShowMessageDetails(MessageEntity message) {
        // todo: show message details
    }

    private BaseEntityActionHandler<MessageEntity> mMessageClickListener = new BaseEntityActionHandler<MessageEntity>() {
        @Override
        public void onAdapterEntityClick(MessageEntity message) {
            onClickShowMessageDetails(message);
        }
    };
}

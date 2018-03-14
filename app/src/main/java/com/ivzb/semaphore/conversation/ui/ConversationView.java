package com.ivzb.semaphore.conversation.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ivzb.semaphore.R;
import com.ivzb.semaphore._base.ui._contracts.BaseAdapter;
import com.ivzb.semaphore._base.ui._contracts.BaseEntityActionHandler;
import com.ivzb.semaphore._base.ui.endless_scroll.EndlessScrollView;
import com.ivzb.semaphore.conversation.data.MessageEntity;
import com.ivzb.semaphore.messaging.service.MessagingService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConversationView
        extends EndlessScrollView<MessageEntity, ConversationContract.Presenter, ConversationContract.ViewModel>
        implements ConversationContract.View {

    private Toast mToast;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        mPresenter.refresh(mViewModel.getContainerId());

        mViewModel.getIvSendMessage().setOnClickListener(mSendMessageClickListener);

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mMessageReceiver,
                new IntentFilter(MessagingService.MESSAGE_ACTION));

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mMessageReceiver);
    }

    @Override
    public View inflateFragment(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.conversation_frag, container, false);
    }

    @Override
    public BaseAdapter<MessageEntity> initEndlessAdapter() {
        return new ConversationAdapter(mContext, mMessageClickListener);
    }

    @Override
    public LinearLayoutManager initLayoutManager(Context context) {
        LinearLayoutManager layoutManager = super.initLayoutManager(context);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        return layoutManager;
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra(MessagingService.EXTRA_MESSAGE);
            Random random = new Random();

            MessageEntity entity = new MessageEntity(message, random.nextBoolean());
            List<MessageEntity> entities = new ArrayList<>(1);
            entities.add(entity);
            mViewModel.getAdapter().prepend(entities);

            if (mToast != null) mToast.cancel();

            if (mViewModel.getLayoutManager().findFirstVisibleItemPosition() == 0) {
                mViewModel.getRecyclerView().scrollToPosition(0);
                return;
            }

            mToast = Toast.makeText(mContext, "Scroll to view new message...", Toast.LENGTH_LONG);
            mToast.show();
        }
    };

    @Override
    public void clearMessage() {
        mViewModel.getEtMessage().setText("");
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

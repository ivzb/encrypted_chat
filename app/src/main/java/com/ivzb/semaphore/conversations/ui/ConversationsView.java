package com.ivzb.semaphore.conversations.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ivzb.semaphore.R;
import com.ivzb.semaphore._base.data.config.DefaultConfig;
import com.ivzb.semaphore._base.ui.DefaultActionHandlerAdapter;
import com.ivzb.semaphore._base.ui._contracts.BaseAdapter;
import com.ivzb.semaphore._base.ui._contracts.BaseEntityActionHandler;
import com.ivzb.semaphore._base.ui.endless.DefaultEndlessScrollView;
import com.ivzb.semaphore._base.ui.prompt.DialogListener;
import com.ivzb.semaphore._base.ui.prompt.PromptDialogFragment;
import com.ivzb.semaphore.conversation.ui.ConversationActivity;
import com.ivzb.semaphore.conversations.data.ConversationEntity;
import com.ivzb.semaphore.utils.ui.SwipeRefreshLayoutUtils;

import static com.ivzb.semaphore._base.data.config.DefaultConfig.INITIAL_PAGE;

public class ConversationsView
        extends DefaultEndlessScrollView<ConversationEntity, ConversationsContract.Presenter, ConversationsContract.ViewModel>
        implements ConversationsContract.View {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        mPresenter.refresh(mViewModel.getContainerId());

        return view;
    }

    @Override
    public View inflateFragment(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.conversations_frag, container, false);
    }

    @Override
    public BaseAdapter<ConversationEntity> initEndlessAdapter() {
        return new ConversationsAdapter(
                mContext,
                mConversationClickListener,
                mRemoveConversationClickListener);
    }

    @Override
    public void openConversation(ConversationEntity conversation) {
        Intent intent = new Intent(mContext, ConversationActivity.class);
        intent.putExtra(ConversationActivity.EXTRA_CONVERSATION_ID, conversation.getId());
        mContext.startActivity(intent);
    }

    @Override
    public void onClickOpenConversation(ConversationEntity conversation) {
        mPresenter.openConversation(conversation);
    }

    private BaseEntityActionHandler<ConversationEntity> mConversationClickListener = new BaseEntityActionHandler<ConversationEntity>() {
        @Override
        public void onAdapterEntityClick(ConversationEntity conversation) {
            onClickOpenConversation(conversation);
        }
    };

    @Override
    public void onClickRemoveConversation(ConversationEntity conversation) {
        mPresenter.removeConversation(conversation);
    }

    private BaseEntityActionHandler<ConversationEntity> mRemoveConversationClickListener = new BaseEntityActionHandler<ConversationEntity>() {
        @Override
        public void onAdapterEntityClick(final ConversationEntity conversation) {
            PromptDialogFragment dialogFragment = new PromptDialogFragment()
                    .setTitle(getResources().getString(R.string.remove_conversation))
                    .setListener(new DialogListener() {
                        @Override
                        public void onDialogPositiveClick() {
                            onClickRemoveConversation(conversation);
                        }

                        @Override
                        public void onDialogNegativeClick() {
                            // no action needed
                        }
                    });

            dialogFragment.show(getFragmentManager(), "remove_conversation_dialog");
        }
    };
}

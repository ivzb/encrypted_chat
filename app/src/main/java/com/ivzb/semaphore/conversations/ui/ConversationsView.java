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

        // todo: refresh with conversation id
        mPresenter.refresh(DefaultConfig.NO_ID);

        return view;
    }

    @Override
    public View inflateFragment(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.conversations_frag, container, false);
    }

    @Override
    public BaseAdapter<ConversationEntity> initEndlessAdapter() {
        DefaultActionHandlerAdapter<ConversationEntity> adapter =
            new ConversationsAdapter(
                    getContext(),
                    mConversationClickListener,
                    mRemoveConversationClickListener);

        setupEndlessAdapter(
                getContext(),
                adapter,
                mViewModel.getRvConversations());

        return adapter;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mViewModel != null) {
            mViewModel.saveInstanceState(outState);
        }
    }

    @Override
    public void openConversation(ConversationEntity conversation) {
        Intent intent = new Intent(getContext(), ConversationActivity.class);
//        intent.putExtra(ConversationActivity.EXTRA_CONVERSATION_ID, conversation.getId());
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        mPresenter.refresh(DefaultConfig.NO_ID);
    }

    @Override
    public void onConversationClick(ConversationEntity conversation) {
        mPresenter.clickConversation(conversation);
    }

    private BaseEntityActionHandler<ConversationEntity> mConversationClickListener = new BaseEntityActionHandler<ConversationEntity>() {
        @Override
        public void onAdapterEntityClick(ConversationEntity conversation) {
            onConversationClick(conversation);
        }
    };

    @Override
    public void onRemoveConversationClick(ConversationEntity conversation) {
        mPresenter.clickRemoveConversation(conversation);
    }

    private BaseEntityActionHandler<ConversationEntity> mRemoveConversationClickListener = new BaseEntityActionHandler<ConversationEntity>() {
        @Override
        public void onAdapterEntityClick(final ConversationEntity conversation) {
            PromptDialogFragment dialogFragment = new PromptDialogFragment()
                .setTitle(getResources().getString(R.string.remove_conversation))
                .setListener(new DialogListener() {
                    @Override
                    public void onDialogPositiveClick() {
                                                              onRemoveConversationClick(conversation);
                                                                                                      }

                    @Override
                    public void onDialogNegativeClick() {
                        // no action needed
                    }
                });

            dialogFragment.show(getFragmentManager(), "remove_conversation_dialog");
        }
    };

    @Override
    public void onErrorClick() {
        mPresenter.clickErrorMessage();
    }

    private View.OnClickListener mErrorClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onErrorClick();
        }
    };

    @Override
    public void setLoadingIndicator(boolean active) {
        if (!isActive()) return;

        SwipeRefreshLayoutUtils.setLoading(mViewModel.getRefreshLayout(), active);
    }

    @Override
    public void showEntities(boolean show) {
        mViewModel.getRvConversations().setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showNoEntities(boolean show) {
        show &= !(mViewModel.getPage() > INITIAL_PAGE); // don't show "no conversations" if already loaded entities
        int visibility = show ? View.VISIBLE : View.GONE;

        mViewModel.getIvNoConversations().setVisibility(visibility);
        mViewModel.getTvNoConversations().setVisibility(visibility);
    }

    @Override
    public void showErrorMessage(boolean show) {
        mViewModel.getCvError().setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setErrorMessage(String message) {
        mViewModel.getTvError().setText(message);
    }
}

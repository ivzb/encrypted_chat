package com.ivzb.encrypted_chat.conversations.ui;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ivzb.encrypted_chat._base.data.Result;
import com.ivzb.encrypted_chat._base.data.callbacks.SaveCallback;
import com.ivzb.encrypted_chat._base.ui.endless.DefaultEndlessScrollPresenter;
import com.ivzb.encrypted_chat.conversations.data.ConversationEntity;
import com.ivzb.encrypted_chat.conversations.data.ConversationsDataSource;

public class ConversationsPresenter
        extends DefaultEndlessScrollPresenter<ConversationEntity, ConversationsContract.View, ConversationsDataSource>
        implements ConversationsContract.Presenter {

    protected static final int REQUEST_USER_SAVE = 301;
    protected static final int RESULT_OK = -1;

    public ConversationsPresenter(
            @NonNull Context context,
            @NonNull ConversationsContract.View view,
            @NonNull ConversationsDataSource dataSource) {

        super(context, view, dataSource);
    }

    @Override
    public void result(int requestCode, int resultCode, String message) {
        if (requestCode == REQUEST_USER_SAVE && resultCode == RESULT_OK) {
            mView.setSuccessMessage(message);
            mView.showSuccessMessage(true);
        }
    }

    @Override
    public void clickConversation(ConversationEntity conversation) {
        if (!mView.isActive()) return;

        mView.openConversation(conversation);
    }

    @Override
    public void clickRemoveConversation(ConversationEntity user) {
        if (!mView.isActive()) return;

        mView.setLoadingIndicator(true);

        mDataSource.remove(user.getId(), mConversationSaveCallback);
    }

    @Override
    public void clickErrorMessage() {
        if (!mView.isActive()) return;

        mView.showErrorMessage(false);
        mView.setErrorMessage("");
    }

    private SaveCallback<Boolean> mConversationSaveCallback = new SaveCallback<Boolean>() {
        @Override
        public void onSuccess(Result<Boolean> result) {
            if (!mView.isActive()) return;

            mView.setLoadingIndicator(false);

            Boolean added = result.getResults();

            if (!added) {
                mView.setErrorMessage(result.getMessage());
                mView.showErrorMessage(true);

                return;
            }

            result(REQUEST_USER_SAVE, RESULT_OK, result.getMessage());
        }

        @Override
        public void onFailure(String message) {
            if (!mView.isActive()) return;

            mView.setLoadingIndicator(false);

            mView.setErrorMessage(message);
            mView.showErrorMessage(true);
        }
    };
}

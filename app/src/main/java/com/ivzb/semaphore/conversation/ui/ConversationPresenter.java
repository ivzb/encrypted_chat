package com.ivzb.semaphore.conversation.ui;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ivzb.semaphore._base.data.Result;
import com.ivzb.semaphore._base.data.callbacks.SaveCallback;
import com.ivzb.semaphore._base.ui.endless_scroll.EndlessScrollPresenter;
import com.ivzb.semaphore.conversation.data.MessageEntity;
import com.ivzb.semaphore.conversation.data.MessagesDataSource;

public class ConversationPresenter
        extends EndlessScrollPresenter<MessageEntity, ConversationContract.View, MessagesDataSource>
        implements ConversationContract.Presenter {

    ConversationPresenter(
            @NonNull Context context,
            @NonNull ConversationContract.View view,
            @NonNull MessagesDataSource dataSource) {

        super(context, view, dataSource);
    }

    @Override
    public void sendMessage(final MessageEntity message) {
        if (!mView.isActive()) return;

        if (message == null || message.getMessage() == null || message.getMessage().equals("")) return;

        mView.showErrorMessage(false);
        mView.setLoadingIndicator(true);

        mDataSource.save(message, new SaveCallback<String>() {
            @Override
            public void onSuccess(Result<String> data) {
                    if (!mView.isActive()) return;

                    mView.setLoadingIndicator(false);
                    mView.clearMessage();

                    // nothing to do here, service should handle receive
                }

                @Override
                public void onFailure(String message) {
                    if (!mView.isActive()) return;

                    mView.setLoadingIndicator(false);
                    mView.setErrorMessage(message);
                    mView.showErrorMessage(true);
                }
            });
    }
}

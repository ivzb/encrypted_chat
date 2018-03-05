package com.ivzb.encrypted_chat.conversation.ui;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ivzb.encrypted_chat._base.ui.endless.DefaultEndlessScrollPresenter;
import com.ivzb.encrypted_chat.conversation.data.MessageEntity;
import com.ivzb.encrypted_chat.conversation.data.MessagesDataSource;

public class ConversationPresenter
        extends DefaultEndlessScrollPresenter<MessageEntity, ConversationContract.View, MessagesDataSource>
        implements ConversationContract.Presenter {

    protected static final int REQUEST_USER_SAVE = 301;
    protected static final int RESULT_OK = -1;

    public ConversationPresenter(
            @NonNull Context context,
            @NonNull ConversationContract.View view,
            @NonNull MessagesDataSource dataSource) {

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
    public void sendMessage(MessageEntity message) {
        // todo
    }
}

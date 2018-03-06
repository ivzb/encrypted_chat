package com.ivzb.semaphore.conversation.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.ivzb.semaphore.R;
import com.ivzb.semaphore._base.ui.endless.DefaultEndlessScrollViewModel;
import com.ivzb.semaphore.conversation.data.MessageEntity;

public class ConversationViewModel
        extends DefaultEndlessScrollViewModel<MessageEntity>
        implements ConversationContract.ViewModel {

    private static final String MESSAGE_TEXT_STATE = "message_text_state";

    private EditText mEtMessage;
    private ImageView mIvSendMessage;

    @Override
    public void init(View view) {
        super.init(view);

        mEtMessage = view.findViewById(R.id.etMessage);
        mIvSendMessage = view.findViewById(R.id.ivSendMessage);
    }

    @Override
    public void saveInstanceState(Bundle outState) {
        super.saveInstanceState(outState);

        saveMessageState(outState);
    }

    private void saveMessageState(Bundle outState) {
        String messageText = mEtMessage.getText().toString();
        outState.putString(MESSAGE_TEXT_STATE, messageText);
    }

    @Override
    public void restoreInstanceState(Bundle savedInstanceState) {
        super.restoreInstanceState(savedInstanceState);

        if (savedInstanceState == null) return;

        restoreMessageState(savedInstanceState);
    }

    private void restoreMessageState(Bundle state) {
        if (state.containsKey(MESSAGE_TEXT_STATE)) {
            String message = state.getString(MESSAGE_TEXT_STATE);
            getEtMessage().setText(message);
        }
    }

    @Override
    public EditText getEtMessage() {
        return mEtMessage;
    }

    @Override
    public ImageView getIvSendMessage() {
        return mIvSendMessage;
    }
}

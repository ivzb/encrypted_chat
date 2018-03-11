package com.ivzb.semaphore.conversation.ui;

import android.widget.EditText;
import android.widget.ImageView;

import com.ivzb.semaphore._base.ui._contracts.endless.BaseEndlessScrollPresenter;
import com.ivzb.semaphore._base.ui._contracts.endless.BaseEndlessScrollView;
import com.ivzb.semaphore._base.ui._contracts.endless.BaseEndlessScrollViewModel;
import com.ivzb.semaphore.conversation.data.MessageEntity;

public class ConversationContract {

    public interface View extends BaseEndlessScrollView<MessageEntity, Presenter, ViewModel> {

        void clearMessage();

        void onClickSendMessage(MessageEntity message);
        void onClickShowMessageDetails(MessageEntity message);
    }

    public interface Presenter extends BaseEndlessScrollPresenter<MessageEntity> {

        void sendMessage(MessageEntity message);
    }

    public interface ViewModel extends BaseEndlessScrollViewModel<MessageEntity> {

        EditText getEtMessage();
        ImageView getIvSendMessage();
    }
}

package com.ivzb.encrypted_chat.conversation.ui;

import com.ivzb.encrypted_chat._base.ui._contracts.endless.BaseEndlessScrollPresenter;
import com.ivzb.encrypted_chat._base.ui._contracts.endless.BaseEndlessScrollView;
import com.ivzb.encrypted_chat._base.ui._contracts.endless.BaseEndlessScrollViewModel;
import com.ivzb.encrypted_chat.conversation.data.MessageEntity;

public class ConversationContract {

    public interface View extends BaseEndlessScrollView<MessageEntity, Presenter, ViewModel> {

        void onSendMessage(MessageEntity message);
        void onClickMessage(MessageEntity message);
    }

    public interface Presenter extends BaseEndlessScrollPresenter<MessageEntity> {

        void sendMessage(MessageEntity message);
    }

    public interface ViewModel extends BaseEndlessScrollViewModel<MessageEntity> {

    }
}

package com.ivzb.encrypted_chat.conversations.data;

import com.ivzb.encrypted_chat._base.data._contracts.sources.ReceiveDataSource;
import com.ivzb.encrypted_chat._base.data.callbacks.SaveCallback;

public interface ConversationsDataSource extends ReceiveDataSource<ConversationEntity> {

    void remove(String id, SaveCallback<Boolean> callback);
}

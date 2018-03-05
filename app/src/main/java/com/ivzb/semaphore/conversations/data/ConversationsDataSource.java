package com.ivzb.semaphore.conversations.data;

import com.ivzb.semaphore._base.data._contracts.sources.ReceiveDataSource;
import com.ivzb.semaphore._base.data.callbacks.SaveCallback;

public interface ConversationsDataSource extends ReceiveDataSource<ConversationEntity> {

    void remove(String id, SaveCallback<Boolean> callback);
}

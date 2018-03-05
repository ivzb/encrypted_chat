package com.ivzb.semaphore._base.data._contracts.strategies;

import com.ivzb.semaphore.auth.data.AuthDataSource;
import com.ivzb.semaphore.conversation.data.MessagesDataSource;
import com.ivzb.semaphore.conversations.data.ConversationsDataSource;
import com.ivzb.semaphore.users.data.UsersDataSource;

public interface BaseDataSourcesStrategy {

    AuthDataSource auth();

    UsersDataSource users();

    ConversationsDataSource conversations();

    MessagesDataSource messages();
}
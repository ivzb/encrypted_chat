package com.ivzb.semaphore._base.data.strategies;

import com.ivzb.semaphore._base.data._contracts.strategies.BaseDataSourcesStrategy;
import com.ivzb.semaphore.auth.data.AuthDataSource;
import com.ivzb.semaphore.auth.data.AuthRemoteDataSource;
import com.ivzb.semaphore.conversation.data.MessagesDataSource;
import com.ivzb.semaphore.conversation.data.MessagesRemoteDataSource;
import com.ivzb.semaphore.conversations.data.ConversationsDataSource;
import com.ivzb.semaphore.conversations.data.ConversationsRemoteDataSource;
import com.ivzb.semaphore.users.data.UsersDataSource;
import com.ivzb.semaphore.users.data.UsersRemoteDataSource;

public class RemoteDataSourcesStrategy implements BaseDataSourcesStrategy {

    @Override
    public AuthDataSource auth() {
        return AuthRemoteDataSource.getInstance();
    }

    @Override
    public UsersDataSource users() {
        return UsersRemoteDataSource.getInstance();
    }

    @Override
    public ConversationsDataSource conversations() {
        return ConversationsRemoteDataSource.getInstance();
    }

    @Override
    public MessagesDataSource messages() {
        return MessagesRemoteDataSource.getInstance();
    }
}

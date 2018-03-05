package com.ivzb.semaphore._base.data;

import com.ivzb.semaphore._base.data._contracts.strategies.BaseDataSourcesStrategy;
import com.ivzb.semaphore.auth.data.AuthDataSource;
import com.ivzb.semaphore.conversation.data.MessagesDataSource;
import com.ivzb.semaphore.conversations.data.ConversationsDataSource;
import com.ivzb.semaphore.users.data.UsersDataSource;

import static com.ivzb.semaphore.utils.Preconditions.checkNotNull;

/**
 *
 * DataSources uses different strategies to return correct data source
 *
 */
public class DataSources implements BaseDataSourcesStrategy {

    private static DataSources sINSTANCE;

    private BaseDataSourcesStrategy mStrategy;

    public static DataSources getInstance() {
        return checkNotNull(sINSTANCE);
    }

    public static void createInstance(BaseDataSourcesStrategy strategy) {
        sINSTANCE = new DataSources(strategy);
    }

    public static void destroyInstance() {
        sINSTANCE = null;
    }

    private DataSources(BaseDataSourcesStrategy strategy) {
        mStrategy = strategy;
    }

    @Override
    public AuthDataSource auth() {
        return mStrategy.auth();
    }

    @Override
    public UsersDataSource users() {
        return mStrategy.users();
    }

    @Override
    public ConversationsDataSource conversations() {
        return mStrategy.conversations();
    }

    @Override
    public MessagesDataSource messages() {
        return mStrategy.messages();
    }
}
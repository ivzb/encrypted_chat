package com.ivzb.encrypted_chat._base.data;

import com.ivzb.encrypted_chat._base.data._contracts.strategies.BaseDataSourcesStrategy;
import com.ivzb.encrypted_chat.auth.data.AuthDataSource;
import com.ivzb.encrypted_chat.conversations.data.ConversationsDataSource;
import com.ivzb.encrypted_chat.users.data.UsersDataSource;

import static com.ivzb.encrypted_chat.utils.Preconditions.checkNotNull;

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
}
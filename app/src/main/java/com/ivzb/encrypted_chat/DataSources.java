package com.ivzb.encrypted_chat;

import com.ivzb.encrypted_chat.auth.data.AuthDataSource;
import com.ivzb.encrypted_chat.users.data.UsersDataSource;

import static com.ivzb.encrypted_chat.utils.Preconditions.checkNotNull;

/**
 *
 * DataSources uses different strategies to return correct data source
 *
 */
public class DataSources implements DataSourcesStrategy {

    private static DataSources sINSTANCE;

    private DataSourcesStrategy mStrategy;

    public static DataSources getInstance() {
        return checkNotNull(sINSTANCE);
    }

    public static void createInstance(DataSourcesStrategy strategy) {
        sINSTANCE = new DataSources(strategy);
    }

    public static void destroyInstance() {
        sINSTANCE = null;
    }

    private DataSources(DataSourcesStrategy strategy) {
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
}
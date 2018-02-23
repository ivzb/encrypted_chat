package com.ivzb.encrypted_chat;

import com.ivzb.encrypted_chat.auth.data.AuthDataSource;
import com.ivzb.encrypted_chat.auth.data.AuthRemoteDataSource;
import com.ivzb.encrypted_chat.users.data.UsersDataSource;
import com.ivzb.encrypted_chat.users.data.UsersRemoteDataSource;

public class RemoteStrategy implements DataSourcesStrategy {

    @Override
    public AuthDataSource auth() {
        return AuthRemoteDataSource.getInstance();
    }

    @Override
    public UsersDataSource users() {
        return UsersRemoteDataSource.getInstance();
    }
}

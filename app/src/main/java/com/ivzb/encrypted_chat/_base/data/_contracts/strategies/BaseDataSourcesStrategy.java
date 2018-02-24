package com.ivzb.encrypted_chat._base.data._contracts.strategies;

import com.ivzb.encrypted_chat.auth.data.AuthDataSource;
import com.ivzb.encrypted_chat.users.data.UsersDataSource;

public interface BaseDataSourcesStrategy {

    AuthDataSource auth();

    UsersDataSource users();
}
package com.ivzb.encrypted_chat;

import com.ivzb.encrypted_chat.auth.data.AuthDataSource;
import com.ivzb.encrypted_chat.users.data.UsersDataSource;

public interface DataSourcesStrategy {

    AuthDataSource auth();

    UsersDataSource users();
}
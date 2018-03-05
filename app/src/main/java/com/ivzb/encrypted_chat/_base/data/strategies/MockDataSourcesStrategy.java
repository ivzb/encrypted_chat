package com.ivzb.encrypted_chat._base.data.strategies;

import com.ivzb.encrypted_chat._base.data._contracts.seed.BaseSeed;
import com.ivzb.encrypted_chat._base.data._contracts.strategies.BaseDataSourcesStrategy;
import com.ivzb.encrypted_chat._base.data.generators.DefaultGeneratorConfig;
import com.ivzb.encrypted_chat._base.data.seed.DefaultSeed;
import com.ivzb.encrypted_chat.auth.data.AuthDataSource;
import com.ivzb.encrypted_chat.auth.data.AuthMockDataSource;
import com.ivzb.encrypted_chat.conversation.data.MessagesDataSource;
import com.ivzb.encrypted_chat.conversation.data.MessagesMockDataSource;
import com.ivzb.encrypted_chat.conversations.data.ConversationsDataSource;
import com.ivzb.encrypted_chat.conversations.data.ConversationsMockDataSource;
import com.ivzb.encrypted_chat.users.data.UsersDataSource;
import com.ivzb.encrypted_chat.users.data.UsersMockDataSource;

import java.util.Random;

import io.bloco.faker.Faker;

public class MockDataSourcesStrategy implements BaseDataSourcesStrategy {

    public MockDataSourcesStrategy() {
        DefaultGeneratorConfig.initialize(new Random(), new Faker());
        BaseSeed seed = new DefaultSeed(DefaultGeneratorConfig.getInstance());
        seed.seed();
    }

    @Override
    public AuthDataSource auth() {
        return AuthMockDataSource.getInstance();
    }

    @Override
    public UsersDataSource users() {
        return UsersMockDataSource.getInstance();
    }

    @Override
    public ConversationsDataSource conversations() {
        return ConversationsMockDataSource.getInstance();
    }

    @Override
    public MessagesDataSource messages() {
        return MessagesMockDataSource.getInstance();
    }
}
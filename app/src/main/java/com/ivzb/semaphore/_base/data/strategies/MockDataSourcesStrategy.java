package com.ivzb.semaphore._base.data.strategies;

import com.ivzb.semaphore._base.data._contracts.seed.BaseSeed;
import com.ivzb.semaphore._base.data._contracts.strategies.BaseDataSourcesStrategy;
import com.ivzb.semaphore._base.data.generators.DefaultGeneratorConfig;
import com.ivzb.semaphore._base.data.seed.DefaultSeed;
import com.ivzb.semaphore.auth.data.AuthDataSource;
import com.ivzb.semaphore.auth.data.AuthMockDataSource;
import com.ivzb.semaphore.conversation.data.MessagesDataSource;
import com.ivzb.semaphore.conversation.data.MessagesMockDataSource;
import com.ivzb.semaphore.conversations.data.ConversationsDataSource;
import com.ivzb.semaphore.conversations.data.ConversationsMockDataSource;
import com.ivzb.semaphore.users.data.UsersDataSource;
import com.ivzb.semaphore.users.data.UsersMockDataSource;

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
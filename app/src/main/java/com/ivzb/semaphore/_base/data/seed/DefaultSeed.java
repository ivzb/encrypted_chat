package com.ivzb.semaphore._base.data.seed;

import com.ivzb.semaphore._base.data._contracts.generators.BaseGeneratorConfig;
import com.ivzb.semaphore._base.data._contracts.seed.BaseSeed;
import com.ivzb.semaphore._base.data._contracts.sources.mocks.SeedDataSource;
import com.ivzb.semaphore.conversation.data.MessageEntity;
import com.ivzb.semaphore.conversation.data.MessagesMockDataSource;
import com.ivzb.semaphore.conversations.data.ConversationEntity;
import com.ivzb.semaphore.conversations.data.ConversationsMockDataSource;
import com.ivzb.semaphore.users.data.UserEntity;
import com.ivzb.semaphore.users.data.UsersMockDataSource;

public class DefaultSeed implements BaseSeed {

    private final BaseGeneratorConfig mConfig;

    public DefaultSeed(BaseGeneratorConfig config) {
        mConfig = config;
    }

    @Override
    public void seed() {
        ((SeedDataSource<UserEntity>)UsersMockDataSource.getInstance()).seed(null, mConfig.getNumber(30));
        ((SeedDataSource<UserEntity>)UsersMockDataSource.getInstance()).seed(mConfig.getEmail(), mConfig.getNumber(30));

        ((SeedDataSource<ConversationEntity>) ConversationsMockDataSource.getInstance()).seed(null, mConfig.getNumber(30));

        ((SeedDataSource<MessageEntity>) MessagesMockDataSource.getInstance()).seed(null, mConfig.getNumber(30));
    }
}
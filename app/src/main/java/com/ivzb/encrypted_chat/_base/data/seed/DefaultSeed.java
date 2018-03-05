package com.ivzb.encrypted_chat._base.data.seed;

import com.ivzb.encrypted_chat._base.data._contracts.generators.BaseGeneratorConfig;
import com.ivzb.encrypted_chat._base.data._contracts.seed.BaseSeed;
import com.ivzb.encrypted_chat._base.data._contracts.sources.mocks.SeedDataSource;
import com.ivzb.encrypted_chat.conversation.data.MessageEntity;
import com.ivzb.encrypted_chat.conversation.data.MessagesMockDataSource;
import com.ivzb.encrypted_chat.conversations.data.ConversationEntity;
import com.ivzb.encrypted_chat.conversations.data.ConversationsMockDataSource;
import com.ivzb.encrypted_chat.users.data.UserEntity;
import com.ivzb.encrypted_chat.users.data.UsersMockDataSource;

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
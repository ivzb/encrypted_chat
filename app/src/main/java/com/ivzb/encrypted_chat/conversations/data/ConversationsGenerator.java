package com.ivzb.encrypted_chat.conversations.data;

import android.text.TextUtils;

import com.ivzb.encrypted_chat._base.data._contracts.generators.BaseGeneratorConfig;
import com.ivzb.encrypted_chat._base.data.generators.DefaultGenerator;

public class ConversationsGenerator
        extends DefaultGenerator<ConversationEntity> {

    public ConversationsGenerator(BaseGeneratorConfig config) {
        super(config);
    }

    @Override
    public ConversationEntity instantiate() {
        String id = mConfig.getId();
        String name = TextUtils.join(" ", mConfig.getWords(3));

        return new ConversationEntity(id, name);
    }
}

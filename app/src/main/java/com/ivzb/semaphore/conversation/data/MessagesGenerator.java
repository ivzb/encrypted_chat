package com.ivzb.semaphore.conversation.data;

import android.text.TextUtils;

import com.ivzb.semaphore._base.data._contracts.generators.BaseGeneratorConfig;
import com.ivzb.semaphore._base.data.generators.DefaultGenerator;

public class MessagesGenerator
        extends DefaultGenerator<MessageEntity> {

    public MessagesGenerator(BaseGeneratorConfig config) {
        super(config);
    }

    @Override
    public MessageEntity instantiate() {
        String id = mConfig.getId();
        String message = TextUtils.join(" ", mConfig.getWords(3));

        return new MessageEntity(id, message);
    }
}

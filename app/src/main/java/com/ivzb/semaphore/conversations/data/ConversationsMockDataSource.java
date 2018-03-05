package com.ivzb.semaphore.conversations.data;

import com.ivzb.semaphore._base.data.Result;
import com.ivzb.semaphore._base.data._contracts.generators.BaseGeneratorConfig;
import com.ivzb.semaphore._base.data.callbacks.SaveCallback;
import com.ivzb.semaphore._base.data.generators.DefaultGeneratorConfig;
import com.ivzb.semaphore._base.data.sources.mocks.MockLoadDataSource;

import static com.ivzb.semaphore.utils.Preconditions.checkNotNull;

public class ConversationsMockDataSource
        extends MockLoadDataSource<ConversationEntity>
        implements ConversationsDataSource {

    private static ConversationsDataSource sINSTANCE;
    private BaseGeneratorConfig mGeneratorConfig;

    public static ConversationsDataSource getInstance() {
        if (sINSTANCE == null) {
            sINSTANCE = new ConversationsMockDataSource();
        }

        return sINSTANCE;
    }

    public static void destroyInstance() {
        sINSTANCE = null;
    }

    private ConversationsMockDataSource() {
        super(new ConversationsGenerator(DefaultGeneratorConfig.getInstance()));

        mGeneratorConfig = DefaultGeneratorConfig.getInstance();
    }

    @Override
    public void remove(String id, SaveCallback<Boolean> callback) {
        checkNotNull(callback);

        Boolean added = mGeneratorConfig.getBoolean();
        String message = added ? "Conversation removed." : "Couldn't remove conversation.";

        Result<Boolean> result = new Result<>(added, message);
        callback.onSuccess(result);
    }
}

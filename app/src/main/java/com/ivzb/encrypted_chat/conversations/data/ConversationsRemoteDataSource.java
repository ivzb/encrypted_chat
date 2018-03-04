package com.ivzb.encrypted_chat.conversations.data;

import android.support.annotation.NonNull;

import com.ivzb.encrypted_chat._base.data.Result;
import com.ivzb.encrypted_chat._base.data.callbacks.GetCallback;
import com.ivzb.encrypted_chat._base.data.callbacks.LoadCallback;
import com.ivzb.encrypted_chat._base.data.callbacks.SaveCallback;
import com.ivzb.encrypted_chat._base.data.sources.DefaultRemoteDataSource;

import java.util.List;

import retrofit2.Call;

public class ConversationsRemoteDataSource
        extends DefaultRemoteDataSource<ConversationEntity, ConversationsAPI>
        implements ConversationsDataSource {

    private static ConversationsDataSource sINSTANCE;

    public static ConversationsDataSource getInstance() {
        if (sINSTANCE == null) {
            sINSTANCE = new ConversationsRemoteDataSource();
        }

        return sINSTANCE;
    }

    public static void destroyInstance() {
        sINSTANCE = null;
    }

    // Prevent direct instantiation.
    private ConversationsRemoteDataSource() {
        super(ConversationsAPI.class);
    }

    @Override
    public void get(
            final String id,
            final @NonNull GetCallback<ConversationEntity> callback) {

        final Call<Result<ConversationEntity>> call = mApiService.get(id);
        call.enqueue(getCallback(callback));
    }

    @Override
    public void load(
            final String _,
            final int page,
            final @NonNull LoadCallback<ConversationEntity> callback) {

        final Call<Result<List<ConversationEntity>>> call = mApiService.load(page);
        call.enqueue(loadCallback(page, callback));
    }

    @Override
    public void remove(String id, SaveCallback<Boolean> callback) {
        final Call<Result<Boolean>> call = mApiService.remove(id);
        call.enqueue(saveCallback(callback));
    }
}


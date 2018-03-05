package com.ivzb.semaphore.conversation.data;

import android.support.annotation.NonNull;

import com.ivzb.semaphore._base.data.Result;
import com.ivzb.semaphore._base.data.callbacks.GetCallback;
import com.ivzb.semaphore._base.data.callbacks.LoadCallback;
import com.ivzb.semaphore._base.data.callbacks.SaveCallback;
import com.ivzb.semaphore._base.data.sources.DefaultRemoteDataSource;

import java.util.List;

import retrofit2.Call;

public class MessagesRemoteDataSource
        extends DefaultRemoteDataSource<MessageEntity, MessagesAPI>
        implements MessagesDataSource {

    private static MessagesDataSource sINSTANCE;

    public static MessagesDataSource getInstance() {
        if (sINSTANCE == null) {
            sINSTANCE = new MessagesRemoteDataSource();
        }

        return sINSTANCE;
    }

    public static void destroyInstance() {
        sINSTANCE = null;
    }

    // Prevent direct instantiation.
    private MessagesRemoteDataSource() {
        super(MessagesAPI.class);
    }

    @Override
    public void get(
            final String id,
            final @NonNull GetCallback<MessageEntity> callback) {

        final Call<Result<MessageEntity>> call = mApiService.get(id);
        call.enqueue(getCallback(callback));
    }

    @Override
    public void load(
            final String _,
            final int page,
            final @NonNull LoadCallback<MessageEntity> callback) {

        final Call<Result<List<MessageEntity>>> call = mApiService.load(page);
        call.enqueue(loadCallback(page, callback));
    }

    @Override
    public void save(MessageEntity message, SaveCallback<String> callback) {
        final Call<Result<String>> call = mApiService.send(message);
        call.enqueue(saveCallback(callback));
    }
}

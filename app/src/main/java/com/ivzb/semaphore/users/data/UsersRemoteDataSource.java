package com.ivzb.semaphore.users.data;

import android.support.annotation.NonNull;

import com.ivzb.semaphore._base.data.Result;
import com.ivzb.semaphore._base.data.callbacks.GetCallback;
import com.ivzb.semaphore._base.data.callbacks.LoadCallback;
import com.ivzb.semaphore._base.data.callbacks.SaveCallback;
import com.ivzb.semaphore._base.data.sources.DefaultRemoteDataSource;

import java.util.List;

import retrofit2.Call;

public class UsersRemoteDataSource
        extends DefaultRemoteDataSource<UserEntity, UsersAPI>
        implements UsersDataSource {

    private static UsersDataSource sINSTANCE;

    public static UsersDataSource getInstance() {
        if (sINSTANCE == null) {
            sINSTANCE = new UsersRemoteDataSource();
        }

        return sINSTANCE;
    }

    public static void destroyInstance() {
        sINSTANCE = null;
    }

    // Prevent direct instantiation.
    private UsersRemoteDataSource() {
        super(UsersAPI.class);
    }

    @Override
    public void get(
            final String id,
            final @NonNull GetCallback<UserEntity> callback) {

        final Call<Result<UserEntity>> call = mApiService.get(id);
        call.enqueue(getCallback(callback));
    }

    @Override
    public void load(
            final String _,
            final int page,
            final @NonNull LoadCallback<UserEntity> callback) {

        final Call<Result<List<UserEntity>>> call = mApiService.load(page);
        call.enqueue(loadCallback(page, callback));
    }

    @Override
    public void add(String id, SaveCallback<Boolean> callback) {
        final Call<Result<Boolean>> call = mApiService.add(id);
        call.enqueue(saveCallback(callback));
    }

    @Override
    public void remove(String id, SaveCallback<Boolean> callback) {
        final Call<Result<Boolean>> call = mApiService.remove(id);
        call.enqueue(saveCallback(callback));
    }
}
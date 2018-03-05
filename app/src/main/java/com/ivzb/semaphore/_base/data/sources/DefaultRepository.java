package com.ivzb.semaphore._base.data.sources;

import android.support.annotation.NonNull;

import static com.ivzb.semaphore.utils.Preconditions.checkNotNull;

public abstract class DefaultRepository<T> {

    protected final T mRemoteDataSource;
    protected final T mLocalDataSource;

    protected DefaultRepository(
            @NonNull T remoteDataSource,
            @NonNull T localDataSource) {

        mRemoteDataSource = checkNotNull(remoteDataSource);
        mLocalDataSource = checkNotNull(localDataSource);
    }
}
package com.ivzb.semaphore._base.data.sources.mocks;

import android.support.annotation.NonNull;

import com.ivzb.semaphore._base.data.Result;
import com.ivzb.semaphore._base.data._contracts.entities.BaseEntity;
import com.ivzb.semaphore._base.data._contracts.generators.BaseGenerator;
import com.ivzb.semaphore._base.data._contracts.sources.BaseDataSource;
import com.ivzb.semaphore._base.data.callbacks.SaveCallback;

import java.util.ArrayList;

import static com.ivzb.semaphore.utils.Preconditions.checkNotNull;

public abstract class MockSaveDataSource<T extends BaseEntity>
        extends MockLoadDataSource<T>
        implements BaseDataSource<T> {

    protected static String sNoEntityFailMessage = "No entity to save.";

    public MockSaveDataSource(BaseGenerator<T> generator) {
        super(generator);
    }

    @Override
    public void save(
            @NonNull T entity,
            @NonNull SaveCallback<String> callback) {

        checkNotNull(callback);

        if (entity == null) {
            callback.onFailure(sNoEntityFailMessage);
            return;
        }

        entity.setId(String.valueOf(mEntitiesByContainerId.size() + 1));

        mEntitiesById.put(entity.getId(), entity);

        if (!mEntitiesByContainerId.containsKey(entity.getContainerId())) {
            mEntitiesByContainerId.put(entity.getContainerId(), new ArrayList<T>());
        }

        mEntitiesByContainerId.get(entity.getContainerId()).add(entity);
        String message = "entity saved";

        Result<String> result = new Result<>(entity.getId(), message);
        callback.onSuccess(result);
    }
}
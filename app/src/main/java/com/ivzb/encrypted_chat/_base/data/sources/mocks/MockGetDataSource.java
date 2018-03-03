package com.ivzb.encrypted_chat._base.data.sources.mocks;

import android.support.annotation.NonNull;

import com.ivzb.encrypted_chat._base.data.Result;
import com.ivzb.encrypted_chat._base.data._contracts.entities.BaseEntity;
import com.ivzb.encrypted_chat._base.data._contracts.generators.BaseGenerator;
import com.ivzb.encrypted_chat._base.data._contracts.sources.GetDataSource;
import com.ivzb.encrypted_chat._base.data._contracts.sources.mocks.SeedDataSource;
import com.ivzb.encrypted_chat._base.data.callbacks.GetCallback;
import com.ivzb.encrypted_chat._base.data.config.DefaultConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.ivzb.encrypted_chat.utils.Preconditions.checkNotNull;

public abstract class MockGetDataSource<T extends BaseEntity>
        implements GetDataSource<T>, SeedDataSource<T> {

    private BaseGenerator<T> mGenerator;

    protected HashMap<String, List<T>> mEntitiesByContainerId;
    protected HashMap<String, T> mEntitiesById;

    protected static String sDoesNotExistFailMessage = "Entity does not exist.";

    public MockGetDataSource(BaseGenerator<T> generator) {
        mEntitiesByContainerId = new HashMap<>();
        mEntitiesById = new HashMap<>();
        mGenerator = generator;
    }

    @Override
    public void get(
            String id,
            @NonNull GetCallback<T> callback) {

        checkNotNull(callback);

        if (!mEntitiesById.containsKey(id)) {
            callback.onFailure(sDoesNotExistFailMessage);
            return;
        }

        String message = "entity found";

        Result<T> result = new Result<>(mEntitiesById.get(id), message);
        callback.onSuccess(result);
    }

    @Override
    public List<T> seed(String containerId, int size) {
        if (containerId == null) containerId = DefaultConfig.NO_ID;

        int entitiesSize = 0;

        if (mEntitiesByContainerId.containsKey(containerId)) {
            entitiesSize = mEntitiesByContainerId.get(containerId).size();
        }

        int generateSize = entitiesSize + size;

        if (generateSize <= 0) {
            return null;
        }

        List<T> generated = mGenerator.multiple(generateSize);

        if (!mEntitiesByContainerId.containsKey(containerId)) {
            mEntitiesByContainerId.put(containerId, new ArrayList<T>());
        }

        for (T entity: generated) {
            mEntitiesById.put(entity.getId(), entity);
            mEntitiesByContainerId.get(containerId).add(entity);
        }

        return mEntitiesByContainerId.get(containerId);
    }
}
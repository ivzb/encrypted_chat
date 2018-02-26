package com.ivzb.encrypted_chat._base.data.sources.mocks;


import android.support.annotation.NonNull;

import com.ivzb.encrypted_chat._base.data.Result;
import com.ivzb.encrypted_chat._base.data._contracts.entities.BaseEntity;
import com.ivzb.encrypted_chat._base.data._contracts.generators.BaseGenerator;
import com.ivzb.encrypted_chat._base.data._contracts.sources.ReceiveDataSource;
import com.ivzb.encrypted_chat._base.data._contracts.sources.mocks.SeedDataSource;
import com.ivzb.encrypted_chat._base.data.callbacks.LoadCallback;
import com.ivzb.encrypted_chat._base.data.config.DefaultConfig;

import java.util.List;

import static com.ivzb.encrypted_chat.utils.Preconditions.checkNotNull;

public abstract class MockReceiveDataSource<T extends BaseEntity>
        extends MockGetDataSource<T>
        implements ReceiveDataSource<T>, SeedDataSource<T> {

    protected static int sPageSize = 9;
    protected static String sInvalidPageFailMessage = "Please provide non negative page.";

    public MockReceiveDataSource(BaseGenerator<T> generator) {
        super(generator);
    }

    @Override
    public void load(
            String containerId,
            int page,
            @NonNull LoadCallback<T> callback) {

        checkNotNull(callback);

        if (containerId == null) containerId = DefaultConfig.NO_ID;

        if (page < 0) {
            callback.onFailure(sInvalidPageFailMessage);
            return;
        }

        List<T> entities = mEntitiesByContainerId.get(containerId);

        if (entities == null) {
            callback.onNoMore();
            return;
        }

        int start = page * sPageSize;
        int size = entities.size();
        boolean noMore = start > size || size == 0;
        int end = Math.min(start + sPageSize, size);

        if (noMore) {
            callback.onNoMore();
            return;
        }

        List<T> data = entities.subList(start, end);
        Result<List<T>> result = new Result<>(data);

        callback.onSuccess(result, page);
    }
}
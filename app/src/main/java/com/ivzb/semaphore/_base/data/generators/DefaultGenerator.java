package com.ivzb.semaphore._base.data.generators;

import com.ivzb.semaphore._base.data._contracts.generators.BaseGenerator;
import com.ivzb.semaphore._base.data._contracts.generators.BaseGeneratorConfig;

import java.util.ArrayList;
import java.util.List;

public abstract class DefaultGenerator<T>
        implements BaseGenerator<T> {

    protected final BaseGeneratorConfig mConfig;

    public DefaultGenerator(BaseGeneratorConfig config) {
        mConfig = config;
    }

    @Override
    public T single() {
        return instantiate();
    }

    @Override
    public List<T> multiple(int size) {
        List<T> data = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            data.add(single());
        }

        return data;
    }
}
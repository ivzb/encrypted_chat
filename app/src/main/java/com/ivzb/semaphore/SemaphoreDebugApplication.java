package com.ivzb.semaphore;

import com.ivzb.semaphore._base.data.DataSources;
import com.ivzb.semaphore._base.data.strategies.MockDataSourcesStrategy;

public class SemaphoreDebugApplication extends SemaphoreApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        DataSources.createInstance(new MockDataSourcesStrategy());
    }
}

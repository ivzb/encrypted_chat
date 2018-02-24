package com.ivzb.encrypted_chat;

import android.app.Application;

import com.ivzb.encrypted_chat._base.data.DataSources;
import com.ivzb.encrypted_chat._base.data.strategies.MockDataSourcesStrategy;
import com.ivzb.encrypted_chat._base.data.strategies.RemoteDataSourcesStrategy;

public class EncryptedChatApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DataSources.createInstance(new MockDataSourcesStrategy());
    }
}

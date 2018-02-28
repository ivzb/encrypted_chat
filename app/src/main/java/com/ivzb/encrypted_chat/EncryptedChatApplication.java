package com.ivzb.encrypted_chat;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.ivzb.encrypted_chat._base.data.DataSources;
import com.ivzb.encrypted_chat._base.data.strategies.MockDataSourcesStrategy;
import com.ivzb.encrypted_chat._base.data.strategies.RemoteDataSourcesStrategy;

import okhttp3.OkHttpClient;

public class EncryptedChatApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DataSources.createInstance(new MockDataSourcesStrategy());

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        ImagePipelineConfig imagePipelineConfig = OkHttpImagePipelineConfigFactory
                .newBuilder(this, okHttpClient)
                .setDownsampleEnabled(true)
                .build();

        Fresco.initialize(this, imagePipelineConfig);
    }
}

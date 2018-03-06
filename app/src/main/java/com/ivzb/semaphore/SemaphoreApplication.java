package com.ivzb.semaphore;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.ivzb.semaphore._base.data.DataSources;
import com.ivzb.semaphore._base.data.strategies.MockDataSourcesStrategy;

import okhttp3.OkHttpClient;

public class SemaphoreApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        ImagePipelineConfig imagePipelineConfig = OkHttpImagePipelineConfigFactory
                .newBuilder(this, okHttpClient)
                .setDownsampleEnabled(true)
                .build();

        Fresco.initialize(this, imagePipelineConfig);
    }

    protected boolean isUnitTesting() {
        return false;
    }
}

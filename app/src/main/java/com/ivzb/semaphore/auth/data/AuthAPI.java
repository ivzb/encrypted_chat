package com.ivzb.semaphore.auth.data;

import com.ivzb.semaphore.BuildConfig;
import com.ivzb.semaphore._base.data.Result;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthAPI {

    @POST(BuildConfig.API_VERSION + "/user/auth")
    Call<Result<String>> auth(@Body AuthEntity auth);

    @POST(BuildConfig.API_VERSION + "/user/create")
    Call<Result<String>> create(@Body AuthEntity auth);
}
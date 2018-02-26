package com.ivzb.encrypted_chat.auth.data;

import com.ivzb.encrypted_chat.BuildConfig;
import com.ivzb.encrypted_chat._base.data.Result;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthAPI {

    @POST(BuildConfig.API_VERSION + "/user/auth")
    Call<Result<String>> auth(@Body AuthEntity auth);

    @POST(BuildConfig.API_VERSION + "/user/create")
    Call<Result<String>> create(@Body AuthEntity auth);
}
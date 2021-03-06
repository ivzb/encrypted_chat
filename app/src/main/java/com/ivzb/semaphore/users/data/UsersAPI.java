package com.ivzb.semaphore.users.data;

import com.ivzb.semaphore.BuildConfig;
import com.ivzb.semaphore._base.data.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UsersAPI {

    @GET(BuildConfig.API_VERSION + "/users")
    Call<Result<List<UserEntity>>> load(@Query("page") int page);

    @GET(BuildConfig.API_VERSION + "/user/{id}")
    Call<Result<UserEntity>> get(@Path("id") String id);

    @POST(BuildConfig.API_VERSION + "/users/append")
    Call<Result<Boolean>> add(@Field("id") String id);

    @POST(BuildConfig.API_VERSION + "/users/remove")
    Call<Result<Boolean>> remove(@Field("id") String id);
}
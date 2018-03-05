package com.ivzb.encrypted_chat.conversation.data;

import com.ivzb.encrypted_chat.BuildConfig;
import com.ivzb.encrypted_chat._base.data.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MessagesAPI {

    @GET(BuildConfig.API_VERSION + "/messages")
    Call<Result<List<MessageEntity>>> load(@Query("page") int page);

    @GET(BuildConfig.API_VERSION + "/message/{id}")
    Call<Result<MessageEntity>> get(@Path("id") String id);

    @POST(BuildConfig.API_VERSION + "/message/send")
    Call<Result<String>> send(@Body MessageEntity message);
}

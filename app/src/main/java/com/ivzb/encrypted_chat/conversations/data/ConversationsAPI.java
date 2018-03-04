package com.ivzb.encrypted_chat.conversations.data;

import com.ivzb.encrypted_chat.BuildConfig;
import com.ivzb.encrypted_chat._base.data.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ConversationsAPI {

    @GET(BuildConfig.API_VERSION + "/conversations")
    Call<Result<List<ConversationEntity>>> load(@Query("page") int page);

    @GET(BuildConfig.API_VERSION + "/conversation/{id}")
    Call<Result<ConversationEntity>> get(@Path("id") String id);

    @POST(BuildConfig.API_VERSION + "/conversation/remove")
    Call<Result<Boolean>> remove(@Field("id") String id);
}

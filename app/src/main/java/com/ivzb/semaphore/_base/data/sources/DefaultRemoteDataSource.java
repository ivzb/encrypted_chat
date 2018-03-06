package com.ivzb.semaphore._base.data.sources;

import com.google.gson.Gson;
import com.ivzb.semaphore._base.data.RESTClient;
import com.ivzb.semaphore._base.data.Result;
import com.ivzb.semaphore._base.data.callbacks.GetCallback;
import com.ivzb.semaphore._base.data.callbacks.LoadCallback;
import com.ivzb.semaphore._base.data.callbacks.SaveCallback;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DefaultRemoteDataSource<M, API> {

    private static final String sNetworkError = "Server could not be reached. Please try again.";

    protected API mApiService;

    // Prevent direct instantiation.
    protected DefaultRemoteDataSource(final Class<API> service) {
        mApiService = RESTClient
                .getClient()
                .create(service);
    }

    protected Callback<Result<M>> getCallback(final GetCallback<M> callback) {
        return new Callback<Result<M>>() {
            @Override
            public void onResponse(Call<Result<M>> call, Response<Result<M>> response) {
                int statusCode = response.code();

                if (statusCode != 200) {
                    Result result = new Gson().fromJson(
                            response.errorBody().charStream(),
                            Result.class);

                    callback.onFailure(result.getMessage());
                    return;
                }

                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Result<M>> call, Throwable t) {
                callback.onFailure(sNetworkError);
            }
        };
    }

    protected Callback<Result<List<M>>> loadCallback(
            final LoadCallback<M> callback) {

        return new Callback<Result<List<M>>>() {
            @Override
            public void onResponse(Call<Result<List<M>>> call, Response<Result<List<M>>> response) {
                int statusCode = response.code();

                if (statusCode != 200) {
                    callback.onFailure(response.message());
                    return;
                }

                Result<List<M>> result = response.body();

                if (result.getResults() == null) {
                    callback.onNoMore();
                    return;
                }

                callback.onSuccess(result);
            }

            @Override
            public void onFailure(Call<Result<List<M>>> call, Throwable t) {
                callback.onFailure(sNetworkError);
            }
        };
    }

    protected <T> Callback<Result<T>> saveCallback(
            final SaveCallback<T> callback) {

        return new Callback<Result<T>>() {
            @Override
            public void onResponse(Call<Result<T>> call, Response<Result<T>> response) {
                int statusCode = response.code();

                if (statusCode != 200) {
                    callback.onFailure(response.message());
                    return;
                }

                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Result<T>> call, Throwable t) {
                callback.onFailure(sNetworkError);
            }
        };
    }
}
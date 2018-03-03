package com.ivzb.encrypted_chat._base.data;

import com.google.gson.annotations.SerializedName;

public class Result<T> {

    @SerializedName("message")
    String message;

    @SerializedName("results")
    T results;

    public Result(T results, String message) {
        this.results = results;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public T getResults() {
        return results;
    }
}
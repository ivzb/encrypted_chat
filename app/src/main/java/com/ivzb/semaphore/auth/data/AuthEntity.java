package com.ivzb.semaphore.auth.data;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel(analyze = { AuthEntity.class })
public class AuthEntity {

    @SerializedName("email")
    String email;

    @SerializedName("password")
    String password;

    public AuthEntity() {

    }

    public AuthEntity(
            String email,
            String password) {

        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
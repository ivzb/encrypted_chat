package com.ivzb.encrypted_chat.users.data;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel(analyze = { com.ivzb.encrypted_chat.users.data.UserEntity.class })
public class UserEntity {

    @SerializedName("id")
    String id;

    @SerializedName("email")
    String email;

    @SerializedName("public_key")
    String publicKey;

    public UserEntity() {

    }

    public UserEntity(
            String id,
            String email,
            String publicKey) {

        this.id = id;
        this.email = email;
        this.publicKey = publicKey;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPublicKey() {
        return publicKey;
    }
}
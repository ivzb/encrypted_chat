package com.ivzb.semaphore.users.data;

import com.google.gson.annotations.SerializedName;
import com.ivzb.semaphore._base.data._contracts.entities.BaseEntity;

import org.parceler.Parcel;

@Parcel(analyze = { com.ivzb.semaphore.users.data.UserEntity.class })
public class UserEntity implements BaseEntity {

    @SerializedName("id")
    String id;

    @SerializedName("email")
    String email;

    @SerializedName("public_key")
    String publicKey;

    @SerializedName("is_friend")
    boolean isFriend;

    public UserEntity() {

    }

    public UserEntity(String id) {
        this.id = id;
    }

    public UserEntity(
            String id,
            String email,
            boolean isFriend) {

        this.id = id;
        this.email = email;
        this.isFriend = isFriend;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public boolean isFriend() {
        return isFriend;
    }

    @Override
    public String getContainerId() {
        return null;
    }
}
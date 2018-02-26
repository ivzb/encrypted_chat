package com.ivzb.encrypted_chat.users.data;

import com.google.gson.annotations.SerializedName;
import com.ivzb.encrypted_chat._base.data._contracts.entities.BaseEntity;

import org.parceler.Parcel;

@Parcel(analyze = { com.ivzb.encrypted_chat.users.data.UserEntity.class })
public class UserEntity implements BaseEntity {

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
            String email) {
            //String publicKey) {

        this.id = id;
        this.email = email;
        //this.publicKey = publicKey;
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

    @Override
    public String getContainerId() {
        return null;
    }
}
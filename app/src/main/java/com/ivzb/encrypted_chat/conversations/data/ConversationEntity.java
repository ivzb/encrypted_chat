package com.ivzb.encrypted_chat.conversations.data;

import com.google.gson.annotations.SerializedName;
import com.ivzb.encrypted_chat._base.data._contracts.entities.BaseEntity;

import org.parceler.Parcel;

@Parcel(analyze = { com.ivzb.encrypted_chat.users.data.UserEntity.class })
public class ConversationEntity implements BaseEntity {

    @SerializedName("id")
    String id;

    @SerializedName("name")
    String name;

    @SerializedName("public_key")
    String publicKey;

    public ConversationEntity() {

    }

    public ConversationEntity(
            String id,
            String name) {

        this.id = id;
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPublicKey() {
        return publicKey;
    }

    @Override
    public String getContainerId() {
        return null;
    }
}

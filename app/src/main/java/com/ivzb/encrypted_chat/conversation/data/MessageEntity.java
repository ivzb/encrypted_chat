package com.ivzb.encrypted_chat.conversation.data;

import com.google.gson.annotations.SerializedName;
import com.ivzb.encrypted_chat._base.data._contracts.entities.BaseEntity;

import org.parceler.Parcel;

@Parcel(analyze = { com.ivzb.encrypted_chat.users.data.UserEntity.class })
public class MessageEntity implements BaseEntity {

    @SerializedName("id")
    String id;

    @SerializedName("message")
    String message;

    public MessageEntity() {

    }

    public MessageEntity(
            String id,
            String message) {

        this.id = id;
        this.message = message;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String getContainerId() {
        return null;
    }
}

package com.ivzb.semaphore.conversation.data;

import com.google.gson.annotations.SerializedName;
import com.ivzb.semaphore._base.data._contracts.entities.BaseEntity;

import org.parceler.Parcel;

import java.util.Date;

@Parcel(analyze = { com.ivzb.semaphore.users.data.UserEntity.class })
public class MessageEntity implements BaseEntity {

    @SerializedName("id")
    String id;

    @SerializedName("message")
    String message;

    @SerializedName("is_own")
    boolean isOwn;

    @SerializedName("created_at")
    Date createdAt;

    public MessageEntity() {

    }

    public MessageEntity(
            String id,
            String message,
            Boolean isOwn,
            Date createdAt) {

        this.id = id;
        this.message = message;
        this.isOwn = isOwn;
        this.createdAt = createdAt;
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

    public boolean isOwn() {
        return isOwn;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public String getContainerId() {
        return null;
    }
}

package com.lite.chatapp.models;

import com.google.gson.annotations.SerializedName;

public class Message {

    @SerializedName("success")
    private String success;
    @SerializedName("errorMessage")
    private String errorMessage;
    @SerializedName("message")
    private MessageBot message;

    public Message(String mSuccess, String mExternalId, MessageBot message) {
        this.success = mSuccess;
        this.errorMessage = mExternalId;
        this.message = message;
    }

    public MessageBot getMessage() {
        return message;
    }

    public void setMessage(MessageBot message) {
        this.message = message;
    }

    public String getmMessage() {
        return success;
    }

    public void setmMessage(String mMessage) {
        this.success = mMessage;
    }

    public String getmExternalId() {
        return errorMessage;
    }

    public void setmExternalId(String mExternalId) {
        this.errorMessage = mExternalId;
    }

}

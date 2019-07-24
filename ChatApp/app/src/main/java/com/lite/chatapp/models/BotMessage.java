package com.lite.chatapp.models;

import com.google.gson.annotations.SerializedName;

public class BotMessage {

    @SerializedName("chatBotName")
    private String chatBotName;
    @SerializedName("chatbotId")
    private String chatBotId;
    @SerializedName("message")
    private String message;
    @SerializedName("emotion")
    private String emotion;

    public String getChatBotId() {
        return chatBotId;
    }

    public void setChatBotId(String chatBotId) {
        this.chatBotId = chatBotId;
    }

    public String getChatBotName() {
        return chatBotName;
    }

    public void setChatBotName(String chatBotName) {
        this.chatBotName = chatBotName;
    }

    public String getChatBotMessage() {
        return message;
    }

    public void setChatBotMessage(String chatBotMessage) {
        this.message = chatBotMessage;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }


}

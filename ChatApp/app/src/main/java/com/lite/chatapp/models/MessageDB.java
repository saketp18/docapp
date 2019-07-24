package com.lite.chatapp.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * Created by Saket on 24,July,2019
 */
@Entity
public class MessageDB implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "messageId")
    private int messageId;

    private String chatbotId;
    private String message;
    private int senderType;

    public String getChatbotId() {
        return chatbotId;
    }

    public void setChatbotId(String chatbotId) {
        this.chatbotId = chatbotId;
    }

    private String chatBotName;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSenderType() {
        return senderType;
    }

    public void setSenderType(int senderType) {
        this.senderType = senderType;
    }

    public String getChatBotName() {
        return chatBotName;
    }

    public void setChatBotName(String chatBotName) {
        this.chatBotName = chatBotName;
    }

    @Override
    public String toString() {
        return "MessageDB{" +
                "messageId=" + messageId +
                ", chatbotId='" + chatbotId + '\'' +
                ", message='" + message + '\'' +
                ", senderType=" + senderType +
                ", chatBotName='" + chatBotName + '\'' +
                '}';
    }
}

package com.lite.chatapp.models;

public class UIMessage {

    private String mMessage;
    private int type;

    public UIMessage(String mMessage, int type) {
        this.mMessage = mMessage;
        this.type = type;
    }

    public String getmMessage() {
        return mMessage;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

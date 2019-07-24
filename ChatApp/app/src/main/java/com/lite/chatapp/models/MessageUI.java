package com.lite.chatapp.models;

public class MessageUI {

    private MessageBot mMessage;
    private int type;

    public MessageUI(MessageBot mMessage, int type) {
        this.mMessage = mMessage;
        this.type = type;
    }

    public MessageBot getmMessage() {
        return mMessage;
    }

    public void setmMessage(MessageBot mMessage) {
        this.mMessage = mMessage;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

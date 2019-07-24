package com.lite.chatapp.presenter;

import com.lite.chatapp.models.MessageBot;

public interface Interactor {

     interface ChatResponse {
        void onSuccessData(MessageBot message, int type);
        void onFailureData();
    }

    interface UserFragList{
         void getUser(String user);
    }

    interface SendUserList{
         void sendUser(String user);
    }

    interface DBUpdateListener{
         void onDataUpdate();
    }
}

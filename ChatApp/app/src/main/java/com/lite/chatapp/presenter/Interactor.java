package com.lite.chatapp.presenter;

public interface Interactor {

     interface ChatResponse {

        void onSuccessData(String message, int type);
        void onFailureData();
    }
}

package com.lite.chatapp.presenter;

import com.lite.chatapp.models.Message;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface GetMessage {

    @GET("/api/chat/")
    Call<Message> getMessage(@QueryMap Map<String, String> options);
}

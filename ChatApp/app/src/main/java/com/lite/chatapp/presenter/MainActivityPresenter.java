package com.lite.chatapp.presenter;

import android.util.Log;

import com.lite.chatapp.core.RetrofitClient;
import com.lite.chatapp.core.Utils;
import com.lite.chatapp.models.Message;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * Created by Saket on 24,July,2019
 */

public class MainActivityPresenter {

    Interactor.ChatResponse mInteractor;

    public MainActivityPresenter(Interactor.ChatResponse interactor) {
        this.mInteractor = interactor;
    }

    /**
     * Initiates retrofitClient and queries the url for response
     * @param message returns the Message.class received from bot.
     */
    public void callChatBot(String message) {
        Map<String, String> data = new HashMap<>();
        data.put("apiKey", "6nt5d1nJHkqbkphe");
        data.put("externalID", "chirag1");
        data.put("chatBotID", "63906");
        data.put("message", message);
        GetMessage service = RetrofitClient.getRetrofitInstance().create(GetMessage.class);
        Call<Message> call = service.getMessage(data);
        call.enqueue(new Callback<Message>() {

            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if (response != null) {
                    mInteractor.onSuccessData(response.body().getMessage(), Utils.SENDER.BOT.ordinal());
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                mInteractor.onFailureData();
                t.printStackTrace();
            }
        });
    }
}

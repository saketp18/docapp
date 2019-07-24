package com.lite.chatapp.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.lite.chatapp.models.MessageDB;

import java.util.List;

/**
 * Created by Saket on 24,July,2019
 */
public class MessageRepo {

    private String DB_NAME = "db_message";

    private MessageDataBase messageDataBase;
    public MessageRepo(Context context) {
        messageDataBase = Room.databaseBuilder(context, MessageDataBase.class, DB_NAME).build();
    }

    public void insetMessage(String chatBotId, String chatmessage, int sendertype, String chatbotname) {

        MessageDB message = new MessageDB();
        message.setChatbotId(chatBotId);
        message.setMessage(chatmessage);
        message.setSenderType(sendertype);
        message.setChatBotName(chatbotname);
        insertTask(message);

    }

    public void insertTask(final MessageDB messageDB) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                messageDataBase.daoAccess().insertMessage(messageDB);
                return null;
            }
        }.execute();
    }

    public LiveData<List<MessageDB>> getMessage(String bot, String self){
        return messageDataBase.daoAccess().fetchAllMessage(bot, self);
    }

    public LiveData<List<String>> getUsers(String name){
        return messageDataBase.daoAccess().getUsers(name);
    }
}
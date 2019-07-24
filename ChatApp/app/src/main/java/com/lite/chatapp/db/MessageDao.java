package com.lite.chatapp.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.lite.chatapp.models.MessageDB;

import java.util.List;

/**
 * Created by Saket on 24,July,2019
 */
@Dao
public interface MessageDao {

    @Insert
    Long insertMessage(MessageDB note);

    //Update this query for version3 for particular users
    @Query("SELECT * FROM MessageDB WHERE chatBotName =:bot OR chatBotName =:self")
    LiveData<List<MessageDB>> fetchAllMessage(String bot, String self);

    @Query("SELECT DISTINCT chatBotName FROM MessageDB WHERE NOT chatBotName =:name")
    LiveData<List<String>> getUsers(String name);
}

package com.lite.chatapp.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.lite.chatapp.models.MessageDB;

/**
 * Created by Saket on 24,July,2019
 */

@Database(entities = {MessageDB.class}, version = 1, exportSchema = false)
public abstract class MessageDataBase extends RoomDatabase {

    public abstract MessageDao daoAccess();
}
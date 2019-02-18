package com.swx.jetpackxbasic;

import android.content.Context;
import android.util.Log;

import com.swx.jetpackxbasic.db.dao.NewsDetailDao;
import com.swx.jetpackxbasic.db.entity.NewsDetailEntity;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import timber.log.Timber;

/**
 * Created by swx on 2019/1/31.
 * Mail: dlut_frank@163.com
 * Copyright (c) 2019 .
 */

@Database(entities = { NewsDetailEntity.class }, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract NewsDetailDao newsDetailDao();

    public static final String db_name = "zhihu_db";

    public static AppDataBase _instance;

    public static AppDataBase getInstance(Context context) {
        if(_instance == null){
            synchronized (AppDataBase.class) {
                if(_instance == null){
                    _instance = buildDatabase(context);
                }
            }
        }
        return  _instance;
    }

    private static  AppDataBase buildDatabase(final Context context){
        return Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class, db_name)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Timber.d("db crated");
                    }
                })
                .build();
    }
}

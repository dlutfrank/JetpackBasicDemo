package com.swx.jetpackxbasic;

import com.swx.jetpackxbasic.db.dao.NewsDetailDao;
import com.swx.jetpackxbasic.db.entity.NewsDetailEntity;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * Created by swx on 2019/1/31.
 * Mail: dlut_frank@163.com
 * Copyright (c) 2019 .
 */

@Database(entities = { NewsDetailEntity.class }, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract NewsDetailDao newsDetailDao();
}

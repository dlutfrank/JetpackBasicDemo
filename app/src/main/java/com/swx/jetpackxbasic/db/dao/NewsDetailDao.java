package com.swx.jetpackxbasic.db.dao;

import com.swx.jetpackxbasic.db.entity.NewsDetailEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.*;

/**
 * Created by swx on 2019/1/31.
 * Mail: dlut_frank@163.com
 * Copyright (c) 2019 .
 */
//@Dao
public interface NewsDetailDao {
    @Insert(onConflict = REPLACE)
    void saveNews(NewsDetailEntity newsDetail);

//    @Query("select * from news_detail where id = :id")
//    LiveData<NewsDetailEntity> loadNewsDetail(String id);
//
//    @Query("select * from news_detail where id in (:ids)")
//    LiveData<List<NewsDetailEntity>> loadNewsByIds(String[] ids);
}

package com.swx.jetpackxbasic;

import com.swx.jetpackxbasic.model.News;
import com.swx.jetpackxbasic.model.NewsDetail;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * Created by swx on 2019/1/30.
 * Mail: bjshenwenxing@netease.corp.com
 * Copyright (c) 2019 NetEase Spot Investment Platform.
 */
public interface DataCache {
    LiveData<List<News>> getNews();
    LiveData<NewsDetail> getNewsDetail(String id);
    void setNewsList(LiveData<List<News>> newsList);
    void setNewsDetail(String id, LiveData<NewsDetail> detail);
 }

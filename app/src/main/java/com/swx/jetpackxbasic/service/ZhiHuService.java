package com.swx.jetpackxbasic.service;

import com.swx.jetpackxbasic.db.entity.NewsDetailEntity;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by swx on 2019/1/29.
 * Mail: bjshenwenxing@netease.corp.com
 * Copyright (c) 2019 NetEase Spot Investment Platform.
 */
public interface ZhiHuService {

    @GET("api/4/news/latest")
    Call<LatestNews> newsList();

    @GET("api/4/news/latest")
    Single<LatestNews> newsListRx();

    @GET("api/4/news/{id}")
    Call<NewsDetailEntity> news(@Path("id") String newsId);
}

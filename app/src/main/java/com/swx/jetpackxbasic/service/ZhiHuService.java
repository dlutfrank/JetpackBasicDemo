package com.swx.jetpackxbasic.service;

import com.swx.jetpackxbasic.model.News;
import com.swx.jetpackxbasic.model.NewsDetail;

import java.util.List;

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
    Call<List<News>> newsList();

    @GET("api/4/news/{id}")
    Call<NewsDetail> news(@Path("id") String newsId);
}

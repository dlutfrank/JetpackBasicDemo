package com.swx.jetpackxbasic;

import com.swx.jetpackxbasic.db.entity.NewsDetailEntity;
import com.swx.jetpackxbasic.model.News;
import com.swx.jetpackxbasic.model.NewsDetail;
import com.swx.jetpackxbasic.service.LatestNews;
import com.swx.jetpackxbasic.service.ZhiHuService;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by swx on 2019/1/29.
 * Mail: bjshenwenxing@netease.corp.com
 * Copyright (c) 2019 NetEase Spot Investment Platform.
 */

public class DataRepository {
    private static final String TAG = DataRepository.class.getSimpleName();
    private ZhiHuService zhiHuService;
//    private DataCache dataCache;

    final private AppDataBase dataBase;

    private static DataRepository sInstance;

    private MutableLiveData<List<News>> newsData = new MutableLiveData<>();

    private DataRepository(AppDataBase dataBase) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://news-at.zhihu.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        zhiHuService = retrofit.create(ZhiHuService.class);
        this.dataBase = dataBase;
    }

    public static DataRepository getInstance(AppDataBase dataBase) {
        if(sInstance == null) {
            synchronized (DataRepository.class) {
                if(sInstance == null) {
                    sInstance = new DataRepository(dataBase);
                }
            }
        }
        return sInstance;
    }


    private boolean checkNewsList() {
        return  false;
    }

    public LiveData<List<News>> getNewsList() {
//        LiveData<List<News>> cachedNewsList = dataCache.getNews();
//        boolean isLatest = checkNewsList();
//        if(cachedNewsList != null && isLatest){
//            return cachedNewsList;
//        }
//        if(cachedNewsList == null){
//            dataCache.setNewsList(newsData);
//        }
        zhiHuService.newsList().enqueue(new Callback<LatestNews>() {
            @Override
            public void onResponse(Call<LatestNews> call, Response<LatestNews> response) {
                LatestNews latestNews = response.body();
                if(latestNews != null){
                    List<News> list = new ArrayList<>(latestNews.getTopStories());
                    list.removeAll(latestNews.getStories());
                    list.addAll(latestNews.getStories());
                    newsData.setValue(list);
                    Timber.d("data: %s", list.toString());
                }
                Timber.d("onResponse: %s", response.message());
            }

            @Override
            public void onFailure(Call<LatestNews> call, Throwable t) {
                Timber.d("onFailure: %s", t.getMessage());
            }
        });
        return newsData;
    }

    public void refreshDetail(String id) {

    }


    public LiveData<NewsDetail> getNewsDetail(String id) {
        refreshDetail(id);
//        LiveData<NewsDetail> cachedDetail = dataCache.getNewsDetail(id);
//        if(cachedDetail != null){
//            return cachedDetail;
//        }
        final MutableLiveData<NewsDetail> newsDetail = new MutableLiveData<>();
//        dataCache.setNewsDetail(id, newsDetail);
         zhiHuService.news(id).enqueue(new Callback<NewsDetailEntity>() {
            @Override
            public void onResponse(Call<NewsDetailEntity> call, Response<NewsDetailEntity> response) {
                newsDetail.setValue(response.body());
                Timber.d("onResponse: %s", response.body().toString());
            }

            @Override
            public void onFailure(Call<NewsDetailEntity> call, Throwable t) {
                Timber.d("onFailure: %s", t.getMessage());
            }
        });
        return newsDetail;
    }
}

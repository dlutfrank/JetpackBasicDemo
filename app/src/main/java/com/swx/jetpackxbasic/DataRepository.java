package com.swx.jetpackxbasic;

import android.util.Log;

import com.swx.jetpackxbasic.db.dao.NewsDetailDao;
import com.swx.jetpackxbasic.model.News;
import com.swx.jetpackxbasic.model.NewsDetail;
import com.swx.jetpackxbasic.service.ZhiHuService;

import java.util.List;
import java.util.concurrent.Executor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by swx on 2019/1/29.
 * Mail: bjshenwenxing@netease.corp.com
 * Copyright (c) 2019 NetEase Spot Investment Platform.
 */

public class DataRepository {
    public static final String TAG = DataRepository.class.getSimpleName();
    private ZhiHuService zhiHuService;
    private DataCache dataCache;
    private NewsDetailDao newsDetailDao;
    private LiveData<List<News>> newsData = new MutableLiveData<>();
    private Executor executor;

    public DataRepository(ZhiHuService service, NewsDetailDao detailDao, Executor executor) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://news-at.zhihu.com")
//                .build();
//        zhiHuService = retrofit.create(ZhiHuService.class);

        zhiHuService = service;
        newsDetailDao = detailDao;
        this.executor = executor;
    }

    private boolean checkNewsList() {
        return  false;
    }

    public LiveData<List<News>> getNewsList() {
        LiveData<List<News>> cachedNewsList = dataCache.getNews();
        boolean isLatest = checkNewsList();
        if(cachedNewsList != null && isLatest){
            return cachedNewsList;
        }
        if(cachedNewsList == null){
            dataCache.setNewsList(newsData);
        }
        zhiHuService.newsList().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                ((MutableLiveData<List<News>>)newsData).setValue(response.body());
                Timber.d(TAG, "onResponse: ");
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
        return newsData;
    }

    public void refreshDetail(String id) {

    }

    public LiveData<NewsDetail> getNewsDetail(String id) {
        refreshDetail(id);
        LiveData<NewsDetail> cachedDetail = dataCache.getNewsDetail(id);
        if(cachedDetail != null){
            return cachedDetail;
        }
        final MutableLiveData<NewsDetail> newsDetail = new MutableLiveData<>();
        dataCache.setNewsDetail(id, newsDetail);
        zhiHuService.news(id).enqueue(new Callback<NewsDetail>() {
            @Override
            public void onResponse(Call<NewsDetail> call, Response<NewsDetail> response) {
                newsDetail.setValue(response.body());
                Timber.d(TAG, "onResponse: ");
            }

            @Override
            public void onFailure(Call<NewsDetail> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
        return newsDetail;
    }
}

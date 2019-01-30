package com.swx.jetpackxbasic;

import android.util.Log;

import com.swx.jetpackxbasic.model.News;
import com.swx.jetpackxbasic.service.ZhiHuService;

import java.util.List;

import javax.inject.Singleton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by swx on 2019/1/29.
 * Mail: bjshenwenxing@netease.corp.com
 * Copyright (c) 2019 NetEase Spot Investment Platform.
 */

@Singleton
public class DataRepository {
    public static final String TAG = DataRepository.class.getSimpleName();
    private ZhiHuService zhiHuService;
    private MutableLiveData<List<News>> newsData = new MutableLiveData<>();


    public DataRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://news-at.zhihu.com")
                .build();
        zhiHuService = retrofit.create(ZhiHuService.class);
    }

    public LiveData<List<News>> getNewsList() {
        zhiHuService.newsList().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                newsData.setValue(response.body());
                Log.d(TAG, "onResponse: ");
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
        return newsData;
    }
}

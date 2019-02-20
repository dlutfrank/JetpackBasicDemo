package com.swx.jetpackxbasic.viewmodel;

import android.app.Application;

import com.swx.jetpackxbasic.App;
import com.swx.jetpackxbasic.DataRepository;
import com.swx.jetpackxbasic.model.NewsDetail;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by swx on 2019/1/31.
 * Mail: dlut_frank@163.com
 * Copyright (c) 2019 .
 */


public class NewsDetailModel extends AndroidViewModel {
    private LiveData<NewsDetail> newsDetail;
    private final DataRepository dataRepo;
    private final String newsId;

    public NewsDetailModel(Application application, String newsId) {
        super(application);
        App app = (App)application;
        this.dataRepo = app.getDataRepository();
        this.newsId = newsId;
        newsDetail = dataRepo.getNewsDetail(newsId);
    }

    LiveData<NewsDetail> getNewsDetail() {
        return newsDetail;
    }

    public static class Factory extends ViewModelProvider.AndroidViewModelFactory {

        private final String newsId;
        private final Application application;
        public Factory(@NonNull Application application, String newsId) {
            super(application);
            this.newsId = newsId;
            this.application = application;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new NewsDetailModel(application, newsId);
        }
    }
}

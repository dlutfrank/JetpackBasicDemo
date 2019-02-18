package com.swx.jetpackxbasic.viewmodel;

import com.swx.jetpackxbasic.DataRepository;
import com.swx.jetpackxbasic.model.NewsDetail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by swx on 2019/1/31.
 * Mail: dlut_frank@163.com
 * Copyright (c) 2019 .
 */


public class NewsDetailModel extends ViewModel {
    private LiveData<NewsDetail> newsDetail;
    private DataRepository dataRepo;

    public NewsDetailModel(DataRepository repo) {
        super();
        this.dataRepo = repo;
    }

    public void init(String id) {
        if(newsDetail != null){
            return;
        }
        newsDetail = dataRepo.getNewsDetail(id);
    }

    LiveData<NewsDetail> getNewsDetail() {
        return newsDetail;
    }
}

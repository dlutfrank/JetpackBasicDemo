package com.swx.jetpackxbasic.viewmodel;

import com.swx.jetpackxbasic.DataRepository;
import com.swx.jetpackxbasic.model.News;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class NewsListViewModel extends ViewModel {
    private LiveData<List<News>> news;
    private DataRepository dataRepo;

    public  NewsListViewModel(DataRepository dataRepo){
        super();
        this.dataRepo = dataRepo;
        news = dataRepo.getNewsList();
    }

    /**
     * LiveData用来通知其他组件数据变化，并且带有生命周期，不用担心内存泄露
     * @return LiveData<List<News>
     */
    public LiveData<List<News>> getNews(){
        return news;
    }
}

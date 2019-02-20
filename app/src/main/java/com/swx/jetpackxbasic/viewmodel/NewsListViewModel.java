package com.swx.jetpackxbasic.viewmodel;

import android.app.Application;

import com.swx.jetpackxbasic.App;
import com.swx.jetpackxbasic.DataRepository;
import com.swx.jetpackxbasic.model.News;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class NewsListViewModel extends AndroidViewModel {
    private LiveData<List<News>> news;
    private final DataRepository dataRepo;

    public  NewsListViewModel(Application application){
        super(application);
        App app = (App)application;
        this.dataRepo = app.getDataRepository();
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

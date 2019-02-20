package com.swx.jetpackxbasic.service;

import com.google.gson.annotations.SerializedName;
import com.swx.jetpackxbasic.db.entity.NewsEntity;
import com.swx.jetpackxbasic.model.News;

import java.util.List;

/**
 * Created by swx on 2019/2/19.
 * Mail: dlut_frank@163.com
 * Copyright (c) 2019 .
 */
public class LatestNews {
    @SerializedName("date")
    private String date;
    @SerializedName("stories")
    private List<NewsEntity> stories;
    @SerializedName("top_stories")
    private List<NewsEntity> topStories;

    public void setDate(String date) {
        this.date = date;
    }

    public void setStories(List<NewsEntity> stories) {
        this.stories = stories;
    }

    public void setTopStories(List<NewsEntity> stories) {
        this.topStories = stories;
    }

    public List<NewsEntity> getStories() {
        return  this.stories;
    }

    public String getDate() {
        return  this.date;
    }

    public List<NewsEntity> getTopStories() {
        return this.topStories;
    }
}

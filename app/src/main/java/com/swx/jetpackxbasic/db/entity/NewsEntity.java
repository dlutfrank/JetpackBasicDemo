package com.swx.jetpackxbasic.db.entity;

import com.swx.jetpackxbasic.model.News;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by swx on 2019/1/29.
 * Mail: bjshenwenxing@netease.corp.com
 * Copyright (c) 2019 NetEase Spot Investment Platform.
 */

public class NewsEntity implements News {
    @NonNull
    private String id;
    private String title;
    private List<String> images;
    private String image;

    public NewsEntity(@NonNull String id, String title, List<String>images) {
        this.id = id;
        this.title = title;
        this.images = images;
    }


    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj == null ) {
            return  false;
        }
        if(obj instanceof News) {
            return id.equals(((News) obj).getId());
        }
        return  false;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("\n{ id: %s,\ntitle: %s,\nimage: %s,\nimage[0]: %s}\n",
                id, title, image != null? image : "", images != null ? images.get(0): "");
    }

    @Override
    @NonNull
    public String getId() {
        return this.id;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public List<String> getImages() {
        return this.images;
    }

    @Override
    public String getImage() {
        return this.image;
    }
}

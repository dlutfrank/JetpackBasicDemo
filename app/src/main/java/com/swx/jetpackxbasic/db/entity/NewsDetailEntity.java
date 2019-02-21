package com.swx.jetpackxbasic.db.entity;

import com.swx.jetpackxbasic.model.NewsDetail;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

/**
 * Created by swx on 2019/1/29.
 * Mail: bjshenwenxing@netease.corp.com
 * Copyright (c) 2019 NetEase Spot Investment Platform.
 */

//@Entity(tableName = "news_detail", primaryKeys = {"id"})
public class NewsDetailEntity extends NewsEntity implements NewsDetail {
    private String body;
    @Ignore
    private List<String> css;
//    @ColumnInfo(name = "share_url")
    private String shareUrl;

    public NewsDetailEntity(String id, String title, List<String> images) {
        super(id, title, images);
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }



    @Override
    public String getBody() {
        return this.body;
    }

    @Override
    public List<String> getCss() {
        return this.css;
    }

    @Override
    public String getShareUrl() {
        return this.shareUrl;
    }
}

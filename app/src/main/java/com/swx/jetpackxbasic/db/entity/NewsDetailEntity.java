package com.swx.jetpackxbasic.db.entity;

import com.swx.jetpackxbasic.model.NewsDetail;

import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

/**
 * Created by swx on 2019/1/29.
 * Mail: bjshenwenxing@netease.corp.com
 * Copyright (c) 2019 NetEase Spot Investment Platform.
 */

@Entity(tableName = "news_detail", primaryKeys = {"id"})
public class NewsDetailEntity extends NewsEntity implements NewsDetail {
    @Ignore
    private String body;
    @Ignore
    private String css;
    @ColumnInfo(name = "share_url")
    private String shareUrl;
    private String image;

    public NewsDetailEntity(String id, String title, List<String> images) {
        super(id, title, images);
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setCss(String css) {
        this.css = css;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String getBody() {
        return this.body;
    }

    @Override
    public String getCss() {
        return this.css;
    }

    @Override
    public String getShareUrl() {
        return this.shareUrl;
    }

    @Override
    public String getImage() {
        return this.image;
    }
}

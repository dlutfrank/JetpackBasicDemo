package com.swx.jetpackxbasic.data;

import com.swx.jetpackxbasic.model.NewsDetail;

import java.util.List;

/**
 * Created by swx on 2019/1/29.
 * Mail: bjshenwenxing@netease.corp.com
 * Copyright (c) 2019 NetEase Spot Investment Platform.
 */
public class NewsDetailEntity extends NewsEntity implements NewsDetail {
    private String body;
    private String css;
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

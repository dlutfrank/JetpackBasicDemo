package com.swx.jetpackxbasic.model;

/**
 * Created by swx on 2019/1/29.
 * Mail: bjshenwenxing@netease.corp.com
 * Copyright (c) 2019 NetEase Spot Investment Platform.
 */
public interface NewsDetail extends News{
    String getBody();
    String getCss();
    String getShareUrl();
    String getImage();
}
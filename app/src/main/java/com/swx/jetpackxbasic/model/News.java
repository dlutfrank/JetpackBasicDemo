package com.swx.jetpackxbasic.model;

import java.util.List;

/**
 * Created by swx on 2019/1/29.
 * Mail: bjshenwenxing@netease.corp.com
 * Copyright (c) 2019 NetEase Spot Investment Platform.
 */
public interface News {
    String getId();
    String getTitle();
    List<String> getImages();
}

package com.swx.jetpackxbasic.ui;

import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import androidx.databinding.BindingAdapter;

/**
 * Created by swx on 2019/2/18.
 * Mail: dlut_frank@163.com
 * Copyright (c) 2019 .
 */
public class BindingAdapters {
    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE: View.GONE);
    }
    @BindingAdapter("fromUrl")
    public static void bindImageFromUrl(ImageView imageView, String imageUrl) {
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(imageView.getContext())
                    .load(imageUrl)
                    .apply(RequestOptions.circleCropTransform())
                    .into(imageView);
        }
    }

    @BindingAdapter("htmText")
    public static void setHtml(TextView textView, String html) {
        if(html != null) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                textView.setText(Html.fromHtml(html,Html.FROM_HTML_MODE_LEGACY));
            } else {
                textView.setText(Html.fromHtml(html));
            }
        }
    }
}

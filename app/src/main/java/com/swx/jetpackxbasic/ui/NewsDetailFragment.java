package com.swx.jetpackxbasic.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.swx.jetpackxbasic.R;
import com.swx.jetpackxbasic.databinding.NewsDetailFragmentBinding;
import com.swx.jetpackxbasic.databinding.NewsWebviewFragmentBinding;
import com.swx.jetpackxbasic.model.NewsDetail;
import com.swx.jetpackxbasic.utils.HtmlUtil;
import com.swx.jetpackxbasic.viewmodel.NewsDetailModel;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

/**
 * Created by swx on 2019/2/18.
 * Mail: dlut_frank@163.com
 * Copyright (c) 2019 .
 */
public class NewsDetailFragment extends Fragment {
    public static final String NEWS_ID_KEY = "_news_id_key_";

    private NewsDetailModel mModel;
//    private NewsDetailFragmentBinding mBinding;
    private NewsWebviewFragmentBinding mBinding;
    private WebView mHtmlTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.news_webview_fragment, container, false);
        mHtmlTextView = mBinding.htmlContent;
        initWebView(mHtmlTextView);
        return mBinding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        NewsDetailModel.Factory factory = new NewsDetailModel.Factory(getActivity().getApplication(),
                getArguments().getString(NEWS_ID_KEY));
        mModel = ViewModelProviders.of(this, factory).get(NewsDetailModel.class);
        subscribeData(mModel.getNewsDetail());
    }

    private void initWebView(WebView webView) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setSupportZoom(true);
    }

    private void subscribeData(LiveData<NewsDetail> liveData) {
        liveData.observe(this, newsDetail -> {
            mBinding.setNewsDetail(newsDetail);
            String htmlData = HtmlUtil.createHtmlData(newsDetail.getBody(),newsDetail.getCss(),newsDetail.getJs());
//            mHtmlTextView.setHtml(htmlData,new HtmlHttpImageGetter(mHtmlTextView));
            mHtmlTextView.loadData(htmlData, HtmlUtil.MIME_TYPE,HtmlUtil.ENCODING);
//            mHtmlTextView.setWebViewClient(new WebViewClient(){
//                @Override
//                public void onPageFinished(WebView view, String url) {
//                    super.onPageFinished(view, url);
//                    int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
//                    int h = View.MeasureSpec.makeMeasureSpec(0,
//                            View.MeasureSpec.UNSPECIFIED);
//                    mHtmlTextView.measure(w, h);
//                }
//            });
        });
    }

    public static NewsDetailFragment newsFragment(String newsId) {
        NewsDetailFragment fragment = new NewsDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(NEWS_ID_KEY, newsId);
        fragment.setArguments(bundle);
        return fragment;
    }

}

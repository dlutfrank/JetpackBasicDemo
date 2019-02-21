package com.swx.jetpackxbasic.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.swx.jetpackxbasic.R;
import com.swx.jetpackxbasic.databinding.NewsDetailFragmentBinding;
import com.swx.jetpackxbasic.model.NewsDetail;
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
    private NewsDetailFragmentBinding mBinding;
    private HtmlTextView mHtmlTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.news_detail_fragment, container, false);
        mHtmlTextView= mBinding.htmlContent;
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

    private void subscribeData(LiveData<NewsDetail> liveData) {
        liveData.observe(this, newsDetail -> {
            mBinding.setNewsDetail(newsDetail);
            mHtmlTextView.setHtml(newsDetail.getBody(),new HtmlHttpImageGetter(mHtmlTextView));
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

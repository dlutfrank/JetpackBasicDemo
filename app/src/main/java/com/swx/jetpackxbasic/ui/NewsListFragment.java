package com.swx.jetpackxbasic.ui;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import timber.log.Timber;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.swx.jetpackxbasic.R;

import com.swx.jetpackxbasic.databinding.NewsListFragmentBinding;
import com.swx.jetpackxbasic.model.News;
import com.swx.jetpackxbasic.viewmodel.NewsListViewModel;

import java.util.List;

public class NewsListFragment extends Fragment {

    private NewsListViewModel mViewModel;
    private NewsListFragmentBinding mBinding;
    private NewsAdapter mNewsAdapter;

    public static final String TAG = NewsListFragment.class.getSimpleName();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.news_list_fragment, container, false);
        mNewsAdapter = new NewsAdapter(mItemCallback);
        mBinding.rvNews.setAdapter(mNewsAdapter);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NewsListViewModel.class);
        subcribeData(mViewModel.getNews());
    }

    private void subcribeData(LiveData<List<News>> liveData) {
        mBinding.setIsLoading(true);
        liveData.observe(this, newsEntities -> {
            if(newsEntities != null){
                mBinding.setIsLoading(false);
                mNewsAdapter.setNewsList(newsEntities);
            }
        } );
    }

    @Override
    public void onResume() {
        super.onResume();
        subcribeData(mViewModel.getNews());
    }

    private final NewsItemCallback mItemCallback = newsItem -> {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            ((MainActivity) getActivity()).show(newsItem);
        } else {
            Timber.d("not in lifecycle");
        }
    };

}

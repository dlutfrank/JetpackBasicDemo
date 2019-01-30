package com.swx.jetpackxbasic.ui;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.swx.jetpackxbasic.R;
import com.swx.jetpackxbasic.viewmodel.NewsListViewModel;

public class NewsListFragment extends Fragment {

    private NewsListViewModel mViewModel;

    public static final String TAG = NewsListFragment.class.getSimpleName();

    public static NewsListFragment newInstance() {
        return new NewsListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.news_list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NewsListViewModel.class);
        mViewModel.getNews().observe(this,news -> {
            // update ui
        });
    }

}

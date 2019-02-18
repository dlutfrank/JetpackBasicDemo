package com.swx.jetpackxbasic.ui;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import timber.log.Timber;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.swx.jetpackxbasic.R;
import com.swx.jetpackxbasic.databinding.NewsListFragmentBinding;
import com.swx.jetpackxbasic.model.News;
import com.swx.jetpackxbasic.viewmodel.NewsListViewModel;

public class NewsListFragment extends Fragment {

    private NewsListViewModel mViewModel;
    private NewsListFragmentBinding mBinding;

    public static final String TAG = NewsListFragment.class.getSimpleName();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.news_list_fragment, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NewsListViewModel.class);
        mViewModel.getNews().observe(this,news -> {
            // update ui
        });
    }

    private final NewsItemCallback mItemCallback = new NewsItemCallback() {
        @Override
        public void onClick(News newsItem) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
//                ((MainActivity) getActivity()).show(product);
                Timber.d("news click: %s", newsItem.toString());
            } else {
                Timber.d("not in lifecycle");
            }
        }
    };

}

package com.swx.jetpackxbasic.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.swx.jetpackxbasic.R;
import com.swx.jetpackxbasic.databinding.NewsItemBinding;
import com.swx.jetpackxbasic.model.News;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by swx on 2019/2/18.
 * Mail: dlut_frank@163.com
 * Copyright (c) 2019 .
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private final NewsItemCallback mCallback;
    private List<? extends News> mNewsList;
    public NewsAdapter(@NonNull NewsItemCallback callback) {
        mCallback = callback;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NewsItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.news_item, parent, false);
        binding.setCallback(mCallback);
        return new NewsAdapter.ViewHolder(binding);
    }

    public void setNewsList(final List< ? extends News> newsList) {
        if(mNewsList == null) {
            mNewsList = newsList;
            notifyItemRangeInserted(0, mNewsList.size());
        } else {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    if(mNewsList == null){
                        return 0;
                    }
                    return mNewsList.size();
                }

                @Override
                public int getNewListSize() {
                    return newsList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    if(mNewsList == null || newsList == null) {
                        return  false;
                    }
                    News oldItem = mNewsList.get(oldItemPosition);
                    News newItem = newsList.get(newItemPosition);
                    if(oldItem == null || newItem == null) {
                        return  false;
                    }
                    return oldItem.getId().equals(newItem.getId());
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    if(newsList == null || mNewsList == null) {
                        return  false;
                    }
                    News oldItem = mNewsList.get(oldItemPosition);
                    News newItem = newsList.get(newItemPosition);
                    if(oldItem == null || newItem == null) {
                        return  false;
                    }
                    return oldItem.equals(newItem);
                }
            });
            mNewsList = newsList;
            diffResult.dispatchUpdatesTo(this);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        holder.binding.setNews(mNewsList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mNewsList == null ? 0 : mNewsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        final NewsItemBinding binding;

        public ViewHolder(NewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

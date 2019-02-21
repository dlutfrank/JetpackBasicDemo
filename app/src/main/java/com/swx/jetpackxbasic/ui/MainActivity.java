package com.swx.jetpackxbasic.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import timber.log.Timber;

import android.os.Bundle;

import com.swx.jetpackxbasic.R;
import com.swx.jetpackxbasic.model.News;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null){
            NewsListFragment newsListFragment = new NewsListFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container,newsListFragment, NewsListFragment.TAG)
                    .commit();
        }

    }

    public void show(News news) {
        Timber.d("news show %s", news.toString());
        Fragment fragment = NewsDetailFragment.newsFragment(news.getId());
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("news")
                .replace(R.id.fragment_container, fragment, null)
                .commit();
    }
}

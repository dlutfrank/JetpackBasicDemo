package com.swx.jetpackxbasic.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.swx.jetpackxbasic.R;

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
}

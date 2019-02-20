package com.swx.jetpackxbasic;


import androidx.multidex.MultiDexApplication;
import timber.log.Timber;

/**
 * Created by swx on 2019/2/13.
 * Mail: dlut_frank@163.com
 * Copyright (c) 2019 .
 */
public class App extends MultiDexApplication {

    private static App _instance = null;

    public static App getInstance() {
        return _instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        _instance = this;
    }

    public AppDataBase getDatabase() {
        return  AppDataBase.getInstance(this);
    }

    public DataRepository getDataRepository() {
        return DataRepository.getInstance(getDatabase());
    }

}

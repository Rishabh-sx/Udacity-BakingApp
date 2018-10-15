package com.rishabh.bakingapp;

import android.app.Application;
import android.content.Context;

import com.rishabh.bakingapp.data.DataManager;

public class BakingApp extends Application {

    private static BakingApp instance;

    public static Context getContext() {
        return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        DataManager.init(this);
    }
}

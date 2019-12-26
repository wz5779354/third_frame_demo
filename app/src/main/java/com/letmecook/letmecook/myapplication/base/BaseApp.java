package com.letmecook.letmecook.myapplication.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by ao on 2017/6/13.
 */

public class BaseApp extends Application {
    private static BaseApp mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }

    public static Context getAppContext() {
        return mApp;
    }
}

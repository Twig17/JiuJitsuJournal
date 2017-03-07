package com.ferraro.myjiujitsujournal.mjjj;

import android.app.Application;
import android.content.Context;

/**
 * Created by Nick on 3/6/2017.
 */
public class MyApp extends Application {
    private static MyApp instance;
    private static Context mContext;

    public static MyApp getInstance() {
        return instance;
    }

    public static Context getContext() {
        //  return instance.getApplicationContext();
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //  instance = this;
        mContext = getApplicationContext();
    }
}
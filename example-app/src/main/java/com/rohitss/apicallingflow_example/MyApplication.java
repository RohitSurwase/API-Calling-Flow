package com.rohitss.apicallingflow_example;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

/**
 * Created by JayYogeshwar on 21-08-2017.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }
}

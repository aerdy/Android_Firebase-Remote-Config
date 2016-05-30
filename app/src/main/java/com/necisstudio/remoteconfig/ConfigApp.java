package com.necisstudio.remoteconfig;

import android.app.Application;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/**
 * Created by Jarcode on 2016-05-30.
 */

public class ConfigApp extends Application {
    public static FirebaseRemoteConfig mFirebaseRemoteConfig;

    @Override
    public void onCreate() {
        super.onCreate();
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
    }
}

package com.lpdw.urbanproject;

import android.app.Application;
import android.content.Context;

/**
 * Created by yassin on 15/06/16.
 */
public class UrbanPotagerApplication extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        UrbanPotagerApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return UrbanPotagerApplication.context;
    }
}

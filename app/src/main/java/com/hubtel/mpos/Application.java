package com.hubtel.mpos;

import android.content.Context;

/**
 * Created by apple on 20/06/16.
 */
public class Application extends android.app.Application {

    private static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getAppContext() {
        return context;
    }
}

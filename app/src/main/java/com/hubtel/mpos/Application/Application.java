package com.hubtel.mpos.Application;

import android.content.Context;

import com.hubtel.mpos.Models.CartItems;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 20/06/16.
 */
public class Application extends android.app.Application {

    public static Context context;
    public static List<CartItems> cartItemsList=new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getAppContext() {
        return context;
    }
}

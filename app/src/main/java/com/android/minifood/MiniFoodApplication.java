package com.android.minifood;

import android.app.Application;
import android.content.Context;

public class MiniFoodApplication extends Application {
    public Context g_context;

    public User g_user;
    public Dishes g_dishes;
    public Dishes g_shopping;

    public int g_communiMode;
    public String g_ip;
    public String g_http;
    public int g_port;

    @Override
    public void onCreate() {
        super.onCreate();
        g_context = this;
        g_user = new User();
        g_dishes = new Dishes();
        g_dishes.initDishes();
        g_shopping = new Dishes();
        //g_shopping.initShopping();
        g_communiMode = 1;
        g_ip = "";
        g_http = "";
        g_port = 35885;
    }
}

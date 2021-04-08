package com.example.dashfoods;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import java.util.ArrayList;

public class ApplicationMain extends Application implements com.example.dashfoods.UpdateSelectedItem {

    @SuppressLint("StaticFieldLeak")
    private static Context context;
    ArrayList<OrderListModel> orderListModels;

    @Override
    public void onCreate(){
        super.onCreate();
        context=getApplicationContext();
        orderListModels=new ArrayList<>();
    }

    public static Context getMyContext(){
        return context;
    }

    @Override
    public ArrayList<OrderListModel> getItems() {
        return orderListModels;
    }

}

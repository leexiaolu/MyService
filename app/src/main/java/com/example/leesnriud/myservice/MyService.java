package com.example.leesnriud.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by lee.snriud on 2018/3/13.
 */

public class MyService extends Service {

    private static String TAG ="myservice";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG,"myservice onBind");
        return null;
    }

    //service被创建时调用
    @Override
    public void onCreate() {
        Log.e(TAG,"myservice onCreate");
        super.onCreate();
    }

    //service被启动时调用
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG,"myservice onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    //service被关闭前回调
    @Override
    public void onDestroy() {
        Log.e(TAG,"myservice onDestroy");
        super.onDestroy();
    }
}

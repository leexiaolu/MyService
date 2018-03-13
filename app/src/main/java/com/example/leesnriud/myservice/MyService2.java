package com.example.leesnriud.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by lee.snriud on 2018/3/13.
 */

public class MyService2 extends Service {

    private final String TAG = "myservice2";
    private int count;
    private boolean quit;

    //定义onBinder方法所返回的对象
    private MyBinder binder = new MyBinder();
    public class MyBinder extends Binder {
        public int getCount()
        {
            return count;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG,"myservice2 onBind");
        return binder;
    }

    //service被创建时调用
    @Override
    public void onCreate() {
        Log.e(TAG,"myservice2 onCreate");
        super.onCreate();
        //创建一个线程动态地修改count的值
        new Thread()
        {
            public void run()
            {
                while(!quit)
                {
                    try
                    {
                        Thread.sleep(1000);
                    }catch(InterruptedException e){e.printStackTrace();}
                    count++;
                }
            };
        }.start();
    }

    //service被启动时调用
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG,"myservice2 onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    //Service断开连接时回调
    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "myservice onUnbind");
        return true;
    }

    //service被关闭前回调
    @Override
    public void onDestroy() {
        Log.e(TAG,"myservice2 onDestroy");
        super.onDestroy();
        this.quit = true;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.e(TAG,"myservice2 onRebind");
    }
}

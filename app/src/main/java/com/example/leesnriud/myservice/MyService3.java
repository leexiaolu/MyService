package com.example.leesnriud.myservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by lee.snriud on 2018/3/13.
 */

public class MyService3 extends IntentService{

    private final String TAG = "myservice3";

    //必须实现父类的构造方法
    public MyService3() {
        super("MyService3");
    }

    //必须重写的核心方法
    @Override
    protected void onHandleIntent(Intent intent) {
        //Intent是从Activity发过来的，携带识别参数，根据参数不同执行不同的任务
        String action = intent.getExtras().getString("param");
        if(action.equals("s1")) Log.e(TAG,"启动service1");
        else if(action.equals("s2"))Log.e(TAG,"启动service2");
        else if(action.equals("s3"))Log.e(TAG,"启动service3");

        //让服务休眠2秒
        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){e.printStackTrace();}
    }

    //重写其他方法,用于查看方法的调用顺序
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG,"myservice3 onBind");
        return super.onBind(intent);
    }

    @Override
    public void onCreate() {
        Log.e(TAG,"myservice3 onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG,"myservice3 onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void setIntentRedelivery(boolean enabled) {
        super.setIntentRedelivery(enabled);
        Log.e(TAG,"myservice3 setIntentRedelivery");
    }

    @Override
    public void onDestroy() {
        Log.e(TAG,"myservice3 onDestroy");
        super.onDestroy();
    }
}

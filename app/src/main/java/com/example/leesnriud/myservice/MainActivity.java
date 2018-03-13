package com.example.leesnriud.myservice;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * android service
 */
public class MainActivity extends AppCompatActivity {


    Intent intent = new Intent();
    Intent intent2 = new Intent();
    Intent it1 ,it2,it3;


    //保持所启动的Service的IBinder对象,同时定义一个ServiceConnection对象
    MyService2.MyBinder binder;
    private ServiceConnection conn = new ServiceConnection() {

        //Activity与Service断开连接时回调该方法
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("myservice2","------Service DisConnected-------");
        }

        //Activity与Service连接成功时回调该方法
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("myservice2","------Service Connected-------");
            binder = (MyService2.MyBinder) service;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        intent = new Intent();
        intent.setAction("com.example.leesnriud.myservice.MY_SERVICE");
        //Android 5.0以后需要添加下面的代码，否则会出现异常
        //java.lang.IllegalArgumentException: Service Intent must be explicit
        intent.setPackage(getPackageName());

        intent2 = new Intent();
        intent2.setAction("com.example.leesnriud.myservice.MY_SERVICE2");
        intent2.setPackage(getPackageName());

        it1 = new Intent("com.example.leesnriud.myservice.MY_SERVICE3");
        it1.setPackage(getPackageName());
        Bundle b1 = new Bundle();
        b1.putString("param", "s1");
        it1.putExtras(b1);

        it2 = new Intent("com.example.leesnriud.myservice.MY_SERVICE3");
        it2.setPackage(getPackageName());
        Bundle b2 = new Bundle();
        b2.putString("param", "s2");
        it2.putExtras(b2);

        it3 = new Intent("com.example.leesnriud.myservice.MY_SERVICE3");
        it3.setPackage(getPackageName());
        Bundle b3 = new Bundle();
        b3.putString("param", "s3");
        it3.putExtras(b3);

    }

    @OnClick({R.id.bt_startservice, R.id.bt_stopservice, R.id.bt_servicebind,
            R.id.bt_servicecancel, R.id.bt_statusservice,R.id.bt_services,R.id.bt_ntfservice,R.id.bt_service_receiver})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_startservice:
                startService(intent);
                break;
            case R.id.bt_stopservice:
                stopService(intent);
                break;
            case R.id.bt_servicebind:
                bindService(intent2,conn, Service.BIND_AUTO_CREATE);
                break;
            case R.id.bt_servicecancel:
                if(ServiceUtils.isServiceRunning(MainActivity.this,"com.example.leesnriud.myservice.MY_SERVICE2")){
                    unbindService(conn);
                }else{
                    Toast.makeText(MainActivity.this,"该服务已经被解除",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_statusservice:
                Toast.makeText(MainActivity.this, "Service的count的值为:"
                        + binder.getCount(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_services:
                //接着启动多次IntentService,每次启动,都会新建一个工作线程
                //但始终只有一个IntentService实例
                startService(it1);
                startService(it2);
                startService(it3);
                break;
            case R.id.bt_ntfservice:
                Intent intent4 = new Intent();
                intent4.setAction("com.example.leesnriud.myservice.MY_SERVICE4");
                intent4.setPackage(getPackageName());
                startService(intent4);
                break;
            case R.id.bt_service_receiver:
                Intent intent5 = new Intent();
                intent5.setAction("com.example.leesnriud.myservice.MY_SERVICE5");
                intent5.setPackage(getPackageName());
                startService(intent5);
        }
    }
}

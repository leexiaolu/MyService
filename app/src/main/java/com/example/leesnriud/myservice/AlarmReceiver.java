package com.example.leesnriud.myservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by lee.snriud on 2018/3/13.
 */

public class AlarmReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context,MyService5.class);
        context.startService(i);
    }
}

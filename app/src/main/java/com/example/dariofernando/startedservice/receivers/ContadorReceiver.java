package com.example.dariofernando.startedservice.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by DarioFernando on 24/07/2015.
 */
public class ContadorReceiver extends BroadcastReceiver {

    public static final String ACTION="com.example.dariofernando.startedservice.time";
    public static final String KEY_TIME ="time";


    public interface ContadorReceiverI{
        void setTime(int time);
    }

    ContadorReceiverI contadorReceiverI;

    public ContadorReceiver(ContadorReceiverI contadorReceiverI) {
        this.contadorReceiverI = contadorReceiverI;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int time = intent.getExtras().getInt(KEY_TIME);
        contadorReceiverI.setTime(time);
    }
}

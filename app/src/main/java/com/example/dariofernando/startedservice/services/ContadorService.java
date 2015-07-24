package com.example.dariofernando.startedservice.services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/**
 * Created by DarioFernando on 23/07/2015.
 */
public class ContadorService extends Service {

    public static final String ACTION_START = "accionstart";
    public static final String ACTION_PAUSE = "accionpause";
    public static final String ACTION_STOP = "actionstop";

    ServiceHandler handler;



    @Override
    public void onCreate() {
        super.onCreate();

        HandlerThread thread = new HandlerThread("StatedService"
                , Thread.MAX_PRIORITY);
        thread.start();

        handler = new ServiceHandler(thread.getLooper());

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        Message msg = handler.obtainMessage();
        msg.obj = intent.getAction();
        handler.sendMessage(msg);
        return START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class ServiceHandler extends Handler {

        public ServiceHandler(Looper looper){
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            String action = (String) msg.obj;
            if(action.equals(ACTION_START)){

            }else if(action.equals(ACTION_PAUSE)){

            }else{

            }
        }
    }

}

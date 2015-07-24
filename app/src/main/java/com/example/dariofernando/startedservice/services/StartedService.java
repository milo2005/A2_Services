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
public class StartedService extends Service {

    public static final String ACTION_1 = "accion1";
    public static final String ACTION_2 = "accion2";
    public static final String ACTION_3 = "action3";

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
            if(action.equals(ACTION_1)){
                Log.i("StartedService","Accion 1");
            }else if(action.equals(ACTION_2)){
                Log.i("StartedService","Accion 2");
            }else{

                for(int i=0;i<15;i++){
                    try {
                        Thread.sleep(1000);
                        Log.i("StartedService",""+i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

}

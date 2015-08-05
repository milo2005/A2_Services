package com.example.dariofernando.startedservice.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.dariofernando.startedservice.ContadorActivity;
import com.example.dariofernando.startedservice.receivers.ContadorReceiver;

/**
 * Created by DarioFernando on 23/07/2015.
 */
public class ContadorService extends Service {

    public static final String ACTION_START = "accionstart";
    public static final String ACTION_PAUSE = "accionpause";
    public static final String ACTION_STOP = "actionstop";

    ServiceHandler handler;

    boolean paused, running;
    int cont;

    Intent contadorAction;

    @Override
    public void onCreate() {
        super.onCreate();

        contadorAction = new Intent(ContadorReceiver.ACTION);

        HandlerThread thread = new HandlerThread("StatedService"
                , Thread.MAX_PRIORITY);
        thread.start();

        handler = new ServiceHandler(thread.getLooper());

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String action = intent.getAction();
        if(action.equals(ACTION_START)){
            paused = false;
            if(!running) {
                cont=0;
                running = true;
                setUpForeground();
                handler.sendEmptyMessage(0);
            }
        }else if(action.equals(ACTION_PAUSE)){
            paused = true;
        }else{
            running = false;
            stopSelf();
        }

        return START_REDELIVER_INTENT;


    }

    private void setUpForeground() {

        Intent intent = new Intent(this, ContadorActivity.class);
        PendingIntent pendingIntent = PendingIntent
                .getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notificacion = new NotificationCompat.Builder(this)
                .setContentTitle("Contador")
                .setContentText("El servicio esta ejecutandoce")
                .setOngoing(false)
                .setSmallIcon(android.R.drawable.ic_media_play)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(101, notificacion);
    }

    @Override
    public void onDestroy() {
        stopForeground(true);
        super.onDestroy();
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

            while(running){

                try {
                    Thread.sleep(1000);
                    if(!paused) {
                        cont++;
                        contadorAction.putExtra(ContadorReceiver.KEY_TIME,cont);
                        ContadorService.this.sendBroadcast(contadorAction);

                        Log.i("ContadorService", ""+cont);


                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }

}

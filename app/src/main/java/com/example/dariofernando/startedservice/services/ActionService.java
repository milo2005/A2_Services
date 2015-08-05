package com.example.dariofernando.startedservice.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;


public class ActionService extends IntentService {

    public static final String ACTION_1 ="action1";
    public static final String ACTION_2 ="action2";
    public static final String ACTION_3 ="action3";

    public ActionService() {
        super("ActionService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        if(intent.getAction().equals(ACTION_1)){

            Log.i("ActionService", "Se ejecuto la accion 1");

        }else if(intent.getAction().equals(ACTION_2)){
            Log.i("ActionService", "Se ejecuto la accion 2");
        }else{

            for(int i =0 ; i<5;i++){
                try {
                    Thread.sleep(1000);
                    Log.i("ActionService", ""+i);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }

        }
    }
}

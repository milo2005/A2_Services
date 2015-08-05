package com.example.dariofernando.startedservice;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dariofernando.startedservice.receivers.ContadorReceiver;
import com.example.dariofernando.startedservice.services.ContadorService;


public class ContadorActivity extends ActionBarActivity implements View.OnClickListener, ContadorReceiver.ContadorReceiverI {

    Button start, pause, stop;
    TextView txt;

    Intent contadorService;

    ContadorReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contador);

        txt = (TextView) findViewById(R.id.txt);

        start = (Button) findViewById(R.id.btn_start);
        pause = (Button) findViewById(R.id.btn_pause);
        stop = (Button) findViewById(R.id.btn_stop);

        start.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);

        contadorService =  new Intent(this, ContadorService.class);

        receiver =  new ContadorReceiver(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction(ContadorReceiver.ACTION);

        registerReceiver(receiver,filter);
    }


    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_start:
                contadorService.setAction(ContadorService.ACTION_START);
                break;
            case R.id.btn_pause:
                contadorService.setAction(ContadorService.ACTION_PAUSE);
                break;
            case R.id.btn_stop:
                contadorService.setAction(ContadorService.ACTION_STOP);
                break;
        }
        startService(contadorService);
    }

    @Override
    public void setTime(int time) {
        txt.setText(""+time);
    }
}

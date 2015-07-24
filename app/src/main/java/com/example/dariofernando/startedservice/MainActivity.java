package com.example.dariofernando.startedservice;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.dariofernando.startedservice.services.ActionService;
import com.example.dariofernando.startedservice.services.StartedService;

import static com.example.dariofernando.startedservice.R.id.btn_action1;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    Button action1, action2, action3;
    Intent actionService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        action1= (Button) findViewById(R.id.btn_action1);
        action2= (Button) findViewById(R.id.btn_action2);
        action3= (Button) findViewById(R.id.btn_action3);

        action1.setOnClickListener(this);
        action2.setOnClickListener(this);
        action3.setOnClickListener(this);

        //actionService =  new Intent(this, ActionService.class);
        actionService =  new Intent(this, StartedService.class);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_action1:
                //actionService.setAction(ActionService.ACTION_1);
                actionService.setAction(StartedService.ACTION_1);
                break;
            case R.id.btn_action2:
                //actionService.setAction(ActionService.ACTION_2);
                actionService.setAction(StartedService.ACTION_2);
                break;
            case R.id.btn_action3:
                //actionService.setAction(ActionService.ACTION_3);
                actionService.setAction(StartedService.ACTION_3);
                break;

        }
        startService(actionService);
    }
}

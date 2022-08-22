package com.example.rahul.gridview.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.example.rahul.gridview.Activity.BaseActivity;
import com.example.rahul.gridview.R;


import com.example.rahul.gridview.utility_service.BoundService;

public class LocalBoundActivity extends BaseActivity implements View.OnClickListener  {

    BoundService myService;
    boolean isBound = false;
    TextView myTextView;
    Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_bound);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        myTextView = (TextView)findViewById(R.id.myTextView);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);

        Intent intent = new Intent(this, BoundService.class);

        bindService(intent, myConnection, Context.BIND_AUTO_CREATE);


    }



    private ServiceConnection myConnection  = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {


            BoundService.MyLocalBinder  binder = (BoundService.MyLocalBinder)service ;
            myService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.btnSubmit)
        {

            showTime();
        }

    }

    public void showTime()
    {
        String currentTime = myService.getCurrentTime();

        myTextView.setText(currentTime);
    }



}

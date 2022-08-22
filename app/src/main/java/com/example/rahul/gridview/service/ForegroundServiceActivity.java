package com.example.rahul.gridview.service;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;

import com.example.rahul.gridview.Activity.BaseActivity;
import com.example.rahul.gridview.R;
import com.example.rahul.gridview.utility_service.MyForeGroundService;

public class ForegroundServiceActivity extends BaseActivity implements View.OnClickListener {

    Button btnStart, btnStop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreground_service);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        btnStart = findViewById(R.id.start_foreground_service_button);

        btnStop = findViewById(R.id.stop_foreground_service_button);

        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.start_foreground_service_button :
                Intent intent1 = new Intent(ForegroundServiceActivity.this, MyForeGroundService.class);
                intent1.setAction(MyForeGroundService.ACTION_START_FOREGROUND_SERVICE);


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startForegroundService(intent1);
                } else {
                    startService(intent1);
                }
                break;

            case R.id.stop_foreground_service_button :

                Intent intent2 = new Intent(ForegroundServiceActivity.this, MyForeGroundService.class);
                intent2.setAction(MyForeGroundService.ACTION_STOP_FOREGROUND_SERVICE);


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startForegroundService(intent2);
                } else {
                    startService(intent2);
                }
                break;
        }


    }
}

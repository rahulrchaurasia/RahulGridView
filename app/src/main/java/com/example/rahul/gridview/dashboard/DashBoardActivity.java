package com.example.rahul.gridview.dashboard;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;

import com.example.rahul.gridview.Activity.BaseActivity;
import com.example.rahul.gridview.AsyncTaskDemo.DemoOfAsyncActivity;
import com.example.rahul.gridview.R;
import com.example.rahul.gridview.kotlin.RoomDemo.RoomActivity;
import com.example.rahul.gridview.customDrawable.CustomShapeActivity;
import com.example.rahul.gridview.custom_switch.SwitchActivity;
import com.example.rahul.gridview.service.ForegroundServiceActivity;
import com.example.rahul.gridview.service.DownloadNotifyBarActivity;
import com.example.rahul.gridview.service.IntentServiceActivity;
import com.example.rahul.gridview.service_download.ServiceDownloadActivity;
import com.example.rahul.gridview.workManager.WorkManagerDashboard;

public class DashBoardActivity extends BaseActivity implements View.OnClickListener {

    LinearLayout lyServiceNotifyBar,lyServiceDownload,lyServiceForeground,lyCustomSwitch,lyCustShape,lyAsyncTask,
                    lyIntentService,lyQRScanner,lykotlin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        lyServiceNotifyBar = findViewById(R.id.lyServiceNotifyBar);
        lyServiceForeground = findViewById(R.id.lyServiceForeground);
        lyServiceDownload = findViewById(R.id.lyServiceDownload);

        lyCustomSwitch  = findViewById(R.id.lyCustomSwitch);
        lyCustShape   = findViewById(R.id.lyCustShape);
        lyAsyncTask  = findViewById(R.id.lyAsyncTask);

        lyIntentService = findViewById(R.id.lyIntentService);
        lyQRScanner = findViewById(R.id.lyQRScanner);
        lykotlin = findViewById(R.id.lykotlin);

        lyServiceNotifyBar.setOnClickListener(this);
        lyServiceForeground.setOnClickListener(this);
        lyCustomSwitch.setOnClickListener(this);
        lyCustShape.setOnClickListener(this);
        lyAsyncTask.setOnClickListener(this);
        lyServiceDownload.setOnClickListener(this);

        lyIntentService.setOnClickListener(this);
        lyQRScanner.setOnClickListener(this);
        lykotlin.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.lyServiceNotifyBar :
                startActivity(new Intent(DashBoardActivity.this, DownloadNotifyBarActivity.class));
                break;

            case R.id.lyServiceForeground :
               startActivity(new Intent(DashBoardActivity.this, ForegroundServiceActivity.class));

                break;

            case R.id.lyServiceDownload :
                startActivity(new Intent(DashBoardActivity.this, ServiceDownloadActivity.class));

                break;

            case R.id.lyCustomSwitch:

                startActivity(new Intent(DashBoardActivity.this, SwitchActivity.class));

                break;


            case R.id.lyCustShape :
                startActivity(new Intent(DashBoardActivity.this, CustomShapeActivity.class));

                break;

            case R.id.lyAsyncTask :
                startActivity(new Intent(DashBoardActivity.this, DemoOfAsyncActivity.class));

                break;

            case R.id.lyIntentService :
                startActivity(new Intent(DashBoardActivity.this, IntentServiceActivity.class));

                break;

            case R.id.lyQRScanner :
                startActivity(new Intent(DashBoardActivity.this, WorkManagerDashboard.class));

                break;

            case R.id.lykotlin :
                startActivity(new Intent(DashBoardActivity.this, RoomActivity.class));

                break;


        }

    }
}

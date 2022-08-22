package com.example.rahul.gridview.workManager;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.example.rahul.gridview.R;
import com.example.rahul.gridview.workManager.Worker.MyPeriodicWorker;

import java.util.concurrent.TimeUnit;

public class WorkManagerPeriodicActivity extends AppCompatActivity {

    private static final String TAG = "PERIODIC_WORK";
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_manager_periodic);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btnStart = findViewById(R.id.btnStart);
        final PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(
                MyPeriodicWorker.class, 15, TimeUnit.MINUTES)
                .build();


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WorkManager.getInstance().enqueue(periodicWorkRequest);
            }
        });
    }

}

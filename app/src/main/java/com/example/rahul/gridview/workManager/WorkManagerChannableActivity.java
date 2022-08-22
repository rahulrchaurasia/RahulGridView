package com.example.rahul.gridview.workManager;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.example.rahul.gridview.Activity.BaseActivity;
import com.example.rahul.gridview.R;
import com.example.rahul.gridview.workManager.Worker.MyWorkerA;
import com.example.rahul.gridview.workManager.Worker.MyWorkerB;
import com.example.rahul.gridview.workManager.Worker.MyWorkerC;

public class WorkManagerChannableActivity extends BaseActivity implements View.OnClickListener {

    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_manager_channable);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnStart) {

            final OneTimeWorkRequest oneTimeWorkRequestA = new OneTimeWorkRequest.Builder(MyWorkerA.class).build();
            final OneTimeWorkRequest oneTimeWorkRequestB = new OneTimeWorkRequest.Builder(MyWorkerB.class).build();
            final OneTimeWorkRequest oneTimeWorkRequestC = new OneTimeWorkRequest.Builder(MyWorkerC.class).build();


            WorkManager.getInstance()
                    .beginWith(oneTimeWorkRequestA)
                    .then(oneTimeWorkRequestB)
                    .then(oneTimeWorkRequestC)
                    .enqueue();



        }
    }
}

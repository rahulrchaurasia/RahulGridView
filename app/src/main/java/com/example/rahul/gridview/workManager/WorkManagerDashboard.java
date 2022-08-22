package com.example.rahul.gridview.workManager;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;

import com.example.rahul.gridview.Activity.BaseActivity;
import com.example.rahul.gridview.R;

public class WorkManagerDashboard extends BaseActivity implements View.OnClickListener {

    LinearLayout lyWork1,lyWork2,lyWork3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_manager_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        lyWork1 = findViewById(R.id.lyWork1);
        lyWork2 = findViewById(R.id.lyWork2);
        lyWork3 = findViewById(R.id.lyWork3);

        lyWork1.setOnClickListener(this);
        lyWork2.setOnClickListener(this);
        lyWork3.setOnClickListener(this);
    }

    //WorkManagerChannableActivity
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.lyWork1:
                startActivity(new Intent(WorkManagerDashboard.this, WorkManagerActivity.class));
                break;

            case R.id.lyWork2:
                startActivity(new Intent(WorkManagerDashboard.this, WorkManagerPeriodicActivity.class));

                break;
            case R.id.lyWork3:
                startActivity(new Intent(WorkManagerDashboard.this, WorkManagerChannableActivity.class));

                break;

        }
    }
}

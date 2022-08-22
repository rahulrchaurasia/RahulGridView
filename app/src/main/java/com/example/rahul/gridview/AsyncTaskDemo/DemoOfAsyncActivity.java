package com.example.rahul.gridview.AsyncTaskDemo;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;

import com.example.rahul.gridview.Activity.BaseActivity;
import com.example.rahul.gridview.R;

public class DemoOfAsyncActivity extends BaseActivity implements View.OnClickListener {

    Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_of_async);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btn1) {
            startActivity(new Intent(DemoOfAsyncActivity.this, AsyncTaskDemoActivity.class));
        } else if (view.getId() == R.id.btn2) {
            startActivity(new Intent(DemoOfAsyncActivity.this, AsyncTaskRotateActivity.class));
        }
    }
}

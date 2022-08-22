package com.example.rahul.gridview.AsyncTaskDemo;

import android.app.ProgressDialog;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.rahul.gridview.R;

public class AsyncTaskRotateActivity extends AppCompatActivity implements  View.OnClickListener {

    Button btn1;
    TextView txtMessage;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_rotate);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btn1 = findViewById(R.id.btn1);
        txtMessage  = findViewById(R.id.txtMessage);
        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn1) {
            new AsyncTask(new TaskListener() {
                @Override
                public void onTaskStarted() {

                    txtMessage.setText("Loading Stareted...............");
                    progressDialog = ProgressDialog.show(AsyncTaskRotateActivity.this, "Loading", "Please wait a moment!");
                }

                @Override
                public void onTaskFinished(String result) {
                    if (progressDialog != null) {
                        txtMessage.setText("Loading Finished");
                      //  progressDialog.dismiss();
                    }
                }
            }).execute();
        }
    }

//    @Override
//    public void onTaskStarted() {
//        progressDialog = ProgressDialog.show(AsyncTaskRotateActivity.this, "Loading", "Please wait a moment!");
//    }
//
//    @Override
//    public void onTaskFinished(String result) {
//
//        if (progressDialog != null) {
//            progressDialog.dismiss();
//        }
//    }





}

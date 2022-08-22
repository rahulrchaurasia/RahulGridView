package com.example.rahul.gridview.Activity;

import android.os.Bundle;

import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.rahul.gridview.R;

public class CustomProgressActivity extends AppCompatActivity {

    ProgressBar myProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ustom_progress);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myProgress = (ProgressBar)  findViewById(R.id.progressBar1);

        myProgress.setMax(100);
        myProgress.setProgress(25);



    }

}

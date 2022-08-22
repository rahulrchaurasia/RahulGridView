package com.example.rahul.gridview.Activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.widget.Toolbar;

import com.example.rahul.gridview.R;

public class RotateActivity extends BaseActivity implements View.OnClickListener {

    ImageButton imgBtn;
    Button   btnSubmit ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);

        btnSubmit  = (Button) findViewById(R.id.btnSubmit);
        imgBtn = (ImageButton) findViewById(R.id.imgBtn);

        imgBtn.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.imgBtn:

                ObjectAnimator anim = ObjectAnimator.ofFloat(view, "rotation",180, 0);
                anim.setDuration(500);
                anim.start();
                break;

            case R.id.btnSubmit:

                ObjectAnimator anim1 = ObjectAnimator.ofFloat(imgBtn, "rotation",180, 0);
                anim1.setDuration(500);
                anim1.start();
                break;
        }
    }
}

package com.example.rahul.gridview.Activity;

import android.gesture.Gesture;
import android.graphics.Matrix;
import android.os.Bundle;

import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.rahul.gridview.R;
import com.example.rahul.gridview.Utility.OnSwipeTouchListener;

public class GestureActivity extends AppCompatActivity {

    private ImageView iv;
    private TextView textView1;
    private Matrix matrix = new Matrix();
    private float scale = 1f;
    private ScaleGestureDetector SGD;

    LinearLayout llMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        iv=(ImageView)findViewById(R.id.imageView);
        textView1 = (TextView)findViewById(R.id.textView1);
        llMain = (LinearLayout)findViewById(R.id.llMain);
        SGD = new ScaleGestureDetector(this,new ScaleListener());

        textView1.setOnTouchListener( new OnSwipeTouchListener(GestureActivity.this) {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return super.onTouch(v, event);
            }


        });

    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        SGD.onTouchEvent(event);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.
            SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale *= detector.getScaleFactor();
            scale = Math.max(0.1f, Math.min(scale, 5.0f));
            matrix.setScale(scale, scale);
            iv.setImageMatrix(matrix);
            return true;
        }
    }


    /////////////////////////////////////////////////
}

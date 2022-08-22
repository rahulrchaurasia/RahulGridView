package com.example.rahul.gridview.demo;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.rahul.gridview.Activity.BaseActivity;
import com.example.rahul.gridview.R;

public class PopupDialogActivity extends BaseActivity implements View.OnClickListener {


    Button btnClose;
    String TAG = "TAG_LIFE_METHOD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_dialog);

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFinishOnTouchOutside(false);

        btnClose = findViewById(R.id.btnClose);
        btnClose.setOnClickListener(this);

      //  Log.d(TAG,"PopUp Activity onCreate invoked");

    }


    //region Life Cycle Method

//    @Override
//    protected void onStart() {
//        super.onStart();
//        Log.d(TAG,"PopUp Activity onStart invoked");
//    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Log.d(TAG,"PopUp Activity onResume invoked");
//    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        Log.d(TAG,"PopUp Activity onPause invoked");
//    }
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Log.d(TAG,"PopUp Activity onStop invoked");
//    }
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        Log.d(TAG,"PopUp Activity onRestart invoked");
//    }
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Log.d(TAG,"onDestroy PopUp Activity invoked");
//    }

    //endregion
    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.btnClose)
        {
            finish();
        }

    }
}

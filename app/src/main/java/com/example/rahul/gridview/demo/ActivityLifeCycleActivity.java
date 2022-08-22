package com.example.rahul.gridview.demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import com.example.rahul.gridview.Activity.AddEmployee;
import com.example.rahul.gridview.Activity.BaseActivity;
import com.example.rahul.gridview.Activity.HomeActivity;
import com.example.rahul.gridview.R;
import com.example.rahul.gridview.Utility.Utility;

import org.w3c.dom.Text;

public class ActivityLifeCycleActivity extends BaseActivity implements View.OnClickListener {

    String TAG = "TAG_LIFE_METHOD";
    TextView txtMessage;
    Button btnNext,  btnSubmit;
    EditText etName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Log.d(TAG,"onCreate invoked");

        init();
        setOnClickListener();

//        if(savedInstanceState != null)
//        {
//           String myData = savedInstanceState.getString("MYDATA");
//
//            txtMessage.setText(myData);
//        }

    }




    //region Life Cycle Method

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart invoked");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume invoked");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause invoked");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop invoked");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart invoked");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy invoked");
    }

    //endregion

    // region Method
    private void init()
    {
        btnNext = findViewById(R.id.btnNext);
        btnSubmit = findViewById(R.id.btnSubmit);
        txtMessage = findViewById(R.id.txtMessage);
        etName = findViewById(R.id.etName);
    }

    private void setOnClickListener()
    {
        btnNext.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
    }

    private void showCamerGalleryPopUp() {
        AlertDialog.Builder builder = new   AlertDialog.Builder(this, R.style.CustomDialog);

        LinearLayout lyCamera, lyGallery, lyPdf;
        LayoutInflater inflater = this.getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.layout_cam_gallery, null);

        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        // set the custom dialog components - text, image and button
        lyCamera = (LinearLayout) dialogView.findViewById(R.id.lyCamera);
        lyGallery = (LinearLayout) dialogView.findViewById(R.id.lyGallery);

        lyCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //launchCamera();
                //    alertDialog.dismiss();

            }
        });

        lyGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // openGallery();
                //   alertDialog.dismiss();

            }
        });

        alertDialog.setCancelable(true);
        alertDialog.show();
        //  alertDialog.getWindow().setLayout(900, 600);

        // for user define height and width..
    }


    //endregion


//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putString("MYDATA",txtMessage.getText().toString());
//    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnSubmit:

                if(!etName.getText().toString().trim().isEmpty()){
                    txtMessage.setText(etName.getText().toString());
                }else {
                    Toast.makeText(this,"Please Enter text",Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.btnNext:

                startActivity(new Intent(this,SecondDemoActivity.class).putExtra(Utility.DEMO_MESSAGE,etName.getText().toString()));

                break;

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.demo_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_camera:

                showCamerGalleryPopUp();

                break;

            case  R.id.action_second:

                startActivity(new Intent(this,PopupDialogActivity.class));



        }
        return super.onOptionsItemSelected(item);
    }
}

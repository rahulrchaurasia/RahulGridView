package com.example.rahul.gridview.demo;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.example.rahul.gridview.Activity.BaseActivity;
import com.example.rahul.gridview.R;
import com.example.rahul.gridview.Utility.Utility;

public class SecondDemoActivity extends BaseActivity implements View.OnClickListener {

     TextView txtMessage;
     String strMessage = "";
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        init();
        setOnClickListener();

        if(getIntent().hasExtra(Utility.DEMO_MESSAGE)){

            strMessage =  getIntent().getStringExtra(Utility.DEMO_MESSAGE);
            txtMessage.setText("Transferred Data is "+ strMessage );
        }
    }
    // region Method
    private void init()
    {
        btnNext = findViewById(R.id.btnNext);
        txtMessage = findViewById(R.id.txtMessage);
    }



    private void setOnClickListener()
    {
        btnNext.setOnClickListener(this);

    }

    //endregion
    @Override
    public void onClick(View view) {

        switch (view.getId()){


            case R.id.btnNext:

                startActivity(new Intent(SecondDemoActivity.this,ThirdDemoActivity.class));

                break;

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;
        switch (item.getItemId()) {


            case R.id.action_chat:

                break;

        }
        return super.onOptionsItemSelected(item);
    }
}

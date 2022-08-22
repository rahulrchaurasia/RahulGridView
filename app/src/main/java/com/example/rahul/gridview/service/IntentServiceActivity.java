package com.example.rahul.gridview.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.rahul.gridview.R;
import com.example.rahul.gridview.Utility.Utility;
import com.example.rahul.gridview.utility_service.IntentServiceTask;

public class IntentServiceActivity extends AppCompatActivity {

    TextView textView;
    Button button;
    EditText editText;


    public BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction() != null) {
                String message = intent.getStringExtra("broadcastMessage");
              //  textView.setText(textView.getText() + "\n" + message);
                Toast.makeText(IntentServiceActivity.this,message,Toast.LENGTH_SHORT).show();
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.inputText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editText.getText().toString();
                Intent intent = new Intent(IntentServiceActivity.this, IntentServiceTask.class);
                intent.putExtra("message", message);
                startService(intent);
            }
        });
    }

    @Override
    protected void onStart() {

        super.onStart();
        setReceiver();
    }

    @Override
    protected void onStop() {

        super.onStop();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    private void setReceiver() {
    // registerReceiver
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Utility.FILTER_ACTION_KEY);

        LocalBroadcastManager.getInstance(IntentServiceActivity.this).registerReceiver(receiver, intentFilter);

    }




}

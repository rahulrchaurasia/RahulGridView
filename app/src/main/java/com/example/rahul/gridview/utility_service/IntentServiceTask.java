package com.example.rahul.gridview.utility_service;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;

import android.util.Log;

import androidx.annotation.Nullable;

import com.example.rahul.gridview.Utility.Utility;


/**
 * Created by Rahul on 27/12/2019.
 */
public class IntentServiceTask extends IntentService {

    String TAG = "mytask";
    public IntentServiceTask() {
        super("IntentServiceDemo");
        setIntentRedelivery(true);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Log.d(TAG,"onHandleIntent Entered");
        String message = intent.getStringExtra("message");


        SystemClock.sleep(1000);
        String echoMessage = "IntentService after a pause of 3 seconds echoes " + message;

        Intent myIntent = new Intent();
        myIntent.setAction(Utility.FILTER_ACTION_KEY);
        myIntent.putExtra("broadcastMessage", echoMessage);

       // LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(myIntent);

        this.sendBroadcast(myIntent);


    }




}

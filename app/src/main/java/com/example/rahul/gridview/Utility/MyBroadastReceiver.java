package com.example.rahul.gridview.Utility;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by IN-RB on 07-01-2018.
 */

public class MyBroadastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        int receivedNumber = intent.getIntExtra("RandomNumber",-1);

            // Display a notification that the broadcast received
            Toast.makeText(context,"Received : " + receivedNumber, Toast.LENGTH_SHORT).show();
    }
}

package com.example.rahul.gridview.utility_service;

import android.app.Service;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;


/**
 * Created by
 */
public class MessengerService extends Service {


    public MessengerService() {
    }

    private String TAG = "MessengerService";
    static final int MSG_SAY_HELLO = 1;

    // Messenger object used by clients to send messages to IncomingHandler
    Messenger mMessenger = new Messenger(new IncomingHandler());


    // Incoming messages Handler

    class IncomingHandler extends Handler {


        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SAY_HELLO:
                    Bundle bundle = msg.getData();
                    String hello = (String) bundle.get("hello");

                    Toast.makeText(getApplicationContext(), hello, Toast.LENGTH_SHORT).show();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate called");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind done");
        return mMessenger.getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return false;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

package com.example.rahul.gridview.utility_service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;


import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Rahul on 08/04/2019.
 */
// Bound Service Demo :--
public class BoundService extends Service {

    private final IBinder myBinder = new MyLocalBinder();
    public BoundService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return myBinder;
    }


    public String getCurrentTime() {
        SimpleDateFormat dateformat =
                new SimpleDateFormat("HH:mm:ss MM/dd/yyyy",
                        Locale.US);
        return (dateformat.format(new Date()));
    }

    public class  MyLocalBinder extends Binder{

       public   BoundService getService()
        {
            return BoundService.this;
        }
    }
}

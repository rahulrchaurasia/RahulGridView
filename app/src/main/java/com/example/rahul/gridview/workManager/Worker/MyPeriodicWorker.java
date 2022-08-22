package com.example.rahul.gridview.workManager.Worker;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;


import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.rahul.gridview.R;


/**
 * Created by Rahul on 21/01/2020.
 */
public class MyPeriodicWorker extends Worker {


    public static final String CHANNEL_ID = "com.example.rahul.gridview.NotifyID";
    public static final String CHANNEL_NAME = "GRIDVIEW CHANNEL";

    int NOTIFICATION_ID = 0;

    public MyPeriodicWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }
    //  public static   Context mContext;


    @NonNull
    @Override
    public Result doWork() {


        displayNotification("Periodic Notify" ,"My Data");

        return null;
       // return  Result.SUCCESS;
    }

    private void displayNotification(String title, String task) {
        NOTIFICATION_ID = (int) Math.round(Math.random() * 1000);
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder notification;
        notification = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(task)
                .setSmallIcon(R.drawable.ic_launcher1);

        notificationManager.notify(NOTIFICATION_ID, notification.build());
    }
}

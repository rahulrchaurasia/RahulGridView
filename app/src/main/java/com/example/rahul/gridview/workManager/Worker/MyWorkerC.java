package com.example.rahul.gridview.workManager.Worker;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;


import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.rahul.gridview.R;
import com.example.rahul.gridview.workManager.WorkManagerActivity;

/**
 * Created by Rahul on 20/01/2020.
 */
public class MyWorkerC extends Worker {

    public static final String CHANNEL_ID = "com.example.rahul.gridview.NotifyID";
    public static final String CHANNEL_NAME = "GRIDVIEW CHANNEL";
    public static final String KEY_TASK_OUTPUT = "key_task_output";

    int NOTIFICATION_ID = 0;



    //for sending and receiving data

    public MyWorkerC(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        displayNotification("My Worker CCC", "Data For C Description");
        return null;
      //  return Result.SUCCESS;
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

package com.example.rahul.gridview.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;

import com.example.rahul.gridview.Activity.BaseActivity;
import com.example.rahul.gridview.R;

public class DownloadNotifyBarActivity extends BaseActivity implements View.OnClickListener {

    Button btnSubmit,btnSubmit1;
    EditText etTitle, etBody;
    public static final String CHANNEL_NAME = "RAHUL GRIDVIEW CHANNEL";
    public static final String CHANNEL_ID = "com.example.rahul.gridview.NotifyID";

    private NotificationManager mManager;
    NotificationCompat.Builder notificationBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_notify_bar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        etTitle = findViewById(R.id.etTitle);
        etBody = findViewById(R.id.etBody);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit1 = findViewById(R.id.btnSubmit1);

        btnSubmit.setOnClickListener(this);
        btnSubmit1.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnSubmit:

                getNotificationProgress();
                break;
            case R.id.btnSubmit1:

                getNotificationIndeterminate();
                break;
        }

    }

    private void getNotificationProgress() {
        final int progressMax = 100;

        createChannels();


        // Create notification default intent.
        Intent intent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        // Create notification builder.

        notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID);

        notificationBuilder
                .setSmallIcon(R.drawable.ic_launcher1)
                .setContentTitle("Download")
                .setContentText("Download in Progress")
                .setOngoing(true)
                .setOnlyAlertOnce(true)
                .setChannelId(CHANNEL_ID)
                // Make the notification max priority.
                .setPriority(Notification.PRIORITY_MAX)
                .setProgress(progressMax, 0, false)
                // Make head-up notification.
                .setFullScreenIntent(pendingIntent, true);


        // Build the notification.
        getManager().notify(2, notificationBuilder.build());

        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);

                for (int progress = 0; progress <= progressMax; progress += 10) {
                    notificationBuilder.setProgress(progressMax, progress, false);
                    getManager().notify(2, notificationBuilder.build());
                    SystemClock.sleep(1000);
                }
                notificationBuilder.setContentText("Download Finished")
                        .setProgress(0, 0, false)
                        .setOngoing(false);
                getManager().notify(2, notificationBuilder.build());
            }
        }).start();


    }



    private void getNotificationIndeterminate() {
        final int progressMax = 100;

        createChannels();


        // Create notification default intent.
        Intent intent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        // Create notification builder.

        notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID);

        notificationBuilder
                .setSmallIcon(R.drawable.ic_launcher1)
                .setContentTitle("Download")
                .setContentText("Download in Progress")
                .setOngoing(true)
                .setOnlyAlertOnce(true)
                .setChannelId(CHANNEL_ID)
                // Make the notification max priority.
                .setPriority(Notification.PRIORITY_MAX)
                .setProgress(progressMax, 0, true)                      //  Only Change Here
                // Make head-up notification.
                .setFullScreenIntent(pendingIntent, true);


        // Build the notification.
        getManager().notify(2, notificationBuilder.build());

        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);

                for (int progress = 0; progress <= progressMax; progress += 10) {

                    SystemClock.sleep(1000);
                }
                notificationBuilder.setContentText("Download Finished")
                        .setProgress(0, 0, false)
                        .setOngoing(false);
                getManager().notify(2, notificationBuilder.build());
            }
        }).start();


    }

    public void createChannels() {
        if (Build.VERSION.SDK_INT >= 26) {


            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH);
            channel.enableLights(true);
            channel.enableVibration(true);
            channel.setLightColor(Color.BLUE);
            channel.setDescription("Finmart");
            // Sets whether notifications posted to this channel appear on the lockscreen or not
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);    // Notification.VISIBILITY_PRIVATE
            getManager().createNotificationChannel(channel);
        }
    }


    private NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }

}

package com.example.rahul.gridview.Utility;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.rahul.gridview.Activity.LocationTracker;
import com.example.rahul.gridview.Activity.Marshmellow_Permission;
import com.example.rahul.gridview.Activity.ProfileActivity;
import com.example.rahul.gridview.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    Bitmap bitmap_image;
    public static final String CHANNEL_ID = "com.example.rahul.gridview";
    public static final String CHANNEL_NAME = "GRID CHANNEL";
    private NotificationManager mManager;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //Displaying data in log
        //It is optional
        Log.d(TAG, "Data: " + remoteMessage.getData());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());


        sendNotification(remoteMessage, remoteMessage.getData());
    }

    //This method is only generating push notification
    //It is same as we did in earlier posts
    private void sendNotification(RemoteMessage remoteMessage, Map<String, String> data) {

        Map<String, String> NotifyData = remoteMessage.getData();
//        String text= NotifyData.get("notifyFlag");
        Bitmap imgNotify = null;
        String img_url = data.get("img_url");
        bitmap_image = getBitmapfromUrl(img_url);
        Intent intent;

        String type = data.get("notifyFlag");
        if (type.matches("F")) {
            intent = new Intent(this, ProfileActivity.class);
        } else if (type.matches("L")) {
            intent = new Intent(this, Marshmellow_Permission.class);
        } else {
            intent = new Intent(this, LocationTracker.class);
        }


        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, (int) Math.round(Math.random() * 100), intent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);   //  for Sound
        createChannels();


        NotificationCompat.BigPictureStyle BigPicstyle = new NotificationCompat.BigPictureStyle()
                .bigPicture(bitmap_image)
                .setBigContentTitle(NotifyData.get("title"))
                .setSummaryText(NotifyData.get("body"))
                .bigLargeIcon(null);

        NotificationCompat.BigTextStyle BigTextstyle = new NotificationCompat.BigTextStyle()
                .bigText(NotifyData.get("body"))
                .setBigContentTitle(NotifyData.get("title"));

/////////////////////////////////////////////////////////////////////////////
        NotificationCompat.Builder notificationBuilder = null;


        notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID);

        if (bitmap_image != null) {
            notificationBuilder.setStyle(BigPicstyle);
            notificationBuilder.setLargeIcon(bitmap_image);
        } else {
            notificationBuilder.setStyle(BigTextstyle);
            notificationBuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher1));
        }

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            notificationBuilder.setSmallIcon(R.drawable.rc_lg);
            notificationBuilder.setColor(getResources().getColor(R.color.colorPrimary));
            //  .setColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary))
        } else{
            notificationBuilder.setSmallIcon(R.drawable.rc_lg);

        }

        notificationBuilder
                    .setSmallIcon(R.drawable.rc_lg)
                    .setContentTitle(NotifyData.get("title"))
                    .setContentText(NotifyData.get("body"))
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setLargeIcon(bitmap_image)
                    .setTicker("Notification ticker!")
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setNumber(1)
                    .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
                    .setContentIntent(pendingIntent);


            ///////////

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.notify(0, notificationBuilder.build());







    }

    /*
     *To get a Bitmap image from the URL received
     * */


    public Bitmap getBitmapfromUrl (String imageUrl){
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;

        }
    }

    public void createChannels() {
        if (Build.VERSION.SDK_INT >= 26) {


            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH);
            channel.enableLights(true);
            channel.enableVibration(true);
            channel.setLightColor(Color.BLUE);
            channel.setDescription("Elite");
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
package com.example.rahul.gridview.Activity;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;

import android.os.ParcelFileDescriptor;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.FileProvider;

import com.example.rahul.gridview.R;
import com.example.rahul.gridview.Utility.Utility;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Nilesh Birhade on 16-01-2017.
 */

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog dialog;
    private static final CharSequence LOADING = "Loading...";
    CustomPopUpListener customPopUpListener;
    // public static Realm realm;
    public static final int REQUEST_PERMISSION_SETTING = 101;
    public static final String CHANNEL_NAME = "RAHUL GRIDVIEW CHANNEL";
    public static final String CHANNEL_ID = "com.example.rahul.gridview.NotifyID";
    private NotificationManager mManager;
    NotificationCompat.Builder notificationBuilder;

    public void getToast(String strMessage){

        Toast.makeText(BaseActivity.this, strMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showProgressDialog() {
        dialog = ProgressDialog.show(this, "", LOADING, false);
    }

    public void dismissDialog() {
        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    protected void cancelDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    protected void showDialog() {
//        dialog = ProgressDialog.show(BaseActivity.this, "", "Loading...", true);
        showDialog("Loading...");
    }

    protected void showDialog(String msg) {
        if (dialog == null)
            dialog = ProgressDialog.show(BaseActivity.this, "", msg, true);
        else {
            if (!dialog.isShowing())
                dialog = ProgressDialog.show(BaseActivity.this, "", msg, true);
        }
    }

    public static boolean isEmpty(EditText editText) {
        String text = editText.getText().toString().trim();
        return !(text.isEmpty());
    }

    public static boolean isValideEmailID(EditText editText) {
        String emailEntered = editText.getText().toString().trim();
        return !(emailEntered.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailEntered).matches());
    }


    public String getminuteDate(String tempdate) {

        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-YYYY hh:mm:ss a", Locale.US);
            Date date = format.parse(tempdate);
            format = new SimpleDateFormat("HH:mm");
            String dateString = format.format(date);
            return dateString;
        } catch (Exception ex) {
            return "";
        }


    }


    public File createFile(String name) {
        FileOutputStream outStream = null;

        File dir = Utility.createDirIfNotExists();
        String fileName = name + ".jpg";
        fileName = fileName.replaceAll("\\s+", "");
        File outFile = new File(dir, fileName);

        return outFile;
    }

    public File saveImageToStorage(Bitmap bitmap, String name) {
        FileOutputStream outStream = null;

        File dir = Utility.createDirIfNotExists();
        String fileName = name + getCurrentTime()+ ".jpg";
        fileName = fileName.replaceAll("\\s+", "");
        File outFile = new File(dir, fileName);
        try {
            outStream = new FileOutputStream(outFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 70, outStream);
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outFile;
    }

    public boolean deleteAll(){

        File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "/Demo-App");
        if (dir.isDirectory())
        {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++)
            {
                new File(dir, children[i]).delete();
            }
        }
        return true;
    }
    public String getCurrentTime() {
        DateFormat dateFormat = new SimpleDateFormat("DDMMYYYYhhmmss", Locale.US);
        Date date = new Date();
        return dateFormat.format(date);
    }


    public String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss a");
        Date date = new Date();
        return dateFormat.format(date);
    }


    public void openPopUp(final View view, String title, String desc, String positiveButtonName, String negativeButtonName, boolean isNegativeVisible, boolean isCancelable) {
        try {
            final Dialog dialog;
            dialog = new Dialog(BaseActivity.this, R.style.CustomDialog);

            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.layout_common_popup);

            TextView tvTitle = (TextView) dialog.findViewById(R.id.tvTitle);
            tvTitle.setText(title);
            TextView tvOk = (TextView) dialog.findViewById(R.id.tvOk);
            tvOk.setText(positiveButtonName);

            TextView tvCancel = (TextView) dialog.findViewById(R.id.tvCancel);
            tvCancel.setText(negativeButtonName);
            if (isNegativeVisible) {
                tvCancel.setVisibility(View.VISIBLE);
            } else {
                tvCancel.setVisibility(View.GONE);
            }

            TextView txtMessage = (TextView) dialog.findViewById(R.id.txtMessage);
            txtMessage.setText(desc);
            ImageView ivCross = (ImageView) dialog.findViewById(R.id.ivCross);

            dialog.setCancelable(isCancelable);
            dialog.setCanceledOnTouchOutside(isCancelable);

            Window dialogWindow = dialog.getWindow();
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = lp.MATCH_PARENT;  // Width
            lp.height = lp.WRAP_CONTENT; // Height
            dialogWindow.setAttributes(lp);

            dialog.show();
            tvOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Close dialog
                    if (customPopUpListener != null)
                        customPopUpListener.onPositiveButtonClick(dialog, view);
                }
            });

            tvCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Close dialog
                    if (customPopUpListener != null)
                        customPopUpListener.onCancelButtonClick(dialog, view);
                }
            });
            ivCross.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Close dialog
                    if (customPopUpListener != null)
                        customPopUpListener.onCancelButtonClick(dialog, view);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void datashareList(Context context, Bitmap bitmap, String prdSubject, String prdDetail) {

        //  String Deeplink = "https://nykaa.ly/P_" + Sharedata_product_id;

        //  String prdSubject = "Look what I found on Nykaa!";
        //  String prdDetail = "Check out " + Sharedata_product_name + " on Nykaa" + "\n" + Deeplink;
        Uri screenshotUri = null;
        try {


           if(bitmap != null){
               File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "Finmart_product.jpg");

               file.getParentFile().mkdirs();
               FileOutputStream out = new FileOutputStream(file);
               bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
               out.close();

               //  Uri screenshotUri = Uri.fromFile(file);
                screenshotUri = FileProvider.getUriForFile(BaseActivity.this,
                       getString(R.string.file_provider_authority),
                       file);
           }


            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);

            shareIntent.setType("text/plain");

            PackageManager pm = context.getPackageManager();


            List<ResolveInfo> resInfo = pm.queryIntentActivities(shareIntent, 0);
            List<LabeledIntent> intentList = new ArrayList<LabeledIntent>();

            for (int i = 0; i < resInfo.size(); i++) {
                // Extract the label, append it, and repackage it in a LabeledIntent
                ResolveInfo ri = resInfo.get(i);
                String packageName = ri.activityInfo.packageName;
                String processName = ri.activityInfo.processName;
                String AppName = ri.activityInfo.name;

                if ((packageName.contains("android.email") || packageName.contains("mms") || packageName.contains("twitter") || (packageName.contains("whatsapp")) || packageName.contains("messaging") || packageName.contains("android.gm") || packageName.contains("com.google.android.apps.plus")) || (packageName.contains("apps.docs")) && processName.contains("android.apps.docs:Clipboard") || (packageName.contains("android.talk")) && AppName.contains("hangouts")) {

                    shareIntent.setComponent(new ComponentName(packageName, ri.activityInfo.name));

                    if (packageName.contains("android.email")) {
                        shareIntent.setType("image/*");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, prdSubject);
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                        shareIntent.setPackage(packageName);

                    } else if (packageName.contains("twitter")) {

                        shareIntent.setType("image/*");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, prdSubject);
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                       // shareIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                        shareIntent.setPackage(packageName);

                    }
//                    else if (packageName.contains("facebook.katana")) {
//                        shareIntent.setType("text/plain");
//                        shareIntent.putExtra(Intent.EXTRA_TEXT, product.getImageUrl());
//                        shareIntent.setPackage("com.facebook.katana");
//                        //shareIntent.putExtra(Intent.EXTRA_STREAM, Deeplink);
//                    }
//                    else if (packageName.contains("facebook.orca")) {
//                        shareIntent.setType("image/*");
//                        shareIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
//                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
//                        shareIntent.setPackage("com.facebook.orca");
//
//                    }
                    else if (packageName.contains("mms")) {
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                        shareIntent.setPackage(packageName);

                    } else if (packageName.contains("whatsapp")) {
                       // shareIntent.setType("image/*");
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                       // shareIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                        shareIntent.setPackage(packageName);


                    } else if (packageName.contains("messaging")) {
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                        shareIntent.setPackage(packageName);
                    } else if (packageName.contains("com.google.android.apps.plus")) {
                        shareIntent.setType("image/*");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                        //shareIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                        shareIntent.setPackage(packageName);

                    } else if (packageName.contains("android.talk")) {
                        if (AppName.contains("hangouts")) {
                            shareIntent.setType("image/*");
                            shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                           // shareIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                            shareIntent.setPackage(packageName);
                        }

                    } else if (packageName.contains("apps.docs")) {
                        if (processName.contains("android.apps.docs:Clipboard")) {
                            shareIntent.setType("text/plain");
                            shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                            shareIntent.setPackage(packageName);
                        }

                    } else if (packageName.contains("android.gm")) {
                        shareIntent.setType("image/*");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, prdSubject);
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                      //  shareIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                        shareIntent.setPackage(packageName);

                    } else {
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);

                    }

                    intentList.add(new LabeledIntent(shareIntent, packageName, ri.loadLabel(pm), ri.icon));

                }
            }


            if (intentList.size() > 1) {
                intentList.remove(intentList.size() - 1);
            }

            Intent openInChooser = Intent.createChooser(shareIntent, "Share Via");
            // convert intentList to array
            LabeledIntent[] extraIntents = intentList.toArray(new LabeledIntent[intentList.size()]);
            openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents);

            startActivity(openInChooser);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public File createImageFile(String name)  {
        // Create an image file name
        File temp;
        String timeStamp  =new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File storageDir  = getAppSpecificAlbumStorageDir(this, Environment.DIRECTORY_PICTURES,"DemoGrid");
        try {
            temp =  File.createTempFile(name + timeStamp, /* prefix */
                    ".jpg", /* suffix */
                    storageDir /* directory */
            );

            Log.d("IMAGE_PATH","File Name"+ temp.getName() + "File Path" +temp.getAbsolutePath());
            //  String  currentPhotoPath = temp.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return  null;
        }
        return  temp;
    }

    public File getAppSpecificAlbumStorageDir(Context context,String albumName ,  String subAlbumName) {
        // Get the pictures directory that's inside the app-specific directory on
        // external storage.
        File file = new File(context.getExternalFilesDir(albumName), subAlbumName);
        if (file.mkdirs()) {
            Log.e("fssfsf", "Directory not created");
        }

        return file;
    }

    public Bitmap getBitmapFromContentResolver(Uri selectedFileUri) {
        try {
            ParcelFileDescriptor parcelFileDescriptor =
                    getContentResolver().openFileDescriptor(selectedFileUri, "r");
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);

            parcelFileDescriptor.close();
            return  image;
        } catch (IOException e) {
            e.printStackTrace();
            return  null;
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
            channel.setDescription("Finmart");
            // Sets whether notifications posted to this channel appear on the lockscreen or not
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);    // Notification.VISIBILITY_PRIVATE
            getManager().createNotificationChannel(channel);
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

    private NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }

    public void openAppSetting()
    {

        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
    }

    public void registerCustomPopUp(CustomPopUpListener customPopUpListener) {
        if (customPopUpListener != null)
            this.customPopUpListener = customPopUpListener;
    }


    public interface CustomPopUpListener {

        void onPositiveButtonClick(Dialog dialog, View view);

        void onCancelButtonClick(Dialog dialog, View view);

    }



//    }
}

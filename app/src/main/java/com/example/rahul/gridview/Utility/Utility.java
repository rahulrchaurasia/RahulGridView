package com.example.rahul.gridview.Utility;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.os.Environment;

import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Rahul on 20-02-2017.
 */
public class Utility {

    public static String ID = "ID";
    public static String EMAIL_ID = "EMAIL_ID";
    public static String DEVICE_TOKEN = "devicetoken";
    public static String DEMO_MESSAGE = "demo_message";
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    public static final int PERMISSION_CAMERA_STORACGE_CONSTANT = 103;
    public static final int REQUEST_PERMISSION_SETTING = 101;
    public static final String FILTER_ACTION_KEY = "MESSAGE_BROADCAST";

    public static String[] perms = {"android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.ACCESS_FINE_LOCATION",
            "android.permission.ACCESS_COARSE_LOCATION"
    };
    public static final int REQUEST_CODE_ASK_PERMISSIONS = 1115;
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static boolean checkPermission(final Context context)
    {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if(currentAPIVersion>=android.os.Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage("External storage permission is necessary");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();

                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public static int getRandomMaterialColor(Context mcontext, String typeColor) {
        int returnColor = Color.GRAY;
        int arrayId = mcontext.getResources().getIdentifier("mdcolor_" + typeColor, "array", mcontext.getPackageName());

        if (arrayId != 0) {
            TypedArray colors =  mcontext.getResources().obtainTypedArray(arrayId);
            int index = (int) (Math.random() * colors.length());
            returnColor = colors.getColor(index, Color.GRAY);
            colors.recycle();
        }
        return returnColor;
    }

    public static File createDirIfNotExists() {

        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "/Demo-App");
        if (!file.exists()) {
            if (!file.mkdirs()) {
                Log.e("TravellerLog :: ", "Problem creating Image folder");

            }
        }
        return file;
    }

    public static void hideKeyBoard(View view, Context context) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static MultipartBody.Part getMultipartImage(File file ,RequestBody body) {
       // RequestBody imgBody = RequestBody.create( file , MediaType.parse("image/*"));
        MultipartBody.Part imgFile = MultipartBody.Part.createFormData("docfile", file.getName(), body);
        return imgFile;
    }


    public static HashMap<String, Integer> getBody(Context context, int userid, int doctype ) {
        HashMap<String, Integer> body = new HashMap<String, Integer>();


        body.put("userid", (userid));
        body.put("doctype", (doctype));
      //  body.put("order_id", (orderID));

        return body;
    }

}

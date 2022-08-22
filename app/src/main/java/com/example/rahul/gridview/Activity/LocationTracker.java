package com.example.rahul.gridview.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.example.rahul.gridview.R;
import com.example.rahul.gridview.geocodeloction.AppLocationService;
import com.example.rahul.gridview.geocodeloction.LocationAddress;

public class LocationTracker extends BaseActivity implements View.OnClickListener{

    Button btnGPSShowLocation;
    Button btnShowAddress;
    TextView tvAddress;
    final private int REQUEST_CODE_ASK_PERMISSIONS_INIT = 1111;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 1112;
    String[] perms = {"android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.ACCESS_FINE_LOCATION",
            "android.permission.ACCESS_COARSE_LOCATION",
    };

    AppLocationService appLocationService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_tracker);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (!checkPermission()) {
            requestPermission("S");
        }

        tvAddress = (TextView) findViewById(R.id.tvAddress);
        appLocationService = new AppLocationService(
                LocationTracker.this);

        btnGPSShowLocation = (Button) findViewById(R.id.btnGPSShowLocation);
        btnShowAddress = (Button) findViewById(R.id.btnShowAddress);

        btnGPSShowLocation.setOnClickListener(this);
        btnShowAddress.setOnClickListener(this);


//        btnGPSShowLocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                Location gpsLocation = appLocationService
//                        .getLocation(LocationManager.GPS_PROVIDER);
//                if (gpsLocation != null) {
//                    double latitude = gpsLocation.getLatitude();
//                    double longitude = gpsLocation.getLongitude();
//                    String result = "Latitude: " + gpsLocation.getLatitude() +
//                            " Longitude: " + gpsLocation.getLongitude();
//                    tvAddress.setText(result);
//                } else {
//                    showSettingsAlert();
//                }
//            }
//        });
//
//
//        btnShowAddress.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//
//                Location location = appLocationService
//                        .getLocation(LocationManager.GPS_PROVIDER);
//
//                //you can hard-code the lat & long if you have issues with getting it
//                //remove the below if-condition and use the following couple of lines
//                //double latitude = 37.422005;
//                //double longitude = -122.084095
//
//                if (location != null) {
//                    double latitude = location.getLatitude();
//                    double longitude = location.getLongitude();
//                    LocationAddress locationAddress = new LocationAddress();
//                    locationAddress.getAddressFromLocation(latitude, longitude,
//                            getApplicationContext(), new GeocoderHandler());
//                } else {
//                    showSettingsAlert();
//                }
//
//            }
//        });
    }



    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.btnGPSShowLocation)
        {
            if (!checkPermission()) {
                requestPermission("M");
            }else {
                showLocation();
            }
        }
        else if(view.getId() == R.id.btnShowAddress)
        {

            if (!checkPermission()) {
                requestPermission("M");
            }else {
                showAddress();
            }
        }

    }


    private void showLocation()
    {
        Location gpsLocation = appLocationService.getLocation(LocationManager.GPS_PROVIDER);
        if (gpsLocation != null) {
            double latitude = gpsLocation.getLatitude();
            double longitude = gpsLocation.getLongitude();
            String result = "Latitude: " + gpsLocation.getLatitude() +
                    " Longitude: " + gpsLocation.getLongitude();
            tvAddress.setText(result);
        } else {
            showSettingsAlert();
        }
    }

    private void showAddress()
    {
        Location location = appLocationService
            .getLocation(LocationManager.GPS_PROVIDER);

        //you can hard-code the lat & long if you have issues with getting it
        //remove the below if-condition and use the following couple of lines
        //double latitude = 37.422005;
        //double longitude = -122.084095

        if (location != null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            LocationAddress locationAddress = new LocationAddress();
            locationAddress.getAddressFromLocation(latitude, longitude,
                    getApplicationContext(), new GeocoderHandler());
        } else {
            showSettingsAlert();
        }
    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                LocationTracker.this);
        alertDialog.setTitle("SETTINGS");
        alertDialog.setMessage("Enable Location Provider! Go to settings menu?");
        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        LocationTracker.this.startActivity(intent);
                    }
                });
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }

    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            tvAddress.setText(locationAddress);
        }
    }

    private boolean checkPermission() {

        int writeLogResult = ActivityCompat.checkSelfPermission(getApplicationContext(), perms[0]);
        int fineLocation = ActivityCompat.checkSelfPermission(getApplicationContext(), perms[1]);
        int coarseLocation = ActivityCompat.checkSelfPermission(getApplicationContext(), perms[2]);

        return writeLogResult == PackageManager.PERMISSION_GRANTED
                && fineLocation == PackageManager.PERMISSION_GRANTED
                && coarseLocation == PackageManager.PERMISSION_GRANTED;
    }
    private void requestPermission(String initial) {
        if(initial.equals("S")) {
            ActivityCompat.requestPermissions(this, perms, REQUEST_CODE_ASK_PERMISSIONS_INIT);
        }else
        {
            ActivityCompat.requestPermissions(this, perms, REQUEST_CODE_ASK_PERMISSIONS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults.length > 0) {

                    boolean writeExternal = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean accessFine = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean accessCorase = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (accessFine && writeExternal && accessCorase) {


                    } else {

                        //Permission Denied, You cannot access location data and camera
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                            showMessageOKCancel("You need to grant the permissions", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //  finish();
                                }
                            });

                        }

                    }
                }
                break;

        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(LocationTracker.this)
                .setTitle("Exit")
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                //.setNegativeButton("Cancel", null)
                .create()
                .show();
    }
}

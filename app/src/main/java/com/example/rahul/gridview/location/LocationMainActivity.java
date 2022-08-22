package com.example.rahul.gridview.location;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.example.rahul.gridview.Activity.BaseActivity;
import com.example.rahul.gridview.R;

public class LocationMainActivity extends BaseActivity implements View.OnClickListener, LocationService.ILocation {

    LocationService locationService;
    Button btnSubmit;
    private static final int REQUEST_CHECK_SETTINGS = 0x1;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 1111;

    String[] perms = {
            Manifest.permission.ACCESS_FINE_LOCATION

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(this);

        locationService = new LocationService(this);

        locationService.setLocationListener(this);

        if (!checkPermission()) {

                requestPermission();
            }

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btnSubmit) {

                locationService.createLocationRequest();


        }

    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            // Google Api Location Request Callback (ie User click on OK and NO,THANKS )
            // Check for the integer request code originally supplied to startResolutionForResult().
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:

                        Toast.makeText(LocationMainActivity.this, "User agreed .", Toast.LENGTH_SHORT).show();
                        // Nothing to do. startLocationupdates() gets called in onResume again.
                        locationService.startLocationUpdates();
                        break;
                    case Activity.RESULT_CANCELED:

                        Toast.makeText(LocationMainActivity.this, "User Not", Toast.LENGTH_SHORT).show();
                        //        locationService.stopLocationUpdates();
//                        updateUI();
                        break;
                }
                break;
        }
    }

    @Override
    public void getLocation(Location location) {


        if (location != null) {

            locationService.stopLocationUpdates();
            Toast.makeText(this, "Location Callback" + location.getLatitude() + " And " + location.getLongitude(), Toast.LENGTH_LONG).show();


        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationService.stopLocationUpdates();
    }


    private boolean checkPermission() {

            int location = ActivityCompat.checkSelfPermission(this, perms[0]);

        return location == PackageManager.PERMISSION_GRANTED;


    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, perms, REQUEST_CODE_ASK_PERMISSIONS);
    }





}

package com.example.rahul.gridview.Utility;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;


import org.jetbrains.annotations.NotNull;

public class LocationUtilsService {

    private static final String TAG = "LocationUtilsService";

    /*
     * Write log files to device
     */
    //private FUDMLogger mLogger;

    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;
    /**
     * The desired interval for location updates. Inexact. Updates may be more or less frequent.
     */
    private static final long UPDATE_INTERVAL = 30000; // Every 60 seconds.

    /**
     * The fastest rate for active location updates. Updates will never be more frequent
     * than this value, but they may be less frequent.
     */
    private static final long FASTEST_UPDATE_INTERVAL = UPDATE_INTERVAL / 2; // Every 30 seconds

    /**
     * The max time before batched results are delivered by location services. Results may be
     * delivered sooner than this interval.
     */
    private static final long MAX_WAIT_TIME = UPDATE_INTERVAL * 5; // Every 5 minutes.

    /**
     * Stores parameters for requests to the FusedLocationProviderApi.
     */

    /**
     * Provides access to the Fused Location Provider API.
     */
    private FusedLocationProviderClient mFusedLocationClient;

    private Context mContext;

    //private SPbean mSPbean;

    ILocation mILocation;

    private GoogleApiAvailability googleApiAvailability;

    private LocationManager locationManager;

    //private Util util;

    //check location permission
    // AskRunTimePermission runTimePermission;

    public LocationUtilsService(Context context) {
        mContext = context;
        //  mSPbean = new SPbean(context);
        //  mLogger = new FUDMLogger(context);
        googleApiAvailability = GoogleApiAvailability.getInstance();
        // util = new Util();

    }

    public void registerCallback(ILocation iLocation) {
        mILocation = iLocation;
    }

    private LocationRequest getLocationRequest() {
        LocationRequest mLocationRequest = LocationRequest.create();

        // Sets the desired interval for active location updates. This interval is
        // inexact. You may not receive updates at all if no location sources are available, or
        // you may receive them slower than requested. You may also receive updates faster than
        // requested if other applications are requesting location at a faster interval.
        // Note: apps running on "O" devices (regardless of targetSdkVersion) may receive updates
        // less frequently than this interval when the app is no longer in the foreground.
        mLocationRequest.setInterval(UPDATE_INTERVAL);

        // Sets the fastest rate for active location updates. This interval is exact, and your
        // application will never receive updates faster than this value.
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL);

        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        // Sets the maximum time when batched location updates are delivered. Updates may be
        // delivered sooner than this interval.
        mLocationRequest.setMaxWaitTime(MAX_WAIT_TIME);
        mLocationRequest.setSmallestDisplacement(100);
        // mLocationRequest.setWaitForAccurateLocation(true);
        return mLocationRequest;
    }

    public void connectLocation() {
        //  mFusedLocationClient.requestLocationUpdates(mLocationRequest, getPendingIntent());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
                //  mLogger.writeLogs(FILE_LOGGER.LOCATION, "connectLocation", "location permission not given");
                return;
            }
        }

        if (googleApiAvailability.isGooglePlayServicesAvailable(mContext) == ConnectionResult.SUCCESS) {
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(mContext);
            mFusedLocationClient.requestLocationUpdates(getLocationRequest(), mLocationCallback, null);

        } else {
            locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                    !locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                    !locationManager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER)) {
                // util.sendAlert(mContext, Util.ALERT_CAT_TRACKING_DISABLED, "", Util.ALERT_REASON_TRACKING_PROVIDER);
            } else {
                if (locationManager != null) {

                    locationManager.requestLocationUpdates(
                            getBestProvider(),
                            FASTEST_UPDATE_INTERVAL,
                            30,
                            locationListen
                    );

                }

            }

        }
    }


    private String getBestProvider() {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setSpeedRequired(true);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        return locationManager.getBestProvider(criteria, true);

    }

    public void stopLocationUpdate() {
        mFusedLocationClient.removeLocationUpdates(mLocationCallback);
    }

    LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(@NonNull @NotNull LocationResult locationResult) {
            Location mCurrentLocation = locationResult.getLastLocation();
            Log.d(TAG, "---location :" + mCurrentLocation.getLatitude() + " : " + mCurrentLocation.getLongitude());
            Log.d(TAG, "---location Acc :" + mCurrentLocation.getAccuracy());
/*
            mSPbean.setPreference(mSPbean.LATITUDE, String.valueOf(mCurrentLocation.getLatitude()));
            mSPbean.setPreference(mSPbean.LONGITUDE, String.valueOf(mCurrentLocation.getLongitude()));
*/

            /*
            Callback for location if called from registered components.
            * */

            // if fusedLocation gets location null then we fetch location from available location manager
            // From GPS or Network which ever provides the location
            if (mCurrentLocation == null) {
                locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                locationManager.requestLocationUpdates(
                        getBestProvider(),
                        FASTEST_UPDATE_INTERVAL,
                        30,
                        locationListen
                );

               // mLogger.writeLogs(FILE_LOGGER.LOCATION, "mLocationCallback", "Location Received null");
            } else {
//                mLogger.writeLogs(FILE_LOGGER.LOCATION, "mLocationCallback", "Location LatLong: " + mCurrentLocation.getLatitude() +
//                        "," + mCurrentLocation.getLongitude() + "\n Accuracy: " + mCurrentLocation.getAccuracy() + " \nProvider: " + mCurrentLocation.getProvider() +
//                        " \nSpeed: " + mCurrentLocation.getSpeed() + " \nIs mocked: " + mCurrentLocation.isFromMockProvider());
            }
            if (mILocation != null) mILocation.getLocation(mCurrentLocation);

            super.onLocationResult(locationResult);
        }

        @Override
        public void onLocationAvailability(@NonNull @NotNull LocationAvailability locationAvailability) {

            if (!locationAvailability.isLocationAvailable()) {
                connectLocation();
            }

            super.onLocationAvailability(locationAvailability);

        }
    };

    LocationListener locationListen = new LocationListener() {
        @Override
        public void onLocationChanged(Location mCurrentLocation) {

           /*
            Callback for location if called from registered components.
            * */
            if (mCurrentLocation == null) {
              //  mLogger.writeLogs(FILE_LOGGER.LOCATION, "locationListen", "Location null");
            } else {
//                mLogger.writeLogs(FILE_LOGGER.LOCATION, "locationListen", "Location LatLong: " + mCurrentLocation.getLatitude() +
//                        "," + mCurrentLocation.getLongitude() + "\n Accuracy: " + mCurrentLocation.getAccuracy() + " \nProvider: " + mCurrentLocation.getProvider() +
//                        " \nSpeed: " + mCurrentLocation.getSpeed() + " \nIs mocked: " + mCurrentLocation.isFromMockProvider());
            }
            if (mILocation != null) mILocation.getLocation(mCurrentLocation);

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    public interface ILocation {
        void getLocation(Location currentLocation);
    }

}

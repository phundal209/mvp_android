package com.example.phundal2091.basicapplication.wrapper;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class LocationClient {
    private Context context;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    public static final int LOCATION_PERMISSION_TAG = 1;


    public LocationClient(Context context) {
        this.context = context;
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
    }

    public void getLastKnownLocation(final IOnLocationRetrieved onLocationRetrieved) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_TAG);
            return;
        }
        mFusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener((Activity) context, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        onLocationRetrieved.onRetrieved(location);
                    }
                });
    }

    public interface IOnLocationRetrieved {
        void onRetrieved(Location location);
    }
}

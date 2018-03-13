package com.example.phundal2091.basicapplication.ui.bars;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.example.phundal2091.basicapplication.framework.Presenter;
import com.example.phundal2091.basicapplication.location_helper.LocationHelper;
import com.example.phundal2091.basicapplication.wrapper.LocationClient;
import com.example.services.IApiService;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


import static com.example.phundal2091.basicapplication.wrapper.LocationClient.LOCATION_PERMISSION_TAG;

public class BarsPresenter extends Presenter<BarsView, Object> implements IBarsPresenter {
    private GeoDataClient mGeoDataClient;
    private PlaceDetectionClient mPlaceDetectionClient;
    private Location mLocation;
    private LocationClient locationClient;
    private static final String TAG = BarsPresenter.class.getSimpleName();
    private IApiService apiService;

    public BarsPresenter(Context context, BarsView view, IApiService apiService) {
        super(context, view, false);
        this.apiService = apiService;
        this.locationClient = new LocationClient(context);
        this.mGeoDataClient = Places.getGeoDataClient(context, null);
        this.mPlaceDetectionClient = Places.getPlaceDetectionClient(context, null);
    }

    @Override
    public void bindControls() {
        getLikelyPlaces();
    }
    

    private void getLikelyPlaces() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_TAG);
            return;
        }
        Task<PlaceLikelihoodBufferResponse> placeResult = mPlaceDetectionClient.getCurrentPlace(null);
        placeResult.addOnCompleteListener(new OnCompleteListener<PlaceLikelihoodBufferResponse>() {
            @Override
            public void onComplete(@NonNull Task<PlaceLikelihoodBufferResponse> task) {
                PlaceLikelihoodBufferResponse likelyPlaces = task.getResult();
                for (PlaceLikelihood placeLikelihood : likelyPlaces) {
                    Log.i(TAG, String.format("Place '%s' has likelihood: %g",
                            placeLikelihood.getPlace().getName(),
                            placeLikelihood.getLikelihood()));
                }
                likelyPlaces.release();
            }
        });
    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

    }

    @Override
    public void setLocation(Location location) {
        this.mLocation = location;
    }

    @Override
    public void setGeoClient(GeoDataClient mGeoDataClient) {
        this.mGeoDataClient = mGeoDataClient;
    }
}

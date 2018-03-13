package com.example.phundal2091.basicapplication.ui.bars;


import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;

import com.example.phundal2091.basicapplication.framework.Presenter;
import com.example.phundal2091.basicapplication.location_helper.LocationHelper;
import com.example.phundal2091.basicapplication.wrapper.LocationClient;
import com.example.services.IApiService;
import com.google.android.gms.location.places.GeoDataClient;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class BarsPresenter extends Presenter<BarsView, Object> implements IBarsPresenter {
    private GeoDataClient mGeoDataClient;
    private Location mLocation;
    private LocationClient locationClient;
    private LocationHelper locationHelper;
    private static final String TAG = BarsPresenter.class.getSimpleName();
    private IApiService apiService;

    public BarsPresenter(Context context, BarsView view, IApiService apiService) {
        super(context, view, false);
        this.apiService = apiService;
        this.locationClient = new LocationClient(context);
        this.locationHelper = new LocationHelper(context);
    }

    @Override
    public void bindControls() {
        getNearbyLocationResponse();
    }

    private void getNearbyLocationResponse() {
        locationClient.getLastKnownLocation(new LocationClient.IOnLocationRetrieved() {
            @Override
            public void onRetrieved(Location location) {
                if (location == null) {
                    String latlng = mLocation.getLatitude() + "," + mLocation.getLongitude();
                    Log.i(TAG, "latlng = " + latlng);
                    makeApiForNearbyPlaces(locationHelper.getAddress(mLocation), latlng);
                } else {
                    String latlng = location.getLatitude() + "," + location.getLongitude();
                    Log.i(TAG, "latlng = " + latlng);
                    makeApiForNearbyPlaces(locationHelper.getAddress(location), latlng);
                }

            }
        });
    }

    private void makeApiForNearbyPlaces(String address, String latlng) {
        apiService.getNearbyPlaces(address, latlng)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {

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

package com.example.phundal2091.basicapplication.ui.bars;


import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.example.phundal2091.basicapplication.framework.Presenter;
import com.example.phundal2091.basicapplication.wrapper.LocationClient;
import com.google.android.gms.location.places.GeoDataClient;

public class BarsPresenter extends Presenter<BarsView, Object> implements IBarsPresenter {
    private GeoDataClient mGeoDataClient;
    private Location location;
    private static final String TAG = BarsPresenter.class.getSimpleName();

    public BarsPresenter(Context context, BarsView view) {
        super(context, view, false);
    }

    @Override
    public void bindControls() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public void setGeoClient(GeoDataClient mGeoDataClient) {
        this.mGeoDataClient = mGeoDataClient;
    }
}

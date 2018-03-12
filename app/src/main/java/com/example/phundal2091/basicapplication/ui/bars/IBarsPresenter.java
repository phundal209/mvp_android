package com.example.phundal2091.basicapplication.ui.bars;

import android.location.Location;

import com.example.phundal2091.basicapplication.framework.IPresenter;
import com.google.android.gms.location.places.GeoDataClient;

public interface IBarsPresenter extends IPresenter<BarsView, Object> {
    void setLocation(Location location);

    void setGeoClient(GeoDataClient mGeoDataClient);
}

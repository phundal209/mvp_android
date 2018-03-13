package com.example.phundal2091.basicapplication.ui.bars;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.phundal2091.basicapplication.NearbyPlaceFinder;
import com.example.phundal2091.basicapplication.framework.Presenter;
import com.example.phundal2091.basicapplication.ui.PlaceType;
import com.example.phundal2091.basicapplication.ui.root.CityGuideAdapter;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;


import java.util.List;

public class CityItemPresenter extends Presenter<CityItemView, Object> implements ICityItemPresenter {
    private GeoDataClient mGeoDataClient;
    private PlaceDetectionClient mPlaceDetectionClient;
    private static final String TAG = CityItemPresenter.class.getSimpleName();
    private CityGuideAdapter cityGuideAdapter;
    private RecyclerView recyclerView;
    private NearbyPlaceFinder nearbyPlaceFinder;
    private PlaceType type;

    public CityItemPresenter(Context context, CityItemView view) {
        super(context, view, false);
        this.mGeoDataClient = Places.getGeoDataClient(context, null);
        this.mPlaceDetectionClient = Places.getPlaceDetectionClient(context, null);
        this.nearbyPlaceFinder = new NearbyPlaceFinder();
    }

    @Override
    public void bindControls() {
        nearbyPlaceFinder.getLikelyPlaces(context, mPlaceDetectionClient, new NearbyPlaceFinder.IOnListOfPlacesRetrieved() {
            @Override
            public void showPlaces(List<Place> places) {
                bindToAdapter(places);
            }
        });
    }
    private void bindToAdapter(List<Place> places) {
        cityGuideAdapter = new CityGuideAdapter(places, context);
        cityGuideAdapter.setTypeOfItem(type);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(cityGuideAdapter);
    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

    }

    @Override
    public void bindAdapter(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override
    public void setType(PlaceType type) {
        this.type = type;
    }
}

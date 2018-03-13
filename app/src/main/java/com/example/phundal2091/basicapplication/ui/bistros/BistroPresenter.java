package com.example.phundal2091.basicapplication.ui.bistros;


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

public class BistroPresenter extends Presenter<BistroView, Object> implements IBistroPresenter {

    private RecyclerView recyclerView;
    private CityGuideAdapter cityGuideAdapter;
    private GeoDataClient mGeoDataClient;
    private PlaceDetectionClient mPlaceDetectionClient;
    private NearbyPlaceFinder nearbyPlaceFinder;
    private static final String TAG = BistroPresenter.class.getSimpleName();

    public BistroPresenter(Context context, BistroView view) {
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

    private boolean isPlaceABistro(Place place) {
        final List<Integer> placeTypes = place.getPlaceTypes();
        for (Integer placeType : placeTypes) {
            if (placeType == Place.TYPE_FOOD) {
                return true;
            }
        }
        return false;
    }

    private void bindToAdapter(List<Place> places) {
        if (cityGuideAdapter != null) {
            cityGuideAdapter.addAll(places);
        } else {
            cityGuideAdapter = new CityGuideAdapter(places, context);
            cityGuideAdapter.setTypeOfItem(PlaceType.BISTRO);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(cityGuideAdapter);
        }
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
}

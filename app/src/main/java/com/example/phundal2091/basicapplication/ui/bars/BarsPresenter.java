package com.example.phundal2091.basicapplication.ui.bars;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.phundal2091.basicapplication.framework.Presenter;
import com.example.services.IApiService;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


import java.util.ArrayList;
import java.util.List;

import static com.example.phundal2091.basicapplication.wrapper.LocationClient.LOCATION_PERMISSION_TAG;

public class BarsPresenter extends Presenter<BarsView, Object> implements IBarsPresenter {
    private GeoDataClient mGeoDataClient;
    private PlaceDetectionClient mPlaceDetectionClient;
    private static final String TAG = BarsPresenter.class.getSimpleName();
    private IApiService apiService;
    private BarRecyclerAdapter barRecyclerAdapter;
    private RecyclerView recyclerView;

    public BarsPresenter(Context context, BarsView view, IApiService apiService) {
        super(context, view, false);
        this.apiService = apiService;
        this.mGeoDataClient = Places.getGeoDataClient(context, null);
        this.mPlaceDetectionClient = Places.getPlaceDetectionClient(context, null);
    }

    @Override
    public void bindControls() {
        getLikelyPlaces();
    }

    private void bindToAdapter(List<Place> places) {
        if (barRecyclerAdapter != null) {
            barRecyclerAdapter.addAll(places);
        } else {
            barRecyclerAdapter = new BarRecyclerAdapter(places, context);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(barRecyclerAdapter);
        }
    }

    private boolean isPlaceABar(Place place) {
        final List<Integer> placeTypes = place.getPlaceTypes();
        for (Integer placeType : placeTypes) {
            if (placeType == Place.TYPE_BAR) {
                return true;
            }
        }
        return false;
    }

    private void getLikelyPlaces() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_TAG);
            getLikelyPlaces();
        }
        Task<PlaceLikelihoodBufferResponse> placeResult = mPlaceDetectionClient.getCurrentPlace(null);
        final List<Place> listOfLikelyPlaces = new ArrayList<>();
        placeResult.addOnCompleteListener(new OnCompleteListener<PlaceLikelihoodBufferResponse>() {
            @Override
            public void onComplete(@NonNull Task<PlaceLikelihoodBufferResponse> task) {
                PlaceLikelihoodBufferResponse likelyPlaces = task.getResult();
                for (PlaceLikelihood placeLikelihood : likelyPlaces) {
                    Log.i(TAG, String.format("Place '%s' has rating: %g",
                            placeLikelihood.getPlace().getName(),
                            placeLikelihood.getPlace().getRating()));
                    if (isPlaceABar(placeLikelihood.getPlace())) {
                        listOfLikelyPlaces.add(placeLikelihood.getPlace());
                    }
                }
                bindToAdapter(listOfLikelyPlaces);
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
    public void bindAdapter(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }
}

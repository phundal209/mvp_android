package com.example.phundal2091.basicapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import static com.example.phundal2091.basicapplication.wrapper.LocationClient.LOCATION_PERMISSION_TAG;

public class NearbyPlaceFinder {
    private static final String TAG = NearbyPlaceFinder.class.getSimpleName();

    public void getLikelyPlaces(Context context, PlaceDetectionClient mPlaceDetectionClient, final IOnListOfPlacesRetrieved onListOfPlacesRetrieved) {
        final List<Place> listOfLikelyPlaces = new ArrayList<>();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_TAG);
            getLikelyPlaces(context, mPlaceDetectionClient, onListOfPlacesRetrieved);
        }
        Task<PlaceLikelihoodBufferResponse> placeResult = mPlaceDetectionClient.getCurrentPlace(null);
        placeResult.addOnCompleteListener(new OnCompleteListener<PlaceLikelihoodBufferResponse>() {
            @Override
            public void onComplete(@NonNull Task<PlaceLikelihoodBufferResponse> task) {
                PlaceLikelihoodBufferResponse likelyPlaces = task.getResult();
                for (PlaceLikelihood placeLikelihood : likelyPlaces) {
                    Log.i(TAG, String.format("Place '%s' has rating: %g",
                            placeLikelihood.getPlace().getName(),
                            placeLikelihood.getPlace().getRating()));
                    Place frozenPlace = placeLikelihood.getPlace().freeze();
                    listOfLikelyPlaces.add(frozenPlace);
                }
                onListOfPlacesRetrieved.showPlaces(listOfLikelyPlaces);
                likelyPlaces.release();
            }
        });
        placeResult.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i(TAG, "failure due to : " + e.getMessage());
            }
        });
    }

    public interface IOnListOfPlacesRetrieved {
        void showPlaces(List<Place> places);
    }
}

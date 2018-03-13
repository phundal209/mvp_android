package com.example.services;

import io.reactivex.Observable;

/**
 * Created by phundal2091 on 1/18/18.
 */

public interface IApiService {
    Observable<Object> getNearbyPlaces(String address, String latlng);
}

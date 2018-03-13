package com.example.services;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by phundal2091 on 1/18/18.
 */

public interface IRestClient {
    @GET("json")
    Observable<Object> getNearbyPlaces(@Query("address") String address, @Query("key") String key, @Query("latlng") String latlng);
}

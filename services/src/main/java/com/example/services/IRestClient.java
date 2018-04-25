package com.example.services;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by phundal2091 on 1/18/18.
 */

public interface IRestClient {
    @GET("ios/thumbnail/1522954000.jpg")
    Observable<Object> getSomething();

    @POST("ios/search")
    Observable<Object> motionSearch(@Body Object motionBody);
}

package com.example.services;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by phundal2091 on 1/18/18.
 */

public interface IRestClient {
    @GET("some/path")
    Observable<Object> getSomething();
}

package com.example.services;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by phundal on 12/1/17.
 */

public class ApiService implements IApiService{
    private IRestClient restClient;

    public ApiService(IRetrofitProvider retrofitProvider) {
        this.restClient = retrofitProvider.getRetrofit().create(IRestClient.class);
    }


    @Override
    public Observable<Object> getNearbyPlaces(String address, String latlng) {
        return restClient.getNearbyPlaces(address, "AIzaSyBqkoq0VRc_4mcdaQ5U0LCgF9q8yNe49w0", latlng).map(new Function<Object, Object>() {
            @Override
            public Object apply(Object o) throws Exception {
                return null;
            }
        });
    }
}
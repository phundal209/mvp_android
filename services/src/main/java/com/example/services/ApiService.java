package com.example.services;

import com.example.api.PhotosResponse;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by phundal on 12/1/17.
 */

public class ApiService implements IApiService{
    private IRestClient restClient;
    private String method = "flickr.photos.search";
    private String api_key = "3e7cc266ae2b0e0d78e279ce8e361736";
    private String format = "json";
    private int jsoncallback = 1;

    public ApiService(IRetrofitProvider retrofitProvider) {
        this.restClient = retrofitProvider.getRetrofit().create(IRestClient.class);
    }


    @Override
    public Observable<PhotosResponse> getFlickrPhotos(String query, int page) {
        return restClient.getPhotos(method, api_key, format, jsoncallback, query, page).map(new Function<PhotosResponse, PhotosResponse>() {
            @Override
            public PhotosResponse apply(PhotosResponse photosResponse) throws Exception {
                return photosResponse;
            }
        });
    }
}
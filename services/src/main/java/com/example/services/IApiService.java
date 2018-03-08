package com.example.services;

import com.example.api.PhotosResponse;

import io.reactivex.Observable;

/**
 * Created by phundal2091 on 1/18/18.
 */

public interface IApiService {
    Observable<PhotosResponse> getFlickrPhotos(String query, int page);
}

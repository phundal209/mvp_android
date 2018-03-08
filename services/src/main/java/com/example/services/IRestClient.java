package com.example.services;

import com.example.api.PhotosResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by phundal2091 on 1/18/18.
 */

public interface IRestClient {
    @GET("services/rest/")
    Observable<PhotosResponse> getPhotos(@Query("method") String method, @Query("api_key")
            String api_key, @Query("format") String format, @Query("nojsoncallback") int nojsoncallback,
                                         @Query("text") String text, @Query("page") int page);
}

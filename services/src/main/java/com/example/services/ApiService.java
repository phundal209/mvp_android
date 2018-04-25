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
    public Observable<Object> getSomeData() {
        return restClient.getSomething().map(new Function<Object, Object>() {
            @Override
            public Object apply(Object o) throws Exception {
                return null;
            }
        });
    }

    /**
     * Post request takes in the body of the post and returns a results object
     * @param motionSearchObject
     * @return
     */
    public Observable<Object> motionSearchAsync(Object motionSearchObject) {
        return restClient.motionSearch(motionSearchObject)
                .map(new Function<Object, Object>() {
                    @Override
                    public Object apply(Object o) throws Exception {
                        return null;
                    }
                });
    }
}
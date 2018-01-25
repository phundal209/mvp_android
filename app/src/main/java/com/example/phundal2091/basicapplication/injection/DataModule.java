package com.example.phundal2091.basicapplication.injection;

import android.app.Activity;

import com.example.phundal2091.basicapplication.injection.annotation.PerActivity;
import com.example.services.ApiService;
import com.example.services.IApiService;
import com.example.services.IRetrofitProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {
    private final Activity activity;

    public DataModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    IApiService providesApiService(IRetrofitProvider retrofitProvider) {
        return new ApiService(retrofitProvider);
    }
}
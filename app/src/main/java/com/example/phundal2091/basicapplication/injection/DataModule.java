package com.example.phundal2091.basicapplication.injection;

import android.app.Activity;
import android.content.SharedPreferences;

import com.example.phundal2091.basicapplication.injection.annotation.PerActivity;
import com.example.services.ApiService;
import com.example.services.IApiService;
import com.example.services.IRetrofitProvider;
import com.example.services.RetrofitProvider;

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
    IRetrofitProvider providesRetrofitProvider() {
        return new RetrofitProvider();
    }

    @PerActivity
    @Provides
    IApiService provideGoFundmeApiService(IRetrofitProvider provider) {
        return new ApiService(provider);
    }

}
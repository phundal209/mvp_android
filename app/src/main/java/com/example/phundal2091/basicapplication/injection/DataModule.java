package com.example.phundal2091.basicapplication.injection;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {
    private final Activity activity;

    public DataModule(Activity activity) {
        this.activity = activity;
    }

}
package com.example.phundal2091.basicapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.example.phundal2091.basicapplication.injection.AppModule;
import com.example.phundal2091.basicapplication.injection.ApplicationComponent;
import com.example.phundal2091.basicapplication.injection.DaggerApplicationComponent;

import javax.inject.Inject;

/**
 * Created by phundal on 12/1/17.
 */

public class BasicApplication extends MultiDexApplication {

    private ApplicationComponent applicationComponent;

    @Inject
    SharedPreferences sharedPreferences;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .appModule(new AppModule(this))
                .build();
        applicationComponent.inject(this);
    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }
}
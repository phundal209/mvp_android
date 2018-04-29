package com.example.phundal2091.basicapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.widget.Toast;

import com.example.phundal2091.basicapplication.injection.AppModule;
import com.example.phundal2091.basicapplication.injection.ApplicationComponent;
import com.example.phundal2091.basicapplication.injection.DaggerApplicationComponent;
import com.example.services.IRetrofitProvider;

import java.util.Date;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by phundal on 12/1/17.
 */

public class BasicApplication extends MultiDexApplication {

    private ApplicationComponent applicationComponent;

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    IRetrofitProvider retrofitProvider;

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

        Realm.init(this);
    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }
}
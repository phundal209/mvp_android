package com.example.phundal2091.basicapplication.injection;

import android.app.Activity;

import com.example.data.RealmRepository;
import com.example.phundal2091.basicapplication.injection.annotation.PerActivity;
import com.example.services.ApiService;
import com.example.services.IApiService;
import com.example.services.IRetrofitProvider;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

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

    @Provides
    @PerActivity
    RealmRepository provideRealmRepository(Realm realm) {
        return new RealmRepository(realm);
    }

    @Provides
    @PerActivity
    Realm providesRealm() {
        return Realm.getDefaultInstance();
    }
}
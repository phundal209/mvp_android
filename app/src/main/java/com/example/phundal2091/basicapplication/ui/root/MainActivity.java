package com.example.phundal2091.basicapplication.ui.root;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.phundal2091.basicapplication.BasicApplication;
import com.example.phundal2091.basicapplication.R;
import com.example.phundal2091.basicapplication.injection.ActivityModule;
import com.example.phundal2091.basicapplication.injection.DaggerMainActivityComponent;
import com.example.phundal2091.basicapplication.injection.DataModule;
import com.example.phundal2091.basicapplication.injection.MainActivityComponent;
import com.example.phundal2091.basicapplication.wrapper.LocationClient;
import com.google.android.gms.location.places.GeoDataClient;

import javax.inject.Inject;

public class MainActivity extends FragmentActivity {

    private MainActivityComponent component;
    @Inject
    IContentViewPresenter contentViewPresenter;
    private LocationClient locationClient;
    private CityGuidePagerAdapter cityGuidePagerAdapter;

    public MainActivityComponent component() {
        if (component == null) {
            component = DaggerMainActivityComponent.builder()
                    .applicationComponent((getBasicApplication()).getComponent())
                    .activityModule(new ActivityModule(this))
                    .build();
        }
        return component;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        component().inject(this);
        this.locationClient = new LocationClient(this);

        contentViewPresenter
                .getView()
                .withRootView(this.findViewById(android.R.id.content).getRootView());
        bindViewPager();
        contentViewPresenter.bindControls();
    }

    private void bindViewPager() {
        cityGuidePagerAdapter = new CityGuidePagerAdapter(getSupportFragmentManager());
        final ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(cityGuidePagerAdapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        locationClient.getLastKnownLocation(new LocationClient.IOnLocationRetrieved() {
            @Override
            public void onRetrieved(Location location) {
                if (cityGuidePagerAdapter != null) cityGuidePagerAdapter.setLocation(location);
            }
        });
    }


    private BasicApplication getBasicApplication() {
        return (BasicApplication) getApplication();
    }
}

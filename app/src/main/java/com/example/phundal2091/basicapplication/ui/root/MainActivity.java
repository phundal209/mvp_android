package com.example.phundal2091.basicapplication.ui.root;

import android.location.Location;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.phundal2091.basicapplication.BasicApplication;
import com.example.phundal2091.basicapplication.R;
import com.example.phundal2091.basicapplication.injection.ActivityModule;
import com.example.phundal2091.basicapplication.injection.DaggerMainActivityComponent;
import com.example.phundal2091.basicapplication.injection.DataModule;
import com.example.phundal2091.basicapplication.injection.MainActivityComponent;
import com.example.phundal2091.basicapplication.prefs.PrefManager;
import com.example.phundal2091.basicapplication.wrapper.LocationClient;
import com.google.android.gms.location.places.GeoDataClient;

import javax.inject.Inject;

import static com.example.phundal2091.basicapplication.prefs.PrefManager.LOCATION_KEY;
import static com.example.phundal2091.basicapplication.wrapper.LocationClient.LOCATION_PERMISSION_TAG;

public class MainActivity extends FragmentActivity {

    private MainActivityComponent component;
    @Inject
    IContentViewPresenter contentViewPresenter;

    public MainActivityComponent component() {
        if (component == null) {
            component = DaggerMainActivityComponent.builder()
                    .applicationComponent((getBasicApplication()).getComponent())
                    .activityModule(new ActivityModule(this))
                    .dataModule(new DataModule(this))
                    .build();
        }
        return component;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        toolbar.setTitleTextColor(getColor(R.color.white));
        component().inject(this);
        contentViewPresenter
                .getView()
                .withRootView(this.findViewById(android.R.id.content).getRootView());
        bindViewPager();
        contentViewPresenter.bindControls();
    }

    private void bindViewPager() {
        CityGuidePagerAdapter cityGuidePagerAdapter = new CityGuidePagerAdapter(getSupportFragmentManager());
        final ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(cityGuidePagerAdapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    private BasicApplication getBasicApplication() {
        return (BasicApplication) getApplication();
    }
}

package com.example.phundal2091.basicapplication.ui.root;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phundal2091.basicapplication.BasicApplication;
import com.example.phundal2091.basicapplication.NearbyPlaceFinder;
import com.example.phundal2091.basicapplication.R;
import com.example.phundal2091.basicapplication.injection.ActivityModule;
import com.example.phundal2091.basicapplication.injection.DaggerMainActivityComponent;
import com.example.phundal2091.basicapplication.injection.DataModule;
import com.example.phundal2091.basicapplication.injection.MainActivityComponent;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;

import java.util.List;

import javax.inject.Inject;

import static android.widget.Toast.LENGTH_LONG;
import static com.example.phundal2091.basicapplication.wrapper.LocationClient.LOCATION_PERMISSION_TAG;

public class MainActivity extends FragmentActivity {

    private MainActivityComponent component;
    @Inject
    IContentViewPresenter contentViewPresenter;
    CityGuidePagerAdapter cityGuidePagerAdapter;
    private PlaceDetectionClient mPlaceDetectionClient;

    private TextView barPager, bistroPager, cafePager;
    private ViewPager viewPager;

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

        setupViews();
        component().inject(this);
        contentViewPresenter
                .getView()
                .withRootView(this.findViewById(android.R.id.content).getRootView());
        bindViewPager();
        contentViewPresenter.bindControls();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setupViews() {
        barPager = findViewById(R.id.bar_pager);
        bistroPager = findViewById(R.id.bistro_pager);
        cafePager = findViewById(R.id.cafe_pager);
        viewPager = findViewById(R.id.viewPager);
        this.mPlaceDetectionClient = Places.getPlaceDetectionClient(this, null);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        toolbar.setTitleTextColor(getColor(R.color.white));
    }

    private void bindViewPager() {
        cityGuidePagerAdapter = new CityGuidePagerAdapter(getSupportFragmentManager());
        final LockableViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setSwipeable(false);
        viewPager.setAdapter(cityGuidePagerAdapter);
        viewPager.setCurrentItem(0);
        contentViewPresenter.changePager(barPager, viewPager, 0);
        contentViewPresenter.changePager(bistroPager, viewPager, 1);
        contentViewPresenter.changePager(cafePager, viewPager, 2);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // It is possible that the permissions request interaction with the user is interrupted.
        // In this case you will receive empty permissions and results arrays which should be treated as a cancellation.
        if (permissions.length == 0 || grantResults.length == 0) {
            return;
        }
        String permission = permissions[0];
        switch (requestCode) {
            case LOCATION_PERMISSION_TAG:
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                NearbyPlaceFinder nearbyPlaceFinder = new NearbyPlaceFinder();
                nearbyPlaceFinder.getLikelyPlaces(this, mPlaceDetectionClient, new NearbyPlaceFinder.IOnListOfPlacesRetrieved() {
                    @Override
                    public void showPlaces(List<Place> places) {

                    }
                });
            } else {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                    Toast.makeText(this, getString(R.string.accept_permissions_message), LENGTH_LONG).show();
                }
            }
        }
    }


    private BasicApplication getBasicApplication() {
        return (BasicApplication) getApplication();
    }
}

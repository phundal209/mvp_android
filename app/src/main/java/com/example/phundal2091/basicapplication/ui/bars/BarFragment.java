package com.example.phundal2091.basicapplication.ui.bars;

import android.content.Context;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phundal2091.basicapplication.R;
import com.example.phundal2091.basicapplication.location_helper.LocationHelper;
import com.example.phundal2091.basicapplication.prefs.PrefManager;
import com.example.phundal2091.basicapplication.ui.root.MainActivity;
import com.example.phundal2091.basicapplication.wrapper.LocationClient;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Places;

import javax.inject.Inject;

import static com.example.phundal2091.basicapplication.prefs.PrefManager.LOCATION_KEY;

public class BarFragment extends Fragment {

    @Inject
    IBarsPresenter barsPresenter;
    protected GeoDataClient mGeoDataClient;
    private Location location;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View inflate = inflater.inflate(R.layout.fragment_bar, container, false);

        MainActivity activity = (MainActivity) getActivity();
        activity.component().inject(this);
        barsPresenter.getView().withRootView(inflate);
        mGeoDataClient = Places.getGeoDataClient(getActivity(), null);
        barsPresenter.setLocation(location);
        barsPresenter.bindControls();
        barsPresenter.setGeoClient(mGeoDataClient);
        return inflate;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}

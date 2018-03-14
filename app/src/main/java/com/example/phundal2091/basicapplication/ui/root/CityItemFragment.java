package com.example.phundal2091.basicapplication.ui.root;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phundal2091.basicapplication.wrapper.NearbyPlaceFinder;
import com.example.phundal2091.basicapplication.R;
import com.example.phundal2091.basicapplication.ui.PlaceType;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;

import java.util.List;

public class CityItemFragment extends Fragment {

    private RecyclerView recyclerView;
    PlaceDetectionClient mPlaceDetectionClient;
    private NearbyPlaceFinder nearbyPlaceFinder;
    private PlaceType type;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View inflate = inflater.inflate(R.layout.fragment_bar, container, false);
        this.mPlaceDetectionClient = Places.getPlaceDetectionClient(getActivity(), null);
        recyclerView = inflate.findViewById(R.id.recyclerView);
        swipeRefreshLayout = inflate.findViewById(R.id.swipeRefreshLayout);
        getNearbyPlaces();
        bindSwipeRefresh();
        return inflate;
    }

    public void setType(PlaceType type) {
        this.type = type;
    }

    private void getNearbyPlaces() {
        nearbyPlaceFinder = new NearbyPlaceFinder();
        nearbyPlaceFinder.getLikelyPlaces(getContext(), mPlaceDetectionClient, new NearbyPlaceFinder.IOnListOfPlacesRetrieved() {
            @Override
            public void showPlaces(List<Place> places) {
                bindFromPermissions(places);
            }
        });
    }

    public void bindFromPermissions(List<Place> places) {
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            CityGuideAdapter cityGuideAdapter = new CityGuideAdapter(places, getContext());
            cityGuideAdapter.setHasStableIds(true);
            cityGuideAdapter.setTypeOfItem(type);
            recyclerView.setAdapter(cityGuideAdapter);
        }
    }

    private void bindSwipeRefresh() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    getNearbyPlaces();
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
        }
    }

    private boolean _hasLoadedOnce= false;

    @Override
    public void setUserVisibleHint(boolean isFragmentVisible_) {
        super.setUserVisibleHint(true);
        if (this.isVisible()) {
            // we check that the fragment is becoming visible
            if (isFragmentVisible_ && !_hasLoadedOnce) {
                this.nearbyPlaceFinder = new NearbyPlaceFinder();
                getNearbyPlaces();
                _hasLoadedOnce = true;
            }
        }
    }
}

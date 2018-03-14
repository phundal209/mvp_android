package com.example.phundal2091.basicapplication;

import android.content.Context;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.phundal2091.basicapplication.ui.PlaceType;
import com.example.phundal2091.basicapplication.ui.root.CityGuideAdapter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
public class CityGuideAdapterTest {
    // Context of the app under test.
    Context appContext = InstrumentationRegistry.getTargetContext();
    List<Place> placeList = new ArrayList<>();
    private static final int BAR_TYPE = 9;
    private static final int CAFE_TYPE = 15;
    private static final int BISTRO_TYPE = 38;

    private int getRating() {
        Random rand = new Random();
        return rand.nextInt(5) + 1;
    }

    public List<Integer> getListOfPlaces(PlaceType placeType) {
        List<Integer> getPlaceTypeList = new ArrayList<>();
        switch (placeType) {
            case BAR:
                getPlaceTypeList.add(BAR_TYPE);
                break;
            case BISTRO:
                getPlaceTypeList.add(BISTRO_TYPE);
                break;
            case CAFE:
                getPlaceTypeList.add(CAFE_TYPE);
        }
        return getPlaceTypeList;
    }

    private void createListOfPlaces(final PlaceType placeType) {
        Place place = new Place() {
            @Override
            public String getId() {
                return null;
            }

            @Override
            public List<Integer> getPlaceTypes() {
                return getListOfPlaces(placeType);
            }

            @Override
            public CharSequence getAddress() {
                return null;
            }

            @Override
            public Locale getLocale() {
                return null;
            }

            @Override
            public CharSequence getName() {
                return null;
            }

            @Override
            public LatLng getLatLng() {
                return null;
            }

            @Override
            public LatLngBounds getViewport() {
                return null;
            }

            @Override
            public Uri getWebsiteUri() {
                return null;
            }

            @Override
            public CharSequence getPhoneNumber() {
                return null;
            }

            @Override
            public float getRating() {
                return getRating();
            }

            @Override
            public int getPriceLevel() {
                return 0;
            }

            @Override
            public CharSequence getAttributions() {
                return null;
            }

            @Override
            public Place freeze() {
                return null;
            }

            @Override
            public boolean isDataValid() {
                return false;
            }
        };
        placeList.add(place);
    }

    @Test
    private void testPlaceTypeForBars() {
        CityGuideAdapter cityGuideAdapter = new CityGuideAdapter(placeList, appContext);
        cityGuideAdapter.setTypeOfItem(PlaceType.BAR);
        assertEquals(cityGuideAdapter.getPlaceType(), PlaceType.BAR);
    }

    @Test
    private void testPlaceTypeForBistros() {
        CityGuideAdapter cityGuideAdapter = new CityGuideAdapter(placeList, appContext);
        cityGuideAdapter.setTypeOfItem(PlaceType.BISTRO);
        assertEquals(cityGuideAdapter.getPlaceType(), PlaceType.BISTRO);
    }

    @Test
    private void testPlaceTypeForCafes() {
        CityGuideAdapter cityGuideAdapter = new CityGuideAdapter(placeList, appContext);
        cityGuideAdapter.setTypeOfItem(PlaceType.CAFE);
        assertEquals(cityGuideAdapter.getPlaceType(), PlaceType.CAFE);
    }

    @Test
    private void testAdapterListsMatch() {
        createListOfPlaces(PlaceType.BAR);
        CityGuideAdapter cityGuideAdapter = new CityGuideAdapter(placeList, appContext);
        assertEquals(cityGuideAdapter.getPlaces(), placeList);
    }

    @Test
    private void testWhatHappensIfAdapterHasNulls() {
        CityGuideAdapter cityGuideAdapter = new CityGuideAdapter(null, appContext);
        assertEquals(cityGuideAdapter.getItemCount(), 0);
    }


}

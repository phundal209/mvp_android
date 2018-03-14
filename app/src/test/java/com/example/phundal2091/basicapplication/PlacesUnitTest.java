package com.example.phundal2091.basicapplication;
import android.net.Uri;

import com.example.phundal2091.basicapplication.ui.PlaceType;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.*;

public class PlacesUnitTest {

    private NearbyPlaceFinder nearbyPlaceFinder = new NearbyPlaceFinder();
    private static final int BAR_TYPE = 9;
    private static final int CAFE_TYPE = 15;
    private static final int BISTRO_TYPE = 38;
    final List<Integer> placeTypes = new ArrayList<>();

    @Test
    public void testPlaceTypeLength() {
        assertTrue(PlaceType.values().length == 3);
    }

    Place place = new Place() {
        @Override
        public String getId() {
            return null;
        }

        @Override
        public List<Integer> getPlaceTypes() {
            return placeTypes;
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
            return 0;
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

    @Test
    public void testNullCafe() {
        assertFalse(nearbyPlaceFinder.isPlaceACafe(null));
    }

    @Test
    public void testNullBar() {
        assertFalse(nearbyPlaceFinder.isPlaceABar(null));
    }

    @Test
    public void testNullBistro() {
        assertFalse(nearbyPlaceFinder.isPlaceABistro(null));
    }

    @Test
    public void testPlaceIsABar() {
        placeTypes.add(BAR_TYPE);
        assertTrue(nearbyPlaceFinder.isPlaceABar(place));
    }

    @Test
    public void testPlaceIsABistro() {
        placeTypes.add(BISTRO_TYPE);
        assertTrue(nearbyPlaceFinder.isPlaceABistro(place));
    }

    @Test
    public void testPlaceIsACafe() {
        placeTypes.add(CAFE_TYPE);
        assertTrue(nearbyPlaceFinder.isPlaceACafe(place));
    }

    @Test
    public void testPlaceIsNotABar() {
        placeTypes.add(CAFE_TYPE);
        placeTypes.add(BISTRO_TYPE);
        assertFalse(nearbyPlaceFinder.isPlaceABar(place));
    }

    @Test
    public void testPlaceIsNotACafe() {
        placeTypes.add(BAR_TYPE);
        placeTypes.add(BISTRO_TYPE);
        assertFalse(nearbyPlaceFinder.isPlaceACafe(place));
    }

    @Test
    public void testPlaceIsNotABistro() {
        placeTypes.add(BAR_TYPE);
        placeTypes.add(CAFE_TYPE);
        assertFalse(nearbyPlaceFinder.isPlaceABistro(place));
    }
}

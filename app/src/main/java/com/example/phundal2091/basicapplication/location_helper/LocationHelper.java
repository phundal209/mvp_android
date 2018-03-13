package com.example.phundal2091.basicapplication.location_helper;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by phundal on 3/12/18.
 */

public class LocationHelper {
    Geocoder geocoder;

    public LocationHelper(Context context) {
        this.geocoder =  new Geocoder(context, Locale.getDefault());
    }

    public String getAddress(Location location) {
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (addresses != null) {
            String address = addresses.get(0).getAddressLine(0);
            return address;
        }
        return "";
    }
}

package com.example.phundal2091.basicapplication.ui.root;

import android.location.Location;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.phundal2091.basicapplication.ui.bars.BarFragment;
import com.example.phundal2091.basicapplication.ui.bistros.BistroFragment;
import com.example.phundal2091.basicapplication.ui.cafes.CafeFragment;

/**
 * Created by phundal on 3/12/18.
 */

public class CityGuidePagerAdapter extends FragmentPagerAdapter {
    private static int PAGE_COUNT = 3;
    private Location location;


    public CityGuidePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                BarFragment barFragment = new BarFragment();
                barFragment.setLocation(location);
                return barFragment;
            case 1:
                BistroFragment bistroFragment = new BistroFragment();
                return bistroFragment;
            case 2:
                CafeFragment cafeFragment = new CafeFragment();
                return cafeFragment;
            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}

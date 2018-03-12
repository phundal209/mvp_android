package com.example.phundal2091.basicapplication.ui.root;

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


    public CityGuidePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new BarFragment();
            case 1:
                return new BistroFragment();
            case 2:
                return new CafeFragment();
            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }
}

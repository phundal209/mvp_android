package com.example.phundal2091.basicapplication.ui.root;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.phundal2091.basicapplication.ui.PlaceType;

/**
 * Created by phundal on 3/12/18.
 */

public class CityGuidePagerAdapter extends FragmentPagerAdapter {
    private static int PAGE_COUNT = 3;
    CityItemFragment barFragment;
    CityItemFragment bistroFragment;
    CityItemFragment cafeFragment;
    public CityGuidePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                barFragment = new CityItemFragment();
                barFragment.setType(PlaceType.BAR);
                return barFragment;
            case 1:
                bistroFragment = new CityItemFragment();
                bistroFragment.setType(PlaceType.BISTRO);
                return bistroFragment;
            case 2:
                cafeFragment = new CityItemFragment();
                cafeFragment.setType(PlaceType.CAFE);
                return cafeFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

}

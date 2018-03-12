package com.example.phundal2091.basicapplication.ui.root;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.phundal2091.basicapplication.R;
import com.example.phundal2091.basicapplication.framework.ViewScreen;

import butterknife.BindView;

/**
 * Created by phundal2091 on 1/18/18.
 */

public class ContentView extends ViewScreen {
    public ContentView() {
        super();
    }

    @BindView(R.id.viewPager)
    ViewPager viewPager;
}

package com.example.phundal2091.basicapplication.ui.root;

import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.example.phundal2091.basicapplication.framework.IPresenter;

/**
 * Created by phundal2091 on 1/18/18.
 */

public interface IContentViewPresenter extends IPresenter<ContentView, Object>{
    void changePager(TextView pagerText, ViewPager viewPager, int pos);
}

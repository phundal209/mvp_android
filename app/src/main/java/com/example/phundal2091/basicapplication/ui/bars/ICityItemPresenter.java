package com.example.phundal2091.basicapplication.ui.bars;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.phundal2091.basicapplication.framework.IPresenter;
import com.example.phundal2091.basicapplication.ui.PlaceType;

public interface ICityItemPresenter extends IPresenter<CityItemView, Object> {
    void bindAdapter(RecyclerView recyclerView);

    void setType(PlaceType type);
}

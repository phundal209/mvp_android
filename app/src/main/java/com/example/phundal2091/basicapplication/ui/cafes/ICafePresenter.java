package com.example.phundal2091.basicapplication.ui.cafes;

import android.support.v7.widget.RecyclerView;

import com.example.phundal2091.basicapplication.framework.IPresenter;

public interface ICafePresenter extends IPresenter<CafeView, Object> {
    void bindAdapter(RecyclerView recyclerView);
}

package com.example.phundal2091.basicapplication.ui.bistros;


import android.support.v7.widget.RecyclerView;

import com.example.phundal2091.basicapplication.framework.IPresenter;

public interface IBistroPresenter extends IPresenter<BistroView, Object> {
    void bindAdapter(RecyclerView recyclerView);
}

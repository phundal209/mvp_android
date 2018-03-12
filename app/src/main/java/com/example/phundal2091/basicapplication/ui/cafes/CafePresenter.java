package com.example.phundal2091.basicapplication.ui.cafes;


import android.content.Context;

import com.example.phundal2091.basicapplication.framework.Presenter;
import com.example.phundal2091.basicapplication.ui.bars.BarsView;
import com.example.phundal2091.basicapplication.ui.bars.IBarsPresenter;

public class CafePresenter extends Presenter<CafeView, Object> implements ICafePresenter {

    public CafePresenter(Context context, CafeView view) {
        super(context, view, false);
    }

    @Override
    public void bindControls() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

    }
}

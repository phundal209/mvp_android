package com.example.phundal2091.basicapplication.ui.bistros;


import android.content.Context;

import com.example.phundal2091.basicapplication.framework.Presenter;
import com.example.phundal2091.basicapplication.ui.bars.BarsView;
import com.example.phundal2091.basicapplication.ui.bars.IBarsPresenter;

public class BistroPresenter extends Presenter<BistroView, Object> implements IBistroPresenter {

    public BistroPresenter(Context context, BistroView view) {
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

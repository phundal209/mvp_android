package com.example.phundal2091.basicapplication.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.example.phundal2091.basicapplication.R;
import com.example.phundal2091.basicapplication.framework.ViewScreen;

import butterknife.Bind;

/**
 * Created by phundal2091 on 1/18/18.
 */

public class ContentView extends ViewScreen {

    @Bind(R.id.motionImage)
    ImageView motionImage;

    @Bind(R.id.gridLayout)
    GridLayout gridLayout;

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    public ContentView(View rootView) {
        super(rootView);
    }
}

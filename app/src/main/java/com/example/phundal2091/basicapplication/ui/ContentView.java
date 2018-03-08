package com.example.phundal2091.basicapplication.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;

import com.example.phundal2091.basicapplication.R;
import com.example.phundal2091.basicapplication.framework.ViewScreen;

import butterknife.Bind;

/**
 * Created by phundal2091 on 1/18/18.
 */

public class ContentView extends ViewScreen {

    @Bind(R.id.recycler)
    public RecyclerView recyclerView;

    @Bind(R.id.searchView)
    public SearchView searchView;

    public ContentView(View rootView) {
        super(rootView);
    }
}

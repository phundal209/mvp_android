package com.example.phundal2091.basicapplication.framework;

/**
 * Created by phundal on 12/1/17.
 */

import android.view.View;

import butterknife.ButterKnife;

/**
 * View base class
 */
public abstract class ViewScreen {
    View rootView;

    /**
     * With this constructor you will need to manually attach the root view after inflating
     */
    public ViewScreen() {
    }

    public ViewScreen(View rootView) {
        this.rootView = rootView;
    }

    public <T extends ViewScreen> T withRootView(View rootView) {
        this.rootView = rootView;
        bindFromRoot(rootView);
        return (T) this;
    }

    public void bindFromRoot(View rootView) {
        ButterKnife.bind(this, rootView);
    }

    public View getRootView() {
        return rootView;
    }
}
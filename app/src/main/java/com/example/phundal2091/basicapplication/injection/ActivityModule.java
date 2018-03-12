package com.example.phundal2091.basicapplication.injection;

import android.app.Activity;

import com.example.phundal2091.basicapplication.injection.annotation.PerActivity;
import com.example.phundal2091.basicapplication.ui.root.ContentView;
import com.example.phundal2091.basicapplication.ui.root.ContentViewPresenter;
import com.example.phundal2091.basicapplication.ui.root.IContentViewPresenter;

import dagger.Module;
import dagger.Provides;


@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    /**
     * Expose the activity to dependents in the graph.
     */
    @Provides
    @PerActivity
    Activity activity() {
        return activity;
    }

    @Provides
    @PerActivity
    IContentViewPresenter loginPresenter() {
        return new ContentViewPresenter(activity, new ContentView(activity
                .findViewById(android.R.id.content)));
    }
}
package com.example.phundal2091.basicapplication.injection;

import android.app.Activity;

import com.example.phundal2091.basicapplication.injection.annotation.PerActivity;
import com.example.phundal2091.basicapplication.ui.ContentView;
import com.example.phundal2091.basicapplication.ui.ContentViewPresenter;
import com.example.phundal2091.basicapplication.ui.IContentViewPresenter;
import com.example.phundal2091.basicapplication.wrapper.IImageWrapper;
import com.example.phundal2091.basicapplication.wrapper.ImageWrapper;
import com.example.services.IApiService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by phundal on 12/1/17.
 */

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
    IImageWrapper providesImageWrapper() {
        return new ImageWrapper(activity);
    }

    @Provides
    @PerActivity
    IContentViewPresenter loginPresenter(IApiService apiService, IImageWrapper iImageWrapper) {
        return new ContentViewPresenter(activity, new ContentView(activity
                .findViewById(android.R.id.content)), apiService, iImageWrapper);
    }
}
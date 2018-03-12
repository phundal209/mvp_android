package com.example.phundal2091.basicapplication.injection;

import android.app.Activity;

import com.example.phundal2091.basicapplication.injection.annotation.PerActivity;
import com.example.phundal2091.basicapplication.ui.bars.BarsPresenter;
import com.example.phundal2091.basicapplication.ui.bars.BarsView;
import com.example.phundal2091.basicapplication.ui.bars.IBarsPresenter;
import com.example.phundal2091.basicapplication.ui.bistros.BistroPresenter;
import com.example.phundal2091.basicapplication.ui.bistros.BistroView;
import com.example.phundal2091.basicapplication.ui.bistros.IBistroPresenter;
import com.example.phundal2091.basicapplication.ui.cafes.CafePresenter;
import com.example.phundal2091.basicapplication.ui.cafes.CafeView;
import com.example.phundal2091.basicapplication.ui.cafes.ICafePresenter;
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
        return new ContentViewPresenter(activity, new ContentView());
    }

    @Provides
    @PerActivity
    IBarsPresenter provdesBarsPresenter() {
        return new BarsPresenter(activity, new BarsView());
    }

    @Provides
    @PerActivity
    IBistroPresenter provdesBistroPresenter() {
        return new BistroPresenter(activity, new BistroView());
    }

    @Provides
    @PerActivity
    ICafePresenter provdesCafePresenter() {
        return new CafePresenter(activity, new CafeView());
    }
}
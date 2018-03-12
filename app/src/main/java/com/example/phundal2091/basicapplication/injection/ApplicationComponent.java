package com.example.phundal2091.basicapplication.injection;


import android.app.Application;

import com.example.phundal2091.basicapplication.BasicApplication;
import com.example.phundal2091.basicapplication.ui.bars.BarFragment;
import com.example.phundal2091.basicapplication.ui.bistros.BistroFragment;
import com.example.phundal2091.basicapplication.ui.cafes.CafeFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by phundal on 12/1/17.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface ApplicationComponent {
    void inject(BasicApplication application);
    // Exported for child-components.
    Application application();
}
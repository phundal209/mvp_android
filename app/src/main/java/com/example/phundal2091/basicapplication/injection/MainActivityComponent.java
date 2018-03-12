package com.example.phundal2091.basicapplication.injection;


import com.example.phundal2091.basicapplication.ui.bars.BarFragment;
import com.example.phundal2091.basicapplication.ui.bistros.BistroFragment;
import com.example.phundal2091.basicapplication.ui.cafes.CafeFragment;
import com.example.phundal2091.basicapplication.ui.root.MainActivity;
import com.example.phundal2091.basicapplication.injection.annotation.PerActivity;

import dagger.Component;

/**
 * Created by phundal on 12/1/17.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, DataModule.class})
public interface MainActivityComponent extends AbstractActivityComponent {
    void inject(MainActivity mainActivity);
    void inject(BarFragment barFragment);
    void inject(BistroFragment bistroFragment);
    void inject(CafeFragment cafeFragment);
}
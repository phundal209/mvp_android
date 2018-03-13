package com.example.phundal2091.basicapplication.injection;


import com.example.phundal2091.basicapplication.ui.root.CityItemFragment;
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
    void inject(CityItemFragment cityItemFragment);
}
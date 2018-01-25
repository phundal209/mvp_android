package com.example.phundal2091.basicapplication.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.phundal2091.basicapplication.BasicApplication;
import com.example.phundal2091.basicapplication.R;
import com.example.phundal2091.basicapplication.injection.ActivityModule;
import com.example.phundal2091.basicapplication.injection.DaggerMainActivityComponent;
import com.example.phundal2091.basicapplication.injection.DataModule;
import com.example.phundal2091.basicapplication.injection.MainActivityComponent;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private MainActivityComponent component;
    @Inject
    IContentViewPresenter contentViewPresenter;

    MainActivityComponent component() {
        if (component == null) {
            component = DaggerMainActivityComponent.builder()
                    .applicationComponent((getBasicApplication()).getComponent())
                    .activityModule(new ActivityModule(this))
                    .dataModule(new DataModule(this))
                    .build();
        }
        return component;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        component().inject(this);

        contentViewPresenter
                .getView()
                .withRootView(this.findViewById(android.R.id.content).getRootView());
        contentViewPresenter.bindControls();
    }

    private BasicApplication getBasicApplication() {
        return (BasicApplication) getApplication();
    }
}

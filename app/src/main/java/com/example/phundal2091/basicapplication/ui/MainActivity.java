package com.example.phundal2091.basicapplication.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phundal2091.basicapplication.BasicApplication;
import com.example.phundal2091.basicapplication.BroadcastService;
import com.example.phundal2091.basicapplication.QuestionModel;
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
    private static final String TAG = MainActivity.class.getSimpleName();

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
        if (savedInstanceState != null
            && savedInstanceState.getSerializable("question") != null) {
            QuestionModel questionModel = (QuestionModel) savedInstanceState.getSerializable("question");
            contentViewPresenter.bindSelectedQuestion(questionModel);
        } else {
            contentViewPresenter.bindControls();
        }
        startService(new Intent(this, BroadcastService.class));
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putSerializable("question", contentViewPresenter.getSelectedQuestion());
        super.onSaveInstanceState(savedInstanceState);
    }


    private BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateGUI(intent);
        }
    };

    private void updateGUI(Intent intent) {
        if (intent != null
                && intent.hasExtra("countdown")) {
            long timeLeft = intent.getLongExtra("countdown", 0);
            long secondsLeft = timeLeft/1000;
            contentViewPresenter.updateTimer(secondsLeft);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(br, new IntentFilter(BroadcastService.COUNTDOWN_BR));
        Log.i(TAG, "Registered broacast receiver");
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(br);
        Log.i(TAG, "Unregistered broacast receiver");
    }

    private BasicApplication getBasicApplication() {
        return (BasicApplication) getApplication();
    }

}

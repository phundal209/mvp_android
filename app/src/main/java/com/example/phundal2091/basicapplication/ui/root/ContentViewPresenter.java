package com.example.phundal2091.basicapplication.ui.root;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phundal2091.basicapplication.R;
import com.example.phundal2091.basicapplication.framework.Presenter;
import com.example.phundal2091.basicapplication.wrapper.LocationClient;
import com.google.android.gms.location.places.GeoDataClient;

import static android.widget.Toast.LENGTH_LONG;


public class ContentViewPresenter extends Presenter<ContentView, Object> implements IContentViewPresenter {
    private ProgressDialog progressDialog;
    private static final String TAG = ContentViewPresenter.class.getSimpleName();

    public ContentViewPresenter(Context context, ContentView view) {
        super(context, view, false);
    }

    @Override
    public void bindControls() {

    }

    @Override
    public void changePager(TextView pagerText, final ViewPager viewPager, final int pos, final FrameLayout barLayout, final FrameLayout bistroLayout, final FrameLayout cafeLayout) {
        pagerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (pos) {
                    case 0:
                        barLayout.setVisibility(View.VISIBLE);
                        bistroLayout.setVisibility(View.INVISIBLE);
                        cafeLayout.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        barLayout.setVisibility(View.INVISIBLE);
                        bistroLayout.setVisibility(View.VISIBLE);
                        cafeLayout.setVisibility(View.INVISIBLE);
                        break;
                    case 2:
                        barLayout.setVisibility(View.INVISIBLE);
                        bistroLayout.setVisibility(View.INVISIBLE);
                        cafeLayout.setVisibility(View.VISIBLE);
                        break;
                }
                viewPager.setCurrentItem(pos);
            }
        });
    }


    @Override
    public void hide() {
        if(progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void show() {
        if(progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(context.getString(R.string.waiting_message));
            progressDialog.show();
        }
    }
}

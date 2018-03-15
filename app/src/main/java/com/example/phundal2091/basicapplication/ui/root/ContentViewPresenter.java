package com.example.phundal2091.basicapplication.ui.root;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.phundal2091.basicapplication.R;
import com.example.phundal2091.basicapplication.framework.Presenter;
import com.example.phundal2091.basicapplication.ui.PlaceType;


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
                switchToPage(pos, barLayout, bistroLayout, cafeLayout);
                viewPager.setCurrentItem(pos);
            }
        });
    }

    @Override
    public void handleOnSwipeGestureOfPager(ViewPager pager, final FrameLayout barLayout, final FrameLayout bistroLayout, final FrameLayout cafeLayout) {
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switchToPage(position, barLayout, bistroLayout, cafeLayout);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public PlaceType getPlaceType(ViewPager pager) {
        if (pager != null) {
            switch (pager.getCurrentItem()) {
                case 0:
                    return PlaceType.BAR;
                case 1:
                    return PlaceType.BISTRO;
                case 2:
                    return PlaceType.CAFE;
                default:
                    return PlaceType.BAR;
            }
        }
        return PlaceType.BAR;
    }

    private void switchToPage(int pos, FrameLayout barLayout, FrameLayout bistroLayout, FrameLayout cafeLayout) {
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

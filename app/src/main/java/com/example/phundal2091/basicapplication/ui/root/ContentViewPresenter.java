package com.example.phundal2091.basicapplication.ui.root;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.phundal2091.basicapplication.R;
import com.example.phundal2091.basicapplication.framework.Presenter;
import com.google.android.gms.location.places.GeoDataClient;


public class ContentViewPresenter extends Presenter<ContentView, Object> implements IContentViewPresenter {
    private ProgressDialog progressDialog;

    protected GeoDataClient mGeoDataClient;

    public ContentViewPresenter(Context context, ContentView view) {
        super(context, view, false);
    }

    @Override
    public void bindControls() {

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

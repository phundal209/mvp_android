package com.example.phundal2091.basicapplication.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.phundal2091.basicapplication.R;
import com.example.phundal2091.basicapplication.framework.Presenter;
import com.example.phundal2091.basicapplication.wrapper.IImageWrapper;
import com.example.services.IApiService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by phundal2091 on 1/18/18.
 */

public class ContentViewPresenter extends Presenter<ContentView, Object> implements IContentViewPresenter {

    private IApiService apiService;
    private ProgressDialog progressDialog;
    private IImageWrapper imageWrapper;
    private static final String url = "http://ec2-54-187-236-58.us-west-2.compute.amazonaws.com:8021/ios/thumbnail/1522954000.jpg";

    public ContentViewPresenter(Context context, ContentView view, IApiService apiService, IImageWrapper iImageWrapper) {
        super(context, view, false);
        this.apiService = apiService;
        this.imageWrapper = iImageWrapper;
    }

    @Override
    public void bindControls() {
        downloadAndBindImage();
        handleClickOnGridCell();
    }

    private void downloadAndBindImage() {
        imageWrapper.displayImage(url, view.motionImage);
    }

    private void handleClickOnGridCell() {
        int children = view.gridLayout.getChildCount();
        for (int i = 0; i < children; i++) {
            final int index = i;
            ImageView imageView = (ImageView) view.gridLayout.getChildAt(i);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // make API call here
                    Toast.makeText(context, "Touched cell number: " + index, Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private void bindRecyclerView(List<Object> results) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        view.recyclerView.setLayoutManager(linearLayoutManager);
        ContentViewAdapter contentViewAdapter = new ContentViewAdapter(results);
        view.recyclerView.setAdapter(contentViewAdapter);
    }

    private void makeApiCall() {
        apiService.getSomeData().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .onErrorReturn(new Function<Throwable, Object>() {
                    @Override
                    public Object apply(Throwable throwable) throws Exception {
                        return null;
                    }
                })
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {

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

package com.example.phundal2091.basicapplication.ui;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.phundal2091.basicapplication.R;
import com.example.phundal2091.basicapplication.framework.Presenter;
import com.example.services.IApiService;

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

    public ContentViewPresenter(Context context, ContentView view, IApiService apiService) {
        super(context, view, false);
        this.apiService = apiService;
    }

    @Override
    public void bindControls() {

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

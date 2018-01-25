package com.example.phundal2091.basicapplication.framework;


import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;

import com.example.phundal2091.basicapplication.BasicApplication;

import butterknife.ButterKnife;

/**
 * Created by phundal on 9/6/17.
 */

public abstract class Presenter<V extends ViewScreen, TModel> implements IPresenter<V, TModel> {

    protected boolean isShown = false;
    protected Context context;
    protected V view;
    protected TModel model;

    protected Presenter(Context context, V view, TModel model) {
        this.context = context;
        this.view = view;
        this.model = model;
        ButterKnife.bind(view, view.getRootView());
    }

    public View getActionBarView() {
        Window window = ((Activity)context).getWindow();
        View v = window.getDecorView();
        int resId = context.getResources().getIdentifier("action_bar_container", "id", "android");
        return v.findViewById(resId);
    }

    protected void stopLoadingProgress(ProgressBar progressBar){
        progressBar.setVisibility(View.GONE);
    }


    //region random generic helper methods -> should be moved out of base presenter at some point
    protected BasicApplication getApplication(){
        return (BasicApplication) ((Activity) context).getApplication();
    }

    protected void bindToolbarForChildActivity(Toolbar toolbar){

        toolbar.setVisibility(View.VISIBLE);
        ((AppCompatActivity)context).setSupportActionBar(toolbar);
        ((AppCompatActivity)context).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    protected void hideToolbarHomeButton(Toolbar toolbar){

        ((AppCompatActivity)context).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }
    protected void bindToolbarForParentActivity(Toolbar toolbar, String title){

        toolbar.setVisibility(View.VISIBLE);
        toolbar.setTitle(title);
        ((AppCompatActivity)context).setTitle(title);
        ((AppCompatActivity)context).setSupportActionBar(toolbar);
        ((AppCompatActivity)context).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    protected void bindToolbarForMainActivity(Toolbar toolbar, String title){

        toolbar.setVisibility(View.VISIBLE);
        toolbar.setTitle(title);
        ((AppCompatActivity)context).setSupportActionBar(toolbar);
    }

    protected void bindToolbarForChildActivity(Toolbar toolbar, String title){

        bindToolbarForChildActivity(toolbar);
        ((AppCompatActivity)context).setTitle(title);
    }

    protected void showKeyboardForInput(View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, 0);
    }

    protected void hideKeyboard() {
        // Check if no view has focus:
        View view = ((Activity)context).getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }
    //endregion


    protected Presenter(Context context, V view) {
        this(context, view, true);
    }

    protected Presenter(Context context, V view, boolean injectsViewsAtConstuction) {
        this.context = context;
        this.view = view;
        if(injectsViewsAtConstuction) {
            ButterKnife.bind(view, view.getRootView());
        }
    }

    public V getView() {
        return this.view;
    }

    public Presenter<V, TModel> withModel(TModel model) {
        this.model = model;
        return this;
    }
    public TModel getModel() {
        return this.model;
    }

    public abstract void bindControls();


    public abstract void hide();
    public abstract void show();
    public void refreshUI(){
        if(view != null){
            view.rootView.invalidate();
        }
    }
    public void toggleVisibiility() {
        if (!isShown)
            show();
        else
            hide();
    }
}
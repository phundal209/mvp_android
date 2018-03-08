package com.example.phundal2091.basicapplication.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;

import com.example.api.PhotosResponse;
import com.example.phundal2091.basicapplication.R;
import com.example.phundal2091.basicapplication.framework.Presenter;
import com.example.phundal2091.basicapplication.wrapper.IImageWrapper;
import com.example.services.IApiService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static android.support.v7.widget.RecyclerView.*;

/**
 * Created by phundal2091 on 1/18/18.
 */

public class ContentViewPresenter extends Presenter<ContentView, Object> implements IContentViewPresenter {

    private static final String TAG = ContentViewPresenter.class.getSimpleName();
    private IApiService apiService;
    private ProgressDialog progressDialog;
    private IImageWrapper imageWrapper;
    private ContentViewAdapter contentViewAdapter;
    private int num_of_cols = 3;
    private GridLayoutManager gridLayoutManager;
    private String defaultQuery = "kittens";
    private boolean loading = true;
    private int initialPage = 1;
    private int visibleItemCount;
    private int totalItemCount;
    private int pastVisiblesItems;

    public ContentViewPresenter(Context context, ContentView view, IApiService apiService, IImageWrapper iImageWrapper) {
        super(context, view, false);
        this.apiService = apiService;
        this.imageWrapper = iImageWrapper;
    }

    @Override
    public void bindControls() {
        makeApiCall(defaultQuery, initialPage);
        search();
    }

    private String buildUrlForImage(int farmKey, String serverKey, String id, String secret) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://farm");
        stringBuilder.append(String.valueOf(farmKey));
        stringBuilder.append(".static.flickr.com/");
        stringBuilder.append(serverKey);
        stringBuilder.append("/");
        stringBuilder.append(id);
        stringBuilder.append("_");
        stringBuilder.append(secret);
        stringBuilder.append(".jpg");
        return stringBuilder.toString();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void addPaginationToRecycler() {
        view.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if (loading) {
                    if (dy > 0) //check for scroll down
                    {
                        visibleItemCount = gridLayoutManager.getChildCount();
                        totalItemCount = gridLayoutManager.getItemCount();
                        pastVisiblesItems = gridLayoutManager.findFirstVisibleItemPosition();
                        print(visibleItemCount + pastVisiblesItems, totalItemCount);
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                            makeApiCall(defaultQuery, initialPage++);
                        }

                    }
                }
            }
        });
    }

    private void print(int visiblePlusPast, int totalItemCount) {
        Log.d(TAG, "visiblePlusPast = " + visiblePlusPast);
        Log.d(TAG, "totalItemCount = " + totalItemCount);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void bindAdapter(List<String> listOfUrls) {
        if (contentViewAdapter == null) {
            gridLayoutManager = new GridLayoutManager(context, num_of_cols);
            contentViewAdapter = new ContentViewAdapter(listOfUrls, context, imageWrapper);
            view.recyclerView.setLayoutManager(gridLayoutManager);
            view.recyclerView.setHasFixedSize(true);
            view.recyclerView.setAdapter(contentViewAdapter);
            addPaginationToRecycler();
        } else {
            contentViewAdapter.update(listOfUrls);
        }
    }

    private void makeApiCall(String query, int page) {
        final List<String> localUrls = new ArrayList<>();
        show();
        apiService.getFlickrPhotos(query, page).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .onErrorReturn(new Function<Throwable, PhotosResponse>() {
                    @Override
                    public PhotosResponse apply(Throwable throwable) throws Exception {
                        hide();
                        return new PhotosResponse();
                    }
                })
                .subscribe(new Consumer<PhotosResponse>() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void accept(PhotosResponse photosResponse) throws Exception {
                        for (PhotosResponse.PhotoEntity photoEntity : photosResponse.getPhotos().getPhoto()) {
                            int farm = photoEntity.getFarm();
                            String server = photoEntity.getServer();
                            String id = photoEntity.getId();
                            String secret = photoEntity.getSecret();
                            String imageUrl = buildUrlForImage(farm, server, id, secret);
                            localUrls.add(imageUrl);
                        }
                        bindAdapter(localUrls);
                        hide();
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

    @Override
    public void search() {
        view.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                makeApiCall(query, initialPage);
                return true;
            }

            @Override
            public boolean onQueryTextChange(final String newText) {
                return false;
            }
        });
    }
}

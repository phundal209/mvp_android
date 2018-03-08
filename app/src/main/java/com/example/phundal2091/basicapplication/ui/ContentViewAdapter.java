package com.example.phundal2091.basicapplication.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.phundal2091.basicapplication.R;
import com.example.phundal2091.basicapplication.wrapper.IImageWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by phundal2091 on 2/2/18.
 */

public class ContentViewAdapter extends RecyclerView.Adapter<ContentViewAdapter.ViewHolder> {
    List<String> urls;
    Context context;
    IImageWrapper imageWrapper;

    public ContentViewAdapter(List<String> urls, Context context, IImageWrapper imageWrapper) {
        this.urls = urls;
        this.context = context;
        this.imageWrapper = imageWrapper;
    }

    @Override
    public ContentViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.flickr_items, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContentViewAdapter.ViewHolder holder, int position) {
        String url = urls.get(position);
        imageWrapper.displayImage(url, holder.flickr_image);
    }

    @Override
    public int getItemCount() {
        if (urls == null) {
            return 0;
        }
        return urls.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void update(List<String> listOfUrls) {
        if (urls!= null) {
            urls.clear();
        } else {
            urls = new ArrayList<>();
        }
        urls.addAll(listOfUrls);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.flickr_image)
        public ImageView flickr_image;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

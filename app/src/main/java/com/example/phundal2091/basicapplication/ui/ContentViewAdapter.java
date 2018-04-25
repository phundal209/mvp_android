package com.example.phundal2091.basicapplication.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.phundal2091.basicapplication.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by phundal2091 on 4/25/18.
 */

public class ContentViewAdapter extends RecyclerView.Adapter<ContentViewAdapter.ContentViewHolder> {

    private List<Object> resultsArray;

    public ContentViewAdapter(List<Object> resultsArray) {
        this.resultsArray = resultsArray;
    }

    @Override
    public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.results_item, parent, false);
        return new ContentViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ContentViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (resultsArray != null) return resultsArray.size();
        return 0;
    }

    public class ContentViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.resultsText)
        public TextView resultsText;

        public ContentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

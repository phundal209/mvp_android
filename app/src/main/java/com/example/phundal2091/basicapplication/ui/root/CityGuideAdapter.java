package com.example.phundal2091.basicapplication.ui.root;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.phundal2091.basicapplication.R;

import butterknife.ButterKnife;

/**
 * Created by phundal on 3/12/18.
 */

public class CityGuideAdapter extends RecyclerView.Adapter<CityGuideAdapter.ViewHolder> {

    @Override
    public CityGuideAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_guide_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(CityGuideAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

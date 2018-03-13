package com.example.phundal2091.basicapplication.ui.bars;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phundal2091.basicapplication.R;
import com.example.phundal2091.basicapplication.ui.root.CityGuideAdapter;
import com.example.phundal2091.basicapplication.wrapper.IImageWrapper;
import com.example.phundal2091.basicapplication.wrapper.ImageWrapper;
import com.google.android.gms.location.places.Place;

import java.util.List;

/**
 * Created by phundal on 3/12/18.
 */

public class BarRecyclerAdapter extends CityGuideAdapter {
    private List<Place> places;

    public BarRecyclerAdapter(List<Place> places, Context context) {
        super(places, context);
        this.places = places;
    }

    @Override
    public CityGuideAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(CityGuideAdapter.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Place place = places.get(position);
        holder.city_item_type_name.setText(place.getName());
        holder.city_item_type_image.setImageResource(R.mipmap.ic_bar);
    }

    @Override
    public int getItemCount() {
        if (places != null &&
                places.size() > 0) {
            return places.size();
        }
        return 0;
    }
}

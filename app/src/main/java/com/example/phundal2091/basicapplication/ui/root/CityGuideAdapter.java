package com.example.phundal2091.basicapplication.ui.root;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.phundal2091.basicapplication.R;
import com.google.android.gms.location.places.Place;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CityGuideAdapter extends RecyclerView.Adapter<CityGuideAdapter.ViewHolder> {

    private List<Place> places;
    private Context context;

    public CityGuideAdapter(List<Place> places, Context context) {
        this.places = places;
        this.context = context;
    }

    @Override
    public CityGuideAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_guide_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(CityGuideAdapter.ViewHolder holder, int position) {
        Place place = places.get(position);
        double ratingOfPlace = getValidRating(place);
        flipStarsBasedOnRating(holder, ratingOfPlace);
    }

    @Override
    public int getItemCount() {
        if (places != null &&
                places.size() > 0) {
            return places.size();
        }
        return 0;
    }

    public double getValidRating(Place place) {
        if (place.getRating() > 0) {
            return Math.floor(place.getRating());
        }
        return 0;
    }

    public void flipStarsBasedOnRating(ViewHolder holder, double rating) {
        if (rating == 1) {
            holder.star1.setImageResource(R.mipmap.star_pink);
        } else if(rating == 2) {
            holder.star2.setImageResource(R.mipmap.star_pink);
        } else if(rating == 3) {
            holder.star3.setImageResource(R.mipmap.star_pink);
        } else if (rating == 4) {
            holder.star4.setImageResource(R.mipmap.star_pink);
        } else if (rating == 5) {
            holder.star5.setImageResource(R.mipmap.star_pink);
        }
    }

    public void addAll(List<Place> newPlaces) {
        places.addAll(newPlaces);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @BindView(R.id.city_item_type_image)
        public ImageView city_item_type_image;

        @BindView(R.id.city_item_type_name)
        public TextView city_item_type_name;

        @BindView(R.id.star1)
        public ImageView star1;

        @BindView(R.id.star1)
        public ImageView star2;

        @BindView(R.id.star1)
        public ImageView star3;

        @BindView(R.id.star1)
        public ImageView star4;

        @BindView(R.id.star1)
        public ImageView star5;
    }
}

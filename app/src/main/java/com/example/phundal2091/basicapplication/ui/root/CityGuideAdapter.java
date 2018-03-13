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
import com.example.phundal2091.basicapplication.ui.PlaceType;
import com.google.android.gms.location.places.Place;

import java.util.List;

public class CityGuideAdapter extends RecyclerView.Adapter<CityGuideAdapter.ViewHolder> {

    private List<Place> places;
    private Context context;
    private PlaceType placeType;

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
        holder.city_item_type_name.setText(place.getName());
        double ratingOfPlace = getValidRating(place);
        flipStarsBasedOnRating(holder, ratingOfPlace);

        switch (placeType) {
            case BAR:
                holder.city_item_type_image.setImageResource(R.mipmap.ic_bar);
                break;
            case BISTRO:
                holder.city_item_type_image.setImageResource(R.mipmap.ic_bistro);
                break;
            case CAFE:
                holder.city_item_type_image.setImageResource(R.mipmap.ic_cafe);
                break;
        }
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
            holder.star1.setImageResource(R.mipmap.star_pink);
            holder.star2.setImageResource(R.mipmap.star_pink);
        } else if(rating == 3) {
            holder.star1.setImageResource(R.mipmap.star_pink);
            holder.star2.setImageResource(R.mipmap.star_pink);
            holder.star3.setImageResource(R.mipmap.star_pink);
        } else if (rating == 4) {
            holder.star1.setImageResource(R.mipmap.star_pink);
            holder.star2.setImageResource(R.mipmap.star_pink);
            holder.star3.setImageResource(R.mipmap.star_pink);
            holder.star4.setImageResource(R.mipmap.star_pink);
        } else if (rating == 5) {
            holder.star1.setImageResource(R.mipmap.star_pink);
            holder.star2.setImageResource(R.mipmap.star_pink);
            holder.star3.setImageResource(R.mipmap.star_pink);
            holder.star4.setImageResource(R.mipmap.star_pink);
            holder.star5.setImageResource(R.mipmap.star_pink);
        }
    }

    public void addAll(List<Place> newPlaces) {
        places.addAll(newPlaces);
        notifyDataSetChanged();
    }

    public void setTypeOfItem(PlaceType placeType) {
        this.placeType = placeType;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView city_item_type_image;
        public TextView city_item_type_name;
        public ImageView star1;
        public ImageView star2;
        public ImageView star3;
        public ImageView star4;
        public ImageView star5;
        public ViewHolder(View itemView) {
            super(itemView);
            city_item_type_image = itemView.findViewById(R.id.city_item_type_image);
            city_item_type_name = itemView.findViewById(R.id.city_item_type_name);
            star1 = itemView.findViewById(R.id.star1);
            star2 = itemView.findViewById(R.id.star2);
            star3 = itemView.findViewById(R.id.star3);
            star4 = itemView.findViewById(R.id.star4);
            star5 = itemView.findViewById(R.id.star5);
        }

    }
}

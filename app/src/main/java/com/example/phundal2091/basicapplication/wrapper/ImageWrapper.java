package com.example.phundal2091.basicapplication.wrapper;

import android.content.Context;
import android.widget.ImageView;

import com.example.phundal2091.basicapplication.R;
import com.squareup.picasso.Picasso;

/**
 * Created by phundal2091 on 1/24/18.
 *
 * Wrapper to load images. Facade to hold whatever library you use
 * for rendering images.
 */

public class ImageWrapper implements IImageWrapper {
    private Context context;

    public ImageWrapper(Context context) {
        this.context = context;
    }

    @Override
    public void displayImage(String url, ImageView holder) {
        Picasso.with(context)
                .load(url)
                .error(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder);
    }
}

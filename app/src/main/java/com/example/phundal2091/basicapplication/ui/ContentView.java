package com.example.phundal2091.basicapplication.ui;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.phundal2091.basicapplication.R;
import com.example.phundal2091.basicapplication.framework.ViewScreen;

import butterknife.Bind;

/**
 * Created by phundal2091 on 1/18/18.
 */

public class ContentView extends ViewScreen {
    @Bind(R.id.retake)
    public Button retake;
    @Bind(R.id.time_count)
    public TextView time_count;
    @Bind(R.id.question)
    public TextView question;
    @Bind(R.id.image_one)
    public ImageView image_one;
    @Bind(R.id.image_two)
    public ImageView image_two;
    @Bind(R.id.image_three)
    public ImageView image_three;
    @Bind(R.id.image_four)
    public ImageView image_four;
    @Bind(R.id.questionsLayout)
    public RelativeLayout questionsLayout;

    public ContentView(View rootView) {
        super(rootView);
    }
}

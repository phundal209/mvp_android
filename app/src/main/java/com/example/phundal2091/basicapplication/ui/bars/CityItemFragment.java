package com.example.phundal2091.basicapplication.ui.bars;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phundal2091.basicapplication.R;
import com.example.phundal2091.basicapplication.ui.PlaceType;
import com.example.phundal2091.basicapplication.ui.root.MainActivity;

import javax.inject.Inject;

public class CityItemFragment extends Fragment {

    @Inject
    ICityItemPresenter cityItemPresenter;
    private RecyclerView recyclerView;
    private PlaceType type;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View inflate = inflater.inflate(R.layout.fragment_bar, container, false);

        MainActivity activity = (MainActivity) getActivity();
        activity.component().inject(this);
        cityItemPresenter.getView().withRootView(inflate);
        recyclerView = inflate.findViewById(R.id.recyclerView);
        cityItemPresenter.bindAdapter(recyclerView);
        cityItemPresenter.setType(type);
        cityItemPresenter.bindControls();
        return inflate;
    }

    public void setType(PlaceType type) {
        this.type = type;
    }
}

package com.example.phundal2091.basicapplication.ui.cafes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phundal2091.basicapplication.R;
import com.example.phundal2091.basicapplication.ui.root.MainActivity;

import javax.inject.Inject;

public class CafeFragment extends Fragment {

    @Inject
    ICafePresenter cafePresenter;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View inflate = inflater.inflate(R.layout.fragment_cafe, container, false);

        MainActivity activity = (MainActivity) getActivity();
        activity.component().inject(this);
        cafePresenter.getView().withRootView(inflate);
        recyclerView = inflate.findViewById(R.id.recyclerView);
        cafePresenter.bindAdapter(recyclerView);
        cafePresenter.bindControls();

        return inflate;
    }

}

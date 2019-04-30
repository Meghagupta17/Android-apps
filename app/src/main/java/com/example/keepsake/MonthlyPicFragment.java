package com.example.keepsake;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;


public class MonthlyPicFragment extends Fragment {

    private GridView gridView;

    private ArrayList<GridViewImage> gridViewArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monthlypic, container, false);

        gridView = view.findViewById(R.id.grid_view_image_text);

        gridViewArrayList.add(new GridViewImage());
        gridViewArrayList.add(new GridViewImage());
        gridViewArrayList.add(new GridViewImage());
        gridViewArrayList.add(new GridViewImage());
        CustomAdaptor customAdaptor = new CustomAdaptor(getActivity(), gridViewArrayList);
        gridView.setAdapter(customAdaptor);
        return view;

    }

    }

package com.example.keepsake;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class FamilyPicFragment extends Fragment {

    private GridView gridView;
    List<Integer> image = new ArrayList<>();
    List<String> imageText = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_familypic, container, false);

        gridView = view.findViewById(R.id.grid_view_family);
        image.add(R.drawable.welcome);
        image.add(R.drawable.welcome);
        image.add(R.drawable.welcome);
        image.add(R.drawable.welcome);
        image.add(R.drawable.welcome);
        imageText.add("First pic with Mom & dad");
        imageText.add("First pic with Mom & dad");
        imageText.add("First pic with Mom & dad");
        imageText.add("First pic with Mom & dad");
        imageText.add("1 Month old");
        ImageAdapter imageAdapter = new ImageAdapter(getActivity(), image, imageText );
        gridView.setAdapter(imageAdapter);

        return view;

    }

    }

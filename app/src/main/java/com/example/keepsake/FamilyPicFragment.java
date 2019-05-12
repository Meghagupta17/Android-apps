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
    List<String> image = new ArrayList<>();
    List<String> imageText = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_familypic, container, false);

        gridView = view.findViewById(R.id.grid_view_family);
        image.add("baby.jpg");
        image.add("baby.jpg");
        image.add("baby.jpg");
        image.add("baby.jpg");
        image.add("baby.jpg");
        image.add("baby.jpg");
        image.add("baby.jpg");
        image.add("baby.jpg");
        /*image.add(R.drawable.baby);
        image.add(R.drawable.baby);
        image.add(R.drawable.welcome);
        image.add(R.drawable.welcome);
        image.add(R.drawable.welcome);
        image.add(R.drawable.welcome);
        image.add(R.drawable.welcome);
        image.add(R.drawable.welcome);*/

        imageText.add(getString(R.string.picwithmomdad));
        imageText.add(getString(R.string.picwithsiblings));
        imageText.add(getString(R.string.picwithgrandparents));
        imageText.add(getString(R.string.picwithgrandparentss));
        imageText.add(getString(R.string.picwithunclenaunt));
        imageText.add(getString(R.string.picwithunclenaunt2));
        imageText.add(getString(R.string.picwithcousin));
        imageText.add(getString(R.string.picwithcousin2));

        ImageAdapter imageAdapter = new ImageAdapter(getActivity(), image, imageText );
        gridView.setAdapter(imageAdapter);

        return view;

    }

    }

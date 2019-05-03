package com.example.keepsake;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;


public class MonthlyPicFragment extends Fragment {

    /*private GridView gridView;
    private ArrayList<GridViewImage> gridViewArrayList = new ArrayList<>();

    int[] image = {
            R.drawable.welcome,
            R.drawable.welcome,
            R.drawable.welcome,
            R.drawable.welcome,
            R.drawable.welcome,
            R.drawable.welcome,
    };
    String[] imageText = {
            "Google",
            "Github",
            "Instagram",
            "Facebook",
            "Flickr",
            "Pinterest",
            "Quora",
            "Twitter",
            "Vimeo",
            "WordPress",
            "Youtube",
            "Stumbleupon",
            "SoundCloud",
            "Reddit",
            "Blogger"};*/

    private GridView gridView;
    List<Integer> image = new ArrayList<>();
    List<String> imageText = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monthlypic, container, false);

        gridView = view.findViewById(R.id.grid_view_image_text);
        image.add(R.drawable.welcome);
        image.add(R.drawable.welcome);
        image.add(R.drawable.welcome);
        image.add(R.drawable.welcome);
        image.add(R.drawable.welcome);
        imageText.add("1 Month old");
        imageText.add("1 Month old");
        imageText.add("1 Month old");
        imageText.add("1 Month old");
        imageText.add("1 Month old");
        ImageAdapter imageAdapter = new ImageAdapter(getActivity(), image, imageText );
        gridView.setAdapter(imageAdapter);

        /*gridViewArrayList.add(new GridViewImage());
        gridViewArrayList.add(new GridViewImage());
        gridViewArrayList.add(new GridViewImage());
        gridViewArrayList.add(new GridViewImage());
        CustomAdaptor customAdaptor = new CustomAdaptor(getActivity(), gridViewArrayList);
        gridView.setAdapter(customAdaptor);*/

        return view;
    }



}

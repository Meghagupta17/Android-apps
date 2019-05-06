package com.example.keepsake;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class MonthlyPicFragment extends Fragment {

    private GridView gridView;
    List<String> image = new ArrayList<>();
    List<String> imageText = new ArrayList<>();
    private Uri imageUri;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private int PICK_IMAGE_REQUEST = 1;
    Button btnUpload;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monthlypic, container, false);

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference("photos");

        gridView = view.findViewById(R.id.grid_view_image_text);

        image.add("babymonth.jpg");
        image.add("babymonth.jpg");
        image.add("babymonth.jpg");
        image.add("babymonth.jpg");
        image.add("babymonth.jpg");
        image.add("babymonth.jpg");
        image.add("babymonth.jpg");
        image.add("babymonth.jpg");
        image.add("babymonth.jpg");
        image.add("babymonth.jpg");
        image.add("babymonth.jpg");
        image.add("babymonth.jpg");
       /* image.add(R.drawable.babymonth);
        image.add(R.drawable.babymonth);
        image.add(R.drawable.babymonth);
        image.add(R.drawable.babymonth);
        image.add(R.drawable.babymonth);
        image.add(R.drawable.babymonth);
        image.add(R.drawable.babymonth);
        image.add(R.drawable.babymonth);
        image.add(R.drawable.babymonth);
        image.add(R.drawable.babymonth);
        image.add(R.drawable.babymonth);*/

        imageText.add(getString(R.string.one_month_old));
        imageText.add(getString(R.string.two_months_old));
        imageText.add(getString(R.string.three_months_old));
        imageText.add(getString(R.string.four_months_old));
        imageText.add(getString(R.string.five_months_old));
        imageText.add(getString(R.string.six_months_old));
        imageText.add(getString(R.string.seven_months_old));
        imageText.add(getString(R.string.eight_months_old));
        imageText.add(getString(R.string.nine_months_old));
        imageText.add(getString(R.string.ten_months_old));
        imageText.add(getString(R.string.eleven_months_old));
        imageText.add(getString(R.string.twelve_months_old));

        ImageAdapter imageAdapter = new ImageAdapter(getActivity(), image, imageText );
        gridView.setAdapter(imageAdapter);

        return view;
    }
}

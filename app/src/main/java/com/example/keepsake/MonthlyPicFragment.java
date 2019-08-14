package com.example.keepsake;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class MonthlyPicFragment extends Fragment {


    List<String> image = new ArrayList<>();
    List<String> imageText = new ArrayList<>();
    private Uri imageUri;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    Button btnUpload;
    int arrayPosition;
    GridView gridView;
    boolean status ;
    LayoutInflater li;
    ViewGroup c;
    Bundle bs;
    FirebaseUser user;
    FirebaseAuth firebaseAuth;


    public MonthlyPicFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_monthlypic, container, false);

        li = inflater;
        c = container;
        bs = savedInstanceState;

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference("photos");

        gridView = view.findViewById(R.id.grid_view_image_text);

        if(!checkImage( "m_0")){
                image.add(user.getEmail()+"babymonth.jpg");

        }else{
            image.add(user.getEmail()+"m_0");
        }

        if(! checkImage("m_1")){
            image.add("babymonth.jpg");

        }else{
            image.add(user.getEmail()+"m_1");

        }

        if(! checkImage("m_2")){
            image.add("babymonth.jpg");

        }else{
            image.add(user.getEmail()+"m_2");

        }
        if(! checkImage("m_3")){
            image.add("babymonth.jpg");

        }else{
            image.add(user.getEmail()+"m_3");

        }

        if(! checkImage("m_4")){
            image.add("babymonth.jpg");

        }else{
            image.add(user.getEmail()+"m_4");

        }

        if(! checkImage( "m_5")){
            image.add("babymonth.jpg");

        }else{
            image.add(user.getEmail()+"m_5");

        }
        if(! checkImage("m_6")){
            image.add("babymonth.jpg");

        }else{
            image.add(user.getEmail()+"m_6");

        }

        if(! checkImage("m_7")){
            image.add("babymonth.jpg");

        }else{
            image.add(user.getEmail()+"m_7");

        }
        if(! checkImage("m_8")){
            image.add("babymonth.jpg");

        }else{
            image.add(user.getEmail()+"m_8");

        }

        if(! checkImage("m_9")){
            image.add("babymonth.jpg");

        }else{
            image.add(user.getEmail()+"m_9");

        }

        if(! checkImage("m_10")){
            image.add("babymonth.jpg");

        }else{
            image.add(user.getEmail()+"m_10");

        }

        if(! checkImage("m_11")){
            image.add("babymonth.jpg");

        }else{
            image.add(user.getEmail()+"m_11");

        }

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

        ImageAdapter imageAdapter = new ImageAdapter(getContext(), 'm', image, imageText);
        gridView.deferNotifyDataSetChanged();
        gridView.setAdapter(imageAdapter);

        return view;
    }

    public boolean checkImage(String fileName) {

        final String urlImage = "https://firebasestorage.googleapis.com/v0/b/keepsake-ffd69.appspot.com/o/photos%2F"+ user.getEmail()+fileName+"?alt=media";
        try {
            Picasso.get()
                    .load(urlImage)
            ;


        }catch (Exception e){
            return false;
        }

        return true;
    }

    public void reload() {
        onCreateView(li, c, bs);
    }
}

package com.example.keepsake;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FamilyPicFragment extends Fragment {

    private GridView gridView;
    List<String> image = new ArrayList<>();
    List<String> imageText = new ArrayList<>();
    FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    FirebaseUser user;
    FirebaseAuth firebaseAuth;

    public FamilyPicFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_familypic, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference("photos");

        gridView = view.findViewById(R.id.grid_view_family);

        if(!checkImage("f_0")){
            image.add("baby.jpg");

        }else{
            image.add(user.getEmail()+"f_0");
        }

        if(!checkImage("f_1")){
            image.add("baby.jpg");

        }else{
            image.add(user.getEmail()+"f_1");
        }

        if(!checkImage("f_2")){
            image.add("baby.jpg");

        }else{
            image.add(user.getEmail()+"f_2");
        }

        if(!checkImage("f_3")){
            image.add("baby.jpg");

        }else{
            image.add(user.getEmail()+"f_3");
        }

        if(!checkImage("f_4")){
            image.add("baby.jpg");

        }else{
            image.add(user.getEmail()+"f_4");
        }

        if(!checkImage("f_5")){
            image.add("baby.jpg");

        }else{
            image.add(user.getEmail()+"f_5");
        }

        if(!checkImage("f_6")){
            image.add("baby.jpg");

        }else{
            image.add(user.getEmail()+"f_6");
        }

        if(!checkImage("f_7")){
            image.add("baby.jpg");

        }else{
            image.add(user.getEmail()+"f_7");
        }

        imageText.add(getString(R.string.picwithmomdad));
        imageText.add(getString(R.string.picwithsiblings));
        imageText.add(getString(R.string.picwithgrandparents));
        imageText.add(getString(R.string.picwithgrandparentss));
        imageText.add(getString(R.string.picwithunclenaunt));
        imageText.add(getString(R.string.picwithunclenaunt2));
        imageText.add(getString(R.string.picwithcousin));
        imageText.add(getString(R.string.picwithcousin2));

        ImageAdapter imageAdapter = new ImageAdapter(getActivity(), 'f', image, imageText );
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


}

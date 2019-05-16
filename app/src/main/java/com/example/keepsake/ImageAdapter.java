package com.example.keepsake;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends BaseAdapter {

    private List<String> image;
    private List<String> imageText;
    private Context context;
    private LayoutInflater inflater;
    Button btnUpload;
    private Uri imageUri;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private int PICK_IMAGE_REQUEST = 1;
    LoadImage li;
    char parentFragment ;


    public interface LoadImage{
        public void loadImageFunction(int position, char parent);
    }



    public ImageAdapter(Context context, char parentFragment, List<String> image, List<String>imageText) {

        this.context = context;
        this.image = image;
        this.imageText = imageText;
        this.parentFragment = parentFragment;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return imageText.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View view = inflater.inflate(R.layout.gridview, null);

        ImageView imageView = view.findViewById(R.id.gridview_image);

        final String urlImage = "https://firebasestorage.googleapis.com/v0/b/keepsake-ffd69.appspot.com/o/photos%2F"+ image.get(position)+"?alt=media";

        Picasso.get()
                .load(urlImage)
                .into(imageView);

        TextView textView = view.findViewById(R.id.gridview_text);
        textView.setText(imageText.get(position));

        btnUpload = view.findViewById(R.id.btnUpload);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               li = (LoadImage)context;
               li.loadImageFunction(position, parentFragment);

            }
        });

        return view;
    }


}

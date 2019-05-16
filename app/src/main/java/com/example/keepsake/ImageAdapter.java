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

    public interface LoadImage{
        public void loadImageFunction(int position);
    }



    public ImageAdapter(Context context, List<String> image, List<String>imageText) {

        this.context = context;
        this.image = image;
        this.imageText = imageText;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.gridview, null);

        ImageView imageView = view.findViewById(R.id.gridview_image);
        //imageView.setImageResource(image.get(position));
        //final String urlImage = "https://firebasestorage.googleapis.com/v0/b/keepsake-ffd69.appspot.com/o/photos%2F"+ image.get(position)+"?alt=media";
        //https://firebasestorage.googleapis.com/v0/b/keepsake-ffd69.appspot.com/o/photos%2Fbaby.jpg?alt=media&token=8c9bb29a-1a70-41b4-87d1-0dbc9624a270
        //https://firebasestorage.googleapis.com/v0/b/keepsake-ffd69.appspot.com/o/babymonth.jpg?alt=media&token=191e2d3b-b3b4-4cd0-9c8b-afaa6b463e1f
       //GlideApp.with(context)
        final String urlImage = "https://media.wired.com/photos/5a595516f11e325008172bcb/master/w_582,c_limit/BabyGlimpseBaby-640504936.jpg";
        Glide.with(context)
                .asBitmap()
                .load(urlImage)
                .into(imageView);

        TextView textView = view.findViewById(R.id.gridview_text);
        textView.setText(imageText.get(position));

        btnUpload = view.findViewById(R.id.btnUpload);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  li = (LoadImage)context;
               // li.loadImageFunction(position);

            }
        });

        return view;
    }


}

package com.example.keepsake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImageAdapter extends BaseAdapter {

    private List<Integer> image;
    private List<String> imageText;
    private Context context;
    private LayoutInflater inflater;

    public ImageAdapter(Context context, List<Integer> image, List<String>imageText) {

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
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.gridview, null);
        ImageView imageView = view.findViewById(R.id.gridview_image);
        imageView.setImageResource(image.get(position));
        TextView textView = view.findViewById(R.id.gridview_text);
        textView.setText(imageText.get(position));

        return view;
    }
}

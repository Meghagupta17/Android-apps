package com.example.keepsake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdaptor extends BaseAdapter{

    public ArrayList<GridViewImage> gridViewArrayList = new ArrayList<GridViewImage>();
    private Context context;

    public CustomAdaptor(Context context, ArrayList<GridViewImage> gridViewArrayList){

        this.context = context;
        this.gridViewArrayList = gridViewArrayList;
    }

    @Override
    public int getCount() {
        return gridViewArrayList.size();
    }

    @Override
    public Object getItem(int pos) {
        return gridViewArrayList.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                   convertView = inflater.inflate(R.layout.gridview, null);
        }

        TextView gridviewtext = (TextView) convertView.findViewById(R.id.gridview_text);
        ImageView gridviewimage = (ImageView) convertView.findViewById(R.id.gridview_image);

        gridviewtext.setText(gridViewArrayList.get(pos).imageText);
       // gridviewtext.setText(imagetext[pos]);
        Glide.with(context)
                .asBitmap()
                .load(gridViewArrayList.get(pos).image)
                .into(gridviewimage)
        ;

        return convertView;
    }
}

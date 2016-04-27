package com.fill_tummy.uday.moviestage1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dlrl on 26/04/16.
 */
public class Posters extends BaseAdapter {
    Context c;
    ArrayList<String> images;
    void  setData (ArrayList<String> im){
        this.images = im;
        notifyDataSetChanged();
    }

    Posters (Context c, ArrayList  a)
    {
        images = a;
        this.c = c;
    }
    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater li = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gr =li.inflate(R.layout.fragemnt_item,parent, false);
        ImageView img = (ImageView) gr.findViewById(R.id.image);
        Picasso.with(c).load(images.get(position)).into(img);
        return img;
    }
}

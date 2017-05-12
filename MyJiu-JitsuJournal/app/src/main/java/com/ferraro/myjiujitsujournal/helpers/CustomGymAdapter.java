package com.ferraro.myjiujitsujournal.helpers;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ferraro.myjiujitsujournal.mjjj.R;

public class CustomGymAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] gymName;
    private final String[] gymLocation;
    private final Integer[] imgid;

    public CustomGymAdapter(Activity context, String[] gymName, Integer[] imgid, String[] gymLocation) {
        super(context, R.layout.gym_list_layout, gymName);

        this.context=context;
        this.gymName =gymName;
        this.gymLocation = gymLocation;
        this.imgid=imgid;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.gym_list_layout, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);

        txtTitle.setText(gymName[position]);
        imageView.setImageResource(imgid[position]);
        extratxt.setText(gymLocation[position]);
        return rowView;

    };
}
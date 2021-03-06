package com.example.yuuna.finalprojectlibrary_searcher;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashMap;

public class CustomAdapter extends ArrayAdapter<LinkedHashMap<String,String>>{

    ArrayList<LinkedHashMap<String,String>> dataSet;
    Context mContext;

    private static class ViewHolder {
        TextView title;
        ImageView dbType;
    }

    public CustomAdapter(ArrayList<LinkedHashMap<String,String>> data, Context context) {
        super(context,R.layout.item,data);
        this.dataSet = data;
        this.mContext=context;
    }

    private int lastPosition = -1;
    @Override
    public int getCount() {
        return dataSet.size();
    }
    @Override
    public LinkedHashMap<String,String> getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LinkedHashMap<String,String> data = getItem(position);

        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item, parent, false);
            viewHolder.title = (TextView) convertView.findViewById(R.id.list_title);
            viewHolder.dbType = (ImageView) convertView.findViewById(R.id.list_dbType);
            result=convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.top_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;
        //TODO : imageView setting
        viewHolder.title.setText(data.get("자료명/저자사항"));
        return convertView;
    }
}

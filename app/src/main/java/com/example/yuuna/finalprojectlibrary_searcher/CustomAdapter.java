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
import java.util.HashMap;

public class CustomAdapter extends ArrayAdapter<HashMap<String,String>>{

    ArrayList<HashMap<String,String>> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView title;
        ImageView dbType;
    }

    public CustomAdapter(ArrayList<HashMap<String,String>> data, Context context) {
        super(context,R.layout.item,data);
        this.dataSet = data;
        this.mContext=context;
    }
    /*
    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        HashMap<String,String> dataModel=(HashMap<String,String>)object;

        switch (v.getId())
        {
            case R.id.item_info:
                Snackbar.make(v, "Release date " +dataModel.getFeature(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }
    }

    */
    private int lastPosition = -1;
    @Override
    public int getCount() {
        return dataSet.size();
    }
    @Override
    public HashMap<String,String> getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        HashMap<String,String> data = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

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

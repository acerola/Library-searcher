package com.example.yuuna.finalprojectlibrary_searcher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class SimpleAdapter extends ArrayAdapter<String[]> {
    ArrayList<String[]> dataSet;
    Context mContext;

    private static class ViewHolder {
        TextView name;
        TextView value;
    }

    private int lastPosition = -1;

    public SimpleAdapter(ArrayList<String[]> data, Context context) {
        super(context,R.layout.item2,data);
        this.dataSet =data;
        this.mContext=context;
    }
    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public String[] getItem(int position) {
        Iterator keyData = dataSet.iterator();
        String[] k = new String[2];
        for(int i=0;i<=position;i++)
        {
            k = ((String[])keyData.next());
        }
        return k;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        String[] data = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        SimpleAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {
            viewHolder = new SimpleAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item2, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.list_name);
            viewHolder.value = (TextView) convertView.findViewById(R.id.list_value);
            result=convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (SimpleAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.top_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;
        viewHolder.name.setText(data[0]);
        viewHolder.value.setText(data[1]);
        return convertView;
    }
}

package com.example.dailyfeeder;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class VolleyListViewAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    List<CommonBean> VolleyList;
    public VolleyListViewAdapter(Activity activity, List<CommonBean> VolleyList) {
        this.activity = activity;
        this.VolleyList = VolleyList;
    }
    public class ViewHolder {
        ImageView ImageView;
        TextView TextView1;
        TextView TextView2;
    }
    @Override
    public int getCount() {
        return VolleyList.size();
    }
    @Override
    public Object getItem(int location) {
        return VolleyList.get(location);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        holder = new ViewHolder();
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.volley_listview_item, null);
        holder.ImageView = (ImageView) convertView.findViewById(R.id.ImageView);
        holder.TextView1 = (TextView) convertView.findViewById(R.id.TextView1);
        holder.TextView2 = (TextView) convertView.findViewById(R.id.TextView2);
        CommonBean commonBean = VolleyList.get(position);
        holder.TextView1.setText(commonBean.getTextView1());
        holder.TextView2.setText("Rs."+commonBean.getTextView2());
        Glide.with(activity).load(commonBean.getImageView()).into(holder.ImageView);
        return convertView;
    }
}

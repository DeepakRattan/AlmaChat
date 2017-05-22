package com.almabay.almachat.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.almabay.almachat.R;
import com.almabay.almachat.beans.Bean_Group;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by deepakr on 2/29/2016.
 */
public class Adapter_Group_List extends BaseAdapter {
    private List<Bean_Group> listBeanGroups;
    private Context context;
    private LayoutInflater inflater;
    private Bean_Group bean_group;

    public Adapter_Group_List(Context context, List<Bean_Group> listBeanGroups) {
        this.context = context;
        this.listBeanGroups = listBeanGroups;
    }

    private class ViewHolder {
        ImageView imgGroup;
        TextView txtGroupName;
        TextView txtText;
        TextView txtTime;
    }

    @Override
    public int getCount() {
        return listBeanGroups.size();
    }

    @Override
    public Object getItem(int i) {
        return listBeanGroups.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            view = inflater.inflate(R.layout.feed_item_group_list, null);
            viewHolder = new ViewHolder();
            viewHolder.imgGroup = (ImageView) view.findViewById(R.id.img_group);

            viewHolder.txtGroupName = (TextView) view.findViewById(R.id.txtGroupName);
            viewHolder.txtText = (TextView) view.findViewById(R.id.txt);
            viewHolder.txtTime = (TextView) view.findViewById(R.id.txtTime);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        bean_group = listBeanGroups.get(i);
        //Getting values from Bean
        String image_url = bean_group.getUrl_group_image();
        Log.e("ImageGroup",image_url);
        String group_name = bean_group.getGroup_name();
        String group_text = bean_group.getGroup_text();
        String added_on = bean_group.getAdded_on();
        Log.e("AddedOn", added_on);

        //Setting values to the views
        Picasso.with(context).load(image_url).error(R.drawable.default_avatar).into(viewHolder.imgGroup);
        viewHolder.txtGroupName.setText(group_name);
        viewHolder.txtText.setText(group_text);
        //viewHolder.txtTime.setText(added_on);
        return view;
    }
}

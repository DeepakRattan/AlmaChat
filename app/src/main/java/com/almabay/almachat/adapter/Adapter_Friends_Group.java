package com.almabay.almachat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.almabay.almachat.R;
import com.almabay.almachat.apiConfiguration.ApiConfiguration;
import com.almabay.almachat.beans.Bean_Contacts;
import com.almabay.almachat.beans.Bean_Friends;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by deepakr on 2/25/2016.
 */
public class Adapter_Friends_Group extends BaseAdapter {
    private Context context;
    public List<Bean_Friends> listBeanFriends;
    private LayoutInflater inflater;
    private ApiConfiguration apiConfiguration;
    private Bean_Friends bean_friends;


    public Adapter_Friends_Group(Context context, List<Bean_Friends> listBeanFriends) {
        this.context = context;
        this.listBeanFriends = listBeanFriends;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView txtName;
        CheckBox chkFriend;
    }

    @Override
    public int getCount() {
        return listBeanFriends.size();
    }

    @Override
    public Object getItem(int i) {
        return listBeanFriends.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;
        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null) {
            view = inflater.inflate(R.layout.feed_item_friends, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) view.findViewById(R.id.friendsImage);
            viewHolder.txtName = (TextView) view.findViewById(R.id.nameFriends);
            viewHolder.chkFriend = (CheckBox) view.findViewById(R.id.chkFriends);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        bean_friends = listBeanFriends.get(i);
        String name = bean_friends.getName();
        String url = bean_friends.getUrl();
        String extension = bean_friends.getExtension();
        apiConfiguration = new ApiConfiguration();
        String api = apiConfiguration.getApi();
        String absolute_url = api + "/" + url + "." + extension;

        //loading image into ImageView                                                                                                                                            iew
        Picasso.with(context).load(absolute_url).error(R.drawable.default_avatar).into(viewHolder.imageView);
        //Setting name in the textview
        viewHolder.txtName.setText(name);
        //Setting boolean isSelected if the Checkbox is checked
        viewHolder.chkFriend.setChecked(bean_friends.isSelected());
        viewHolder.chkFriend.setTag(bean_friends);


        viewHolder.chkFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox cb = (CheckBox) view;
                Bean_Friends bean_friends = (Bean_Friends) cb.getTag();
                Toast.makeText(context, "Clicked on Checkbox: " + bean_friends.getName() + " is " + cb.isChecked(), Toast.LENGTH_LONG).show();
                //Setting the boolean value in bean as true if check box is clicked
                bean_friends.setIsSelected(cb.isChecked());
            }
        });
        return view;
    }
}

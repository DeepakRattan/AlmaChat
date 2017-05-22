package com.almabay.almachat.adapter;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.almabay.almachat.R;
import com.almabay.almachat.apiConfiguration.ApiConfiguration;
import com.almabay.almachat.beans.Bean_Friends;
import com.squareup.picasso.Picasso;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by deepakr on 2/5/2016.
 */
public class Adapter_FriendsList extends BaseAdapter {
    private Context context;
    private List<Bean_Friends> listBeanFriends;
    private LayoutInflater inflater;
    private ApiConfiguration apiConfiguration;

    public Adapter_FriendsList(Context context, List<Bean_Friends> listBeanFriends) {
        this.context = context;
        this.listBeanFriends = listBeanFriends;
    }

    @Override
    public int getCount() {
        return listBeanFriends.size();
    }

    @Override
    public Object getItem(int position) {
        return listBeanFriends.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null)
            view = inflater.inflate(R.layout.feed_item_chat_list, null);

        //Getting Views
        ImageView img_friend = (ImageView) view.findViewById(R.id.imgFriend);
        TextView txtNameFriend = (TextView) view.findViewById(R.id.txtNameFriend);
        ImageView imgMoreOption = (ImageView) view.findViewById(R.id.imgMoreoption);
        TextView txtFriendsID = (TextView) view.findViewById(R.id.txtFriendsID);

        imgMoreOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // hide the title bar
                dialog.setContentView(R.layout.more_option);
                dialog.show();
            }
        });

        //Setting values in the views
        //Data source of Adpater is List.At a specific position in the list ,we get Bean_Friends object
        Bean_Friends bean_friends = listBeanFriends.get(position);
        //Getting values from Bean_Friends
        String name = bean_friends.getName();
        Log.e("FriendNAme", name);
        String url = bean_friends.getUrl();
        String extension = bean_friends.getExtension();
        String friendID = bean_friends.getFriendsID();

        //Initializing ApiConfiguration
        apiConfiguration = new ApiConfiguration();
        String api = apiConfiguration.getApi();
        String absoluteURL = api + "/" + url + "." + extension;
        Log.e("AbsoluteURL", absoluteURL);

        Picasso.with(context).load(absoluteURL).error(R.drawable.default_avatar).into(img_friend); //Loading image into the circular Image view using Picasso
        txtNameFriend.setText(name);//Setting name
        txtFriendsID.setText(friendID);
        return view;
    }

    public static boolean exists(String URLName) {
        try {
            HttpURLConnection.setFollowRedirects(false);
            // note : you may also need
            //        HttpURLConnection.setInstanceFollowRedirects(false)
            HttpURLConnection con = (HttpURLConnection) new URL(URLName).openConnection();
            con.setRequestMethod("HEAD");
            return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

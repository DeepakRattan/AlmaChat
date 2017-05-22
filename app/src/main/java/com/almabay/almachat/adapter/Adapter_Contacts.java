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
import com.almabay.almachat.apiConfiguration.ApiConfiguration;
import com.almabay.almachat.beans.Bean_Contacts;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by deepakr on 2/19/2016.
 */
public class Adapter_Contacts extends BaseAdapter {
    private Context context;
    private List<Bean_Contacts> listContacts;
    private LayoutInflater inflater;
    private ApiConfiguration apiConfiguration;


    public Adapter_Contacts(Context context, List<Bean_Contacts> listContacts) {
        this.context = context;
        this.listContacts = listContacts;
    }


    @Override
    public int getCount() {
        return listContacts.size();
    }

    @Override
    public Object getItem(int i) {
        return listContacts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null)
            view = inflater.inflate(R.layout.feed_item_contact_list, null);

            //Getting views
            ImageView img = (ImageView) view.findViewById(R.id.imgContact);
            TextView txtName = (TextView) view.findViewById(R.id.txtNam);
            TextView txtStatus = (TextView) view.findViewById(R.id.stats);

            Bean_Contacts bean_contacts = listContacts.get(i);
            String name = bean_contacts.getName();
            Log.e("NameAdapter",name);
            String url = bean_contacts.getUrl();
            Log.e("URLAdapter",url);
            String extension = bean_contacts.getExtension();
            String status = bean_contacts.getStatus();

            apiConfiguration = new ApiConfiguration();
            String api = apiConfiguration.getApi();
            String absoluteURL = api + "/" + url + "." + extension;
            Log.e("AbsoluteURLAdapter", absoluteURL);

            Picasso.with(context).load(absoluteURL).error(R.drawable.default_avatar).into(img); //Loading image into the circular Image view using Picasso
            txtName.setText(name);
            txtStatus.setText(status);

        return view;
    }
}

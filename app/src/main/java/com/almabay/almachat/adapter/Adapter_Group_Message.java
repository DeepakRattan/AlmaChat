package com.almabay.almachat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.almabay.almachat.R;
import com.almabay.almachat.beans.Bean_Group_Message;
import com.rockerhieu.emojicon.EmojiconTextView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by deepakr on 3/1/2016.
 */
public class Adapter_Group_Message extends BaseAdapter {
    private Context context;
    private List<Bean_Group_Message> list_bean_group_message;
    private LayoutInflater inflater;

    public Adapter_Group_Message(Context context, List<Bean_Group_Message> list_bean_group_message) {
        this.context = context;
        this.list_bean_group_message = list_bean_group_message;
    }


    @Override
    public int getCount() {
        return list_bean_group_message.size();
    }

    @Override
    public Object getItem(int i) {
        return list_bean_group_message.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Bean_Group_Message bean_group_message = list_bean_group_message.get(i);
        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Identifying the message owner
        if (bean_group_message.isSelf()) {
            view = inflater.inflate(R.layout.list_item_message_right,null);

            //Getting views
            EmojiconTextView txtMessage = (EmojiconTextView) view.findViewById(R.id.txtMsgRight);
            TextView txtTime = (TextView) view.findViewById(R.id.txtMsgTime);

            //Setting values
            txtMessage.setText(bean_group_message.getMessage());
            txtTime.setText(bean_group_message.getDate());

        }
        else {
            // message belongs to other person, load the left aligned layout
            view = inflater.inflate(R.layout.list_item_message_left, null);

            //Getting Views
            ImageView imageView = (ImageView) view.findViewById(R.id.imageFriend);
            EmojiconTextView txtMessage = (EmojiconTextView) view.findViewById(R.id.txtMesg);
            TextView txtTime = (TextView) view.findViewById(R.id.txtTime);

            //Setting values
           // Picasso.with(context).load(completeURLFriend).error(R.drawable.default_avatar).into(imageView);
            txtMessage.setText(bean_group_message.getMessage());
            txtTime.setText(bean_group_message.getDate());
        }


        return view;
    }
}

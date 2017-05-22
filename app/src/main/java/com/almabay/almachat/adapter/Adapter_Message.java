package com.almabay.almachat.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.almabay.almachat.R;
import com.almabay.almachat.beans.Bean_Message;
import com.rockerhieu.emojicon.EmojiconTextView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by deepakr on 2/6/2016.
 */
public class Adapter_Message extends BaseAdapter {
    private Context context;
    private List<Bean_Message> listBeanMessages;
    private LayoutInflater inflater;
    private String completeURLFriend;

    public Adapter_Message(Context context, List<Bean_Message> listBeanMessages, String completeURLFriend) {
        this.context = context;
        this.listBeanMessages = listBeanMessages;
        this.completeURLFriend = completeURLFriend;
    }

    @Override
    public int getCount() {
        return listBeanMessages.size();
    }

    @Override
    public Object getItem(int position) {
        return listBeanMessages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Bean_Message bean_message = listBeanMessages.get(position);
        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Identifying the message owner
        if (listBeanMessages.get(position).isSelf()) {
            // message belongs to you, so load the right aligned layout
            view = inflater.inflate(R.layout.list_item_message_right, null);

            //Getting views
            EmojiconTextView txtMessage = (EmojiconTextView) view.findViewById(R.id.txtMsgRight);
            TextView txtTime = (TextView) view.findViewById(R.id.txtMsgTime);

            //Setting values
            txtMessage.setText(bean_message.getMessage());
            txtTime.setText(bean_message.getTime());

        } else {
            // message belongs to other person, load the left aligned layout
            view = inflater.inflate(R.layout.list_item_message_left, null);

            //Getting Views
            ImageView imageView = (ImageView) view.findViewById(R.id.imageFriend);
            EmojiconTextView txtMessage = (EmojiconTextView) view.findViewById(R.id.txtMesg);
            TextView txtTime = (TextView) view.findViewById(R.id.txtTime);

            //Setting values
            Picasso.with(context).load(completeURLFriend).error(R.drawable.default_avatar).into(imageView);
            txtMessage.setText(bean_message.getMessage());
            txtTime.setText(bean_message.getTime());
        }
        return view;
    }
}

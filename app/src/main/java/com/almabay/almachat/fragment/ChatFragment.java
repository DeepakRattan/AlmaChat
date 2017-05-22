package com.almabay.almachat.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.almabay.almachat.HTTPRequestProcessor.HttpRequestProcessor;
import com.almabay.almachat.R;
import com.almabay.almachat.activity.SingleChatActivity;
import com.almabay.almachat.adapter.Adapter_FriendsList;
import com.almabay.almachat.apiConfiguration.ApiConfiguration;
import com.almabay.almachat.beans.Bean_Friends;
import com.almabay.almachat.networkConnectionDetector.NetworkConnectionDetector;
import com.almabay.almachat.sharedPreference.Prefs_Registration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deepakr on 2/4/2016.
 */
public class ChatFragment extends Fragment {
    private String api;
    private ApiConfiguration apiConfiguration;
    private SharedPreferences sharedPreferences;
    private String accessToken, uid, loggedInUserName;
    private String url_feed_friends_list;
    private HttpRequestProcessor httpRequestProcessor;
    private NetworkConnectionDetector networkConnectionDetector;
    private List<Bean_Friends> listBeanFriends;
    private Adapter_FriendsList adapter_friendsList;
    private ListView lvFriends;


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_chat, container, false);
        // findViewById
        lvFriends = (ListView) v.findViewById(R.id.lvFriends);
        //initialization
        listBeanFriends = new ArrayList<Bean_Friends>();
        adapter_friendsList = new Adapter_FriendsList(getActivity(), listBeanFriends);
        apiConfiguration = new ApiConfiguration();
        sharedPreferences = getActivity().getSharedPreferences(Prefs_Registration.prefsName, Context.MODE_PRIVATE);
        httpRequestProcessor = new HttpRequestProcessor();
        networkConnectionDetector = new NetworkConnectionDetector(getActivity());

        //Setting adapter in the list view
        lvFriends.setAdapter(adapter_friendsList);

        //Getting userID and access token form the shared preference
        uid = sharedPreferences.getString(Prefs_Registration.get_user_id, null);
        accessToken = sharedPreferences.getString(Prefs_Registration.get_access_token, null);
        loggedInUserName = sharedPreferences.getString(Prefs_Registration.get_user_name, null);

        //Base URL for getting friends list
        api = apiConfiguration.getApi();
        //Url for getting fr
        // iends list
        url_feed_friends_list = api + "webservice/listMyFriends?user_id=" + uid + "&access_token=" + accessToken;
        Log.e("URL friend list", url_feed_friends_list);

        if (networkConnectionDetector.isConnectedToInternet()) {
            new GetFriendsListTask().execute();
        } else {
            showAlertDialog(getActivity(), "Internet Connection", "Please Check your internet connection.You don't have internet Connection");
        }

        //On clicking the item of list view
        lvFriends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                /*String friendName = ((TextView) view.findViewById(R.id.txtNameFriend)).getText().toString();
                Log.e("Friends Name", friendName);
                String friendID = ((TextView) view.findViewById(R.id.txtFriendsID)).getText().toString();
                Log.e("friends ID", friendID);*/
                Log.e("Position", String.valueOf(position));
                String friendName = listBeanFriends.get(position).getName();
                Log.e("Friends Name", friendName);
                String friendID = listBeanFriends.get(position).getFriendsID();
                Log.e("Friends ID", friendID);
                String url = listBeanFriends.get(position).getUrl();
                String extension = listBeanFriends.get(position).getExtension();
                apiConfiguration = new ApiConfiguration();
                String api = apiConfiguration.getApi();
                String absoluteURL = api + url + "." + extension;
                Log.e("Absolute URL", absoluteURL);
                Intent intent = new Intent(getActivity(), SingleChatActivity.class);
                intent.putExtra("friendID", friendID);
                intent.putExtra("friendsName", friendName);
                intent.putExtra("absoluteURL", absoluteURL);
                intent.putExtra("loggedInUserName", loggedInUserName);
                startActivity(intent);
            }
        });



        return v;
    }

    //Getting friends listing
    public class GetFriendsListTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            String response = httpRequestProcessor.gETRequestProcessor(url_feed_friends_list);
            return response;
        }

        @Override
        protected void onPostExecute(String response) {
            Log.e("Response", response);
            if (response != null) {
                parseJsonFeed(response);
            }

        }
    }

    public void parseJsonFeed(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray detail = jsonObject.getJSONArray("detail");
            for (int i = 0; i < detail.length(); i++) {
                JSONObject object = detail.getJSONObject(i);
                // getting name,thumbnail url ,extension form jsonObject
                String name = object.getString("name");
                Log.e("Name", name);
                String url = object.getString("url");
                String extension = object.getString("extension");
                String friendsID = object.getString("ID");

                //Initializing Bean_Friends
                Bean_Friends bean_friends = new Bean_Friends();
                //Setting name,url,extension in the bean
                bean_friends.setName(name);
                bean_friends.setUrl(url);
                bean_friends.setExtension(extension);
                bean_friends.setFriendsID(friendsID);
                // adding Bean_Friends object to ListBeanFriends
                listBeanFriends.add(bean_friends);
            }
            adapter_friendsList.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void showAlertDialog(Context context, String title, String message) {

        AlertDialog dialog = new AlertDialog.Builder(context).create();
        //Setting Dialog Title
        dialog.setTitle(title);
        //Setting Dialog message
        dialog.setMessage(message);
        //Setting OK button
        dialog.setButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialog.show();
    }

}

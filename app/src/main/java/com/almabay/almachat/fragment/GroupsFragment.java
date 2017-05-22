package com.almabay.almachat.fragment;

import android.app.AlertDialog;
import android.content.Context;
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
import android.widget.Button;
import android.widget.ListView;

import com.almabay.almachat.HTTPRequestProcessor.HttpRequestProcessor;
import com.almabay.almachat.R;
import com.almabay.almachat.activity.GroupChatActivity;
import com.almabay.almachat.activity.NewGroupActivity;
import com.almabay.almachat.adapter.Adapter_Group_List;
import com.almabay.almachat.apiConfiguration.ApiConfiguration;
import com.almabay.almachat.beans.Bean_Group;
import com.almabay.almachat.sharedPreference.Prefs_Registration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deepakr on 2/4/2016.
 */
public class GroupsFragment extends Fragment {
    private Button btnNewGroup;
    private ListView lv_groups;
    private Adapter_Group_List adapter_group_list;
    private List<Bean_Group> listBeanGroup;
    private HttpRequestProcessor httpRequestProcessor;
    private String url_get_group_detail;
    private ApiConfiguration apiConfiguration;
    private String api, uID, accessToken;
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_groups, container, false);

        //findViewByID
        btnNewGroup = (Button) v.findViewById(R.id.newGroup);
        lv_groups = (ListView) v.findViewById(R.id.lv_groups);
        btnNewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), NewGroupActivity.class));
            }
        });


        //Initialization
        listBeanGroup = new ArrayList<Bean_Group>();
        adapter_group_list = new Adapter_Group_List(getActivity(), listBeanGroup);
        httpRequestProcessor = new HttpRequestProcessor();
        apiConfiguration = new ApiConfiguration();
        sharedPreferences = getActivity().getSharedPreferences(Prefs_Registration.prefsName, Context.MODE_PRIVATE);

        //Setting adapter to the list view
        lv_groups.setAdapter(adapter_group_list);

        //Getting userID and access token from shared preference
        uID = sharedPreferences.getString(Prefs_Registration.get_user_id, null);
        accessToken = sharedPreferences.getString(Prefs_Registration.get_access_token, null);

        //Getting api end point
        api = apiConfiguration.getApi();

        //http://phpstack-11819-25991-62288.cloudwaysapps.com/webservice/getMyChatGroups?user_id=784&type=group&attribute=create&access_token=yrpRA9NI0dOp1EDjoiD9QSk59VmdVHUt9kkJt8stlgTnVxGjPjtGcNMlI8yEQt7Z&source=mobile_api
        url_get_group_detail = api + "/webservice/getMyChatGroups?user_id=" + uID + "&type=group&attribute=create&access_token=" + accessToken + "&source=mobile_api";
        Log.e("URL Group Detail",url_get_group_detail);

        new GetGroupDetailTask().execute();

        lv_groups.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bean_Group bean_group = listBeanGroup.get(i);
                String group_members = bean_group.getGroup_members();
                Log.e("Group_members", group_members);
                String group_name = bean_group.getGroup_name();
                String ownerID = bean_group.getOwner_id();
                String groupImage = bean_group.getUrl_group_image();
                String chat_group_id = bean_group.getChat_group_id();

                Intent intent = new Intent(getActivity(), GroupChatActivity.class);
                intent.putExtra("group_members", group_members);
                intent.putExtra("group_name", group_name);
                intent.putExtra("owner_id", ownerID);
                intent.putExtra("group_imege", groupImage);
                intent.putExtra("chat_group_id", chat_group_id);
                startActivity(intent);
            }
        });


        lv_groups.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater1 = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View dialogView = inflater1.inflate(R.layout.dialog_group_information, null);
                builder.setView(dialogView);
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });

        return v;


    }

    private class GetGroupDetailTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            String response = httpRequestProcessor.gETRequestProcessor(url_get_group_detail);
            return response;
        }

        @Override
        protected void onPostExecute(String response) {
            Log.e("Response = ", response);
            if (response != null)
                parseJSONFeed(response);
        }
    }

    public void parseJSONFeed(String response) {
        try {

            JSONObject jsonObject = new JSONObject(response);
            JSONArray detail = jsonObject.getJSONArray("detail");

            for (int i = 0; i < detail.length(); i++) {
                JSONObject jsonObject1 = detail.getJSONObject(i);
                String group_name = jsonObject1.getString("name");
                String chat_group_ID = jsonObject1.getString("chat_group_id");
                String group_members = jsonObject1.getString("members");
                String ownerID = jsonObject1.getString("owner");
                String added_on = jsonObject1.getString("added_on");
                String url_group_image = jsonObject1.getString("image_url");

                //Initializing Bean_Group
                Bean_Group bean_group = new Bean_Group();

                //Adding values to bean
                bean_group.setGroup_name(group_name);
                bean_group.setChat_group_id(chat_group_ID);
                bean_group.setGroup_members(group_members);
                bean_group.setOwner_id(ownerID);
                bean_group.setAdded_on(added_on);
                bean_group.setUrl_group_image(url_group_image);

                //Adding Bean object to List
                listBeanGroup.add(bean_group);
            }
            adapter_group_list.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}

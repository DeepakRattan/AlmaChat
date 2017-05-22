package com.almabay.almachat.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.almabay.almachat.HTTPRequestProcessor.HttpRequestProcessor;
import com.almabay.almachat.R;
import com.almabay.almachat.adapter.Adapter_Contacts;
import com.almabay.almachat.apiConfiguration.ApiConfiguration;
import com.almabay.almachat.beans.Bean_Contacts;
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
public class ContactsFragment extends Fragment {
    private Adapter_Contacts adapter_contacts;
    private List<Bean_Contacts> listBeanContacts;
    private ListView lv;
    private String url_feed_contact_list;
    private ApiConfiguration apiConfiguration;
    private SharedPreferences sharedPreferences;
    private String api, uid, accessToken;
    private HttpRequestProcessor httpRequestProcessor;
    private NetworkConnectionDetector networkConnectionDetector;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contacts, container, false);

        //findViewByID
        lv = (ListView) v.findViewById(R.id.listContacts);

        //Initialization
        listBeanContacts = new ArrayList<Bean_Contacts>();
        adapter_contacts = new Adapter_Contacts(getActivity(), listBeanContacts);
        apiConfiguration = new ApiConfiguration();
        sharedPreferences = getActivity().getSharedPreferences(Prefs_Registration.prefsName, Context.MODE_PRIVATE);
        httpRequestProcessor = new HttpRequestProcessor();
        networkConnectionDetector = new NetworkConnectionDetector(getActivity());

        //Setting Adapter in the ListView
        lv.setAdapter(adapter_contacts);
        api = apiConfiguration.getApi();
        uid = sharedPreferences.getString(Prefs_Registration.get_user_id, null);
        accessToken = sharedPreferences.getString(Prefs_Registration.get_access_token, null);

        url_feed_contact_list = api + "webservice/listMyFriends?user_id=" + uid + "&access_token=" + accessToken;

        if (networkConnectionDetector != null) {
            new getContactsTask().execute();
        }
        else {
            showAlertDialog(getActivity(), "Internet Connection", "Please Check your internet connection.You don't have internet Connection");
        }


        return v;
    }

    public class getContactsTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            String response = httpRequestProcessor.gETRequestProcessor(url_feed_contact_list);
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
                Log.e("NameContacts", name);
                String url = object.getString("url");
                Log.e("URLContacts",url);
                String extension = object.getString("extension");

                //Initializing Bean_Contacts
                Bean_Contacts bean_contacts = new Bean_Contacts();
                //Setting name,url,extension in the bean
                bean_contacts.setName(name);
                bean_contacts.setUrl(url);
                bean_contacts.setExtension(extension);
                // adding Bean_Contacts object to ListBeanContacts
                listBeanContacts.add(bean_contacts);
            }
            adapter_contacts.notifyDataSetChanged();

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

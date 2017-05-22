package com.almabay.almachat.gcm;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.almabay.almachat.HTTPRequestProcessor.HttpRequestProcessor;
import com.almabay.almachat.HTTPRequestProcessor.Response;
import com.almabay.almachat.R;
import com.almabay.almachat.apiConfiguration.ApiConfiguration;
import com.almabay.almachat.sharedPreference.Prefs_Registration;
import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by deepakr on 3/7/2016.
 */

//The RegistrationIntentService.java class is also where you put the code to send the registration information obtained from GCM to your app's server
public class RegistrationIntentService extends IntentService {

    private static final String TAG = "RegIntentService";
    private static final String[] TOPICS = {"global"};
    private String url_send_token_to_server;
    private HttpRequestProcessor httpRequestProcessor;
    private Response response;
    private String api;
    private ApiConfiguration apiConfiguration;
    private SharedPreferences sharedPreferences;

    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        try {
            // [START register_for_gcm]
            // Initially this call goes out to the network to retrieve the token, subsequent calls
            // are local.
            // [START get_token]
            InstanceID instanceID = InstanceID.getInstance(this);
            String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            // [END get_token]
            Log.e(TAG, "GCM Registration Token: " + token);

            // TODO: Implement this method to send any registration to your app's servers.
            sendRegistrationToServer(token);

            // Subscribe to topic channels
            subscribeTopics(token);

            // You should store a boolean that indicates whether the generated token has been
            // sent to your server. If the boolean is false, send the token to your server,
            // otherwise your server should have already received the token.
            sharedPreferences.edit().putBoolean(Config.SENT_TOKEN_TO_SERVER, true).apply();
            // [END register_for_gcm]
        } catch (Exception e) {
            Log.d(TAG, "Failed to complete token refresh", e);
            // If an exception happens while fetching the new token or updating our registration data
            // on a third-party server, this ensures that we'll attempt the update at a later time.
            sharedPreferences.edit().putBoolean(Config.SENT_TOKEN_TO_SERVER, false).apply();
        }
        // Notify UI that registration has completed
        Intent registrationComplete = new Intent(Config.REGISTRATION_COMPLETE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

    /**
     * Persist registration to third-party servers.
     * <p/>
     * Modify this method to associate the user's GCM registration token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) throws JSONException {
        // http://phpstack-11819-25991-62288.cloudwaysapps.com/webservice/gcm?user_id=784&access_token=yrpRA9NI0dOp1EDjoiD9QSk59VmdVHUt9kkJt8stlgTnVxGjPjtGcNMlI8yEQt7Z&source=mobile_api&source=mobile_api
        Log.e("SendToken", "To server Called");

        //Creating json form for token to be sent to server
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("gcm_token", token);
        String reg_token = jsonObject.toString();

        apiConfiguration = new ApiConfiguration();
        httpRequestProcessor = new HttpRequestProcessor();
        response = new Response();
        api = apiConfiguration.getApi();

        //Getting UserID and access_token from Shared preference
        sharedPreferences = getApplicationContext().getSharedPreferences(Prefs_Registration.prefsName, Context.MODE_PRIVATE);
        String access_token = sharedPreferences.getString(Prefs_Registration.get_access_token, null);
        String userID = sharedPreferences.getString(Prefs_Registration.get_user_id, null);
        url_send_token_to_server = api + "webservice/gcm/?user_id=" + userID + "&access_token=" + access_token + "&source=mobile_api";
        Log.e("URL_SEND_TOKEN", url_send_token_to_server);
        response = httpRequestProcessor.pOSTRequestProcessor(reg_token, url_send_token_to_server);
        String responseString = response.getJsonResponseString();
        Log.e("Response String", responseString);

        JSONObject object = new JSONObject(responseString);
        if(object.getString("statusCode").equals("200")){
            Log.e("Reg_Token","Sent successfully");
        }

    }

    /**
     * Subscribe to any GCM topics of interest, as defined by the TOPICS constant.
     *
     * @param token GCM token
     * @throws IOException if unable to reach the GCM PubSub service
     */
    // [START subscribe_topics]
    private void subscribeTopics(String token) throws IOException {
        GcmPubSub pubSub = GcmPubSub.getInstance(this);
        for (String topic : TOPICS) {
            pubSub.subscribe(token, "/topics/" + topic, null);
        }
    }
    // [END subscribe_topics]
}
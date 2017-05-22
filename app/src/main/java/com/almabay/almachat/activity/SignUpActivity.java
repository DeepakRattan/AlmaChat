package com.almabay.almachat.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.almabay.almachat.HTTPRequestProcessor.HttpRequestProcessor;
import com.almabay.almachat.HTTPRequestProcessor.Response;
import com.almabay.almachat.R;
import com.almabay.almachat.apiConfiguration.ApiConfiguration;
import com.almabay.almachat.networkConnectionDetector.NetworkConnectionDetector;
import com.almabay.almachat.sharedPreference.Prefs_Registration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by deepakr on 2/9/2016.
 */
public class SignUpActivity extends Activity {
    private String url_signUP;
    private EditText edtFirstName, edtLastName, edtEmailId, edtPassword;
    private String firstName, lastName, emailID, password;
    private Button btnJoin;
    private NetworkConnectionDetector networkConnectionDetector;
    private HttpRequestProcessor httpRequestProcessor;
    private ApiConfiguration apiConfiguration;
    private String baseURL;
    private Response response;
    private String jsonResponseString;
    private ProgressDialog progressDialog;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private TextView txtSignIn;
    private ImageView imgArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //findViewById
        edtFirstName = (EditText) findViewById(R.id.firstName);
        edtLastName = (EditText) findViewById(R.id.lastName);
        edtEmailId = (EditText) findViewById(R.id.email);
        edtPassword = (EditText) findViewById(R.id.password);
        btnJoin = (Button) findViewById(R.id.btnJoin);
        txtSignIn = (TextView) findViewById(R.id.signIn);
        imgArrow = (ImageView) findViewById(R.id.arrowSignUp);

        //Initialization
        networkConnectionDetector = new NetworkConnectionDetector(this);
        httpRequestProcessor = new HttpRequestProcessor();
        apiConfiguration = new ApiConfiguration();
        response = new Response();
        progressDialog = new ProgressDialog(this);
        sharedPreferences = getApplicationContext().getSharedPreferences(Prefs_Registration.prefsName, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


        baseURL = apiConfiguration.getApi();
        url_signUP = baseURL + "mobile_registration/register/";
        Log.e("URL_SignUp", url_signUP);


        //On clicking join button
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Checking internet connection
                if (networkConnectionDetector.isConnectedToInternet()) {
                    progressDialog.show();

                    //Geting values
                    firstName = edtFirstName.getText().toString().trim();
                    Log.e("FirstName", firstName);
                    lastName = edtLastName.getText().toString().trim();
                    Log.e("Last Name", lastName);
                    emailID = edtEmailId.getText().toString().trim();
                    Log.e("EmailID", emailID);
                    password = edtPassword.getText().toString().trim();
                    Log.e("Password", password);

                    new ResgitrationTask().execute(firstName, lastName, emailID, password);
                } else {
                    Toast.makeText(getApplicationContext(), "Please check tour internet connection.You are not connected to internet", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //On clicking Sign in button
        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });

        imgArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
            }
        });
    }

    private class ResgitrationTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            firstName = params[0];
            Log.e("FirstName", firstName);
            lastName = params[1];
            Log.e("Last Name", lastName);
            emailID = params[2];
            Log.e("EmailID", emailID);
            password = params[3];
            Log.e("Password", password);

            JSONObject jsonObject = new JSONObject();

            try {
                jsonObject.put("first_name", firstName);
                jsonObject.put("last_name", lastName);
                jsonObject.put("email", emailID);
                jsonObject.put("password", password);

                String jsonPostString = jsonObject.toString();
                Log.d("JSONPOSTString", jsonPostString);
                response = httpRequestProcessor.pOSTRequestProcessor(jsonPostString, url_signUP);
                jsonResponseString = response.getJsonResponseString();

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return jsonResponseString;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.e("Response String ", s);
            progressDialog.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(s);
                if (jsonObject.getString("statusCode").equals("201")) {

                    //Removing data from shared preference
                    editor.remove(Prefs_Registration.get_access_token);
                    editor.remove(Prefs_Registration.get_user_id);
                    editor.remove(Prefs_Registration.get_user_name);
                    editor.remove(Prefs_Registration.get_user_password);
                    editor.remove(Prefs_Registration.get_user_thumbnail);
                    editor.remove(Prefs_Registration.get_user_complete_url);
                    editor.commit();

                    String detail = jsonObject.getString("detail");
                    Toast.makeText(getApplicationContext(), detail, Toast.LENGTH_SHORT).show();
                    JSONArray response = jsonObject.getJSONArray("response");
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject object = (JSONObject) response.get(i);
                        String userID = object.getString("user_id");
                        Log.e("UserID", userID);
                        String accessToken = object.getString("access_token");
                        Log.e("AccessToken", accessToken);

                        //Adding userID and accessToken in the sharedPreference
                        editor.putString(Prefs_Registration.get_user_id, userID);
                        editor.putString(Prefs_Registration.get_access_token, accessToken);

                        //Username
                        String userName = firstName + " " + lastName;
                        Log.e("User Name", userName);
                        //Converting first lowercase letter of every word in uppercase
                        String userNameUpper = upperCase(userName);
                        //Storing User Name in shared Preference
                        editor.putString(Prefs_Registration.get_user_name, userNameUpper);
                        editor.commit();
                        startActivity(new Intent(SignUpActivity.this,WelcomeFragmentActivity.class));
                    }
                } else {
                    String detail = jsonObject.getString("detail");
                    Toast.makeText(getApplicationContext(), detail, Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        // Converting first lowercase letter of every word in Uppercase
        String upperCase(String source) {
            StringBuffer res = new StringBuffer();
            String[] strArr = source.split(" ");
            for (String str : strArr) {
                char[] stringArray = str.trim().toCharArray();
                stringArray[0] = Character.toUpperCase(stringArray[0]);
                str = new String(stringArray);
                res.append(str).append(" ");
            }
            return res.toString().trim();
        }
    }
}

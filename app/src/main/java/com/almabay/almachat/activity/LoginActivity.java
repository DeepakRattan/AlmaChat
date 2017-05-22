package com.almabay.almachat.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.almabay.almachat.R;
import com.almabay.almachat.apiConfiguration.ApiConfiguration;
import com.almabay.almachat.apiServiceInterface.APIServiceInterface;
import com.almabay.almachat.beans.Bean_LoginDetails;
import com.almabay.almachat.networkConnectionDetector.NetworkConnectionDetector;
import com.almabay.almachat.pojo.login_response.Avatar;
import com.almabay.almachat.pojo.login_response.Detail;
import com.almabay.almachat.pojo.login_response.ResponseLogin;
import com.almabay.almachat.pojo.login_response.User;
import com.almabay.almachat.pojo.login_response.UserInfo;
import com.almabay.almachat.pojo.login_response.UserInfo_;
import com.almabay.almachat.pojo.UserLogin;
import com.almabay.almachat.sharedPreference.Prefs_Registration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * Created by deepakr on 1/27/2016.
 */
public class LoginActivity extends Activity {
    EditText edtEmail, edtPasswd;
    Button btnLogin;
    String email, password;
    NetworkConnectionDetector networkConnectionDetector;
    ApiConfiguration apiConfiguration;
    ProgressDialog progressDialog;
    boolean userLoggedIn;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Bean_LoginDetails bean_loginDetails;
    private TextView txtSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //findViewByID
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPasswd = (EditText) findViewById(R.id.edtPasswd);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtSignUp = (TextView) findViewById(R.id.signUpLogin);

        //Initialization
        apiConfiguration = new ApiConfiguration();
        networkConnectionDetector = new NetworkConnectionDetector(getApplicationContext());
        progressDialog = new ProgressDialog(this);
        sharedPreferences = getApplicationContext().getSharedPreferences(Prefs_Registration.prefsName, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        //if the user is logged in
        if (sharedPreferences.getBoolean(Prefs_Registration.get_user_logged_in, false)) {
            startActivity(new Intent(LoginActivity.this, WelcomeActivity.class));
        }
        // else part will be executed when the user is logged out and shared preference only contains email id
        else {
            String email = sharedPreferences.getString(Prefs_Registration.get_user_email, null);
            edtEmail.setText(email);
        }
        /*//Displaying the density of the screen
        Log.e("Density", String.valueOf(getResources().getDisplayMetrics().density));
        int densityDpi1 = getResources().getDisplayMetrics().densityDpi;
        Log.e("DensityDpi", String.valueOf(densityDpi1));

        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        Log.e("Height", String.valueOf(screenHeight));
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        Log.e("Width", String.valueOf(screenWidth));
*/
        double screenSize = getScreenSize();
        Log.e("Screen Size", String.valueOf(screenSize));

        //On clicking login button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                // Checking whether the device is connected to internet or not
                if (networkConnectionDetector.isConnectedToInternet()) {
                    email = edtEmail.getText().toString().trim();
                    password = edtPasswd.getText().toString().trim();
                    if ((email == null)) {
                        if (password == null) {
                            Toast.makeText(getApplicationContext(), "Please enter emailID and password", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Please enter emailID", Toast.LENGTH_SHORT).show();
                        }
                    } else if (password == null) {
                        Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_SHORT).show();
                    }
                    //Validating the email id and password
                    if (!isValidEmail(email)) {
                        edtEmail.setError("Invalid email");
                    }
                    if (!isValidPassword(password)) {
                        edtPasswd.setError("Invalid Password");
                    }


                    //Using Retrofit 2.0 to POST data to server
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(apiConfiguration.getApi())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    APIServiceInterface postApi = retrofit.create(APIServiceInterface.class);
                    UserLogin userLogin = new UserLogin(email, password);
                    Call<ResponseLogin> call = postApi.login(userLogin);
                    call.enqueue(new Callback<ResponseLogin>() {
                        @Override
                        public void onResponse(Response<ResponseLogin> response) {
                            progressDialog.dismiss();
                            Log.e("Status Code", String.valueOf(response.code()));
                            if (response.code() == 200) {
                                //Empty the EditText
                                edtEmail.setText("");
                                edtPasswd.setText("");
                                //Getting response string
                                ResponseLogin responseLogin = response.body(); // ResponseLogin is a POJO correspoding to the response JSON string
                                Detail detail = responseLogin.getDetail();
                                String accessToken = detail.getAccessToken(); // Getting access token
                                Log.e("Access Token", accessToken);
                                String userID = detail.getUserId(); //Getting USerID
                                Log.e("User ID", userID);
                                UserInfo userInfo = detail.getUserInfo();
                                UserInfo_ info = userInfo.getUserInfo();
                                User user = info.getUser();
                                String name = user.getName(); // Getting username
                                Log.e("name", name);
                                String birthday = user.getBirthday();
                                Log.e("Birthday", birthday);
                                String firstName = user.getFirstName();
                                Log.e("FirstName", firstName);
                                String lastName = user.getLastName();
                                Log.e("Last Name", lastName);
                                String gender = user.getGender();
                                Log.e("Gender", gender);
                                String status = user.getStatus();
                                Log.e("Status", status);
                                String email = user.getEmail();
                                Log.e("Email", email);
                                String mobile = user.getMobile();
                                Log.e("Mobile",mobile);
                                Avatar avatar = user.getAvatar();
                                String completerURL = avatar.getCompleteUrl();
                                Log.e("Complete URL", completerURL);
                                //Setting userloggedIn true as the user is logged in
                                userLoggedIn = true;
                                // Saving data in the shared preference
                                save_value_session(email, password, userID, name, accessToken, completerURL, userLoggedIn, firstName, lastName, birthday, gender,mobile);
                                // Initializing Parcelable class
                                bean_loginDetails = new Bean_LoginDetails(userID, accessToken, name, completerURL);
                                Bundle bundle = new Bundle();
                                Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                                bundle.putParcelable("LoginInfo", bean_loginDetails);
                                intent.putExtras(bundle);
                                startActivity(intent);

                            } else {
                                Log.e("Status Code", String.valueOf(response.code()));
                                Toast.makeText(getApplicationContext(), "Please check your emailID and password ", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            progressDialog.dismiss();
                            Log.e("Response", String.valueOf(t));

                        }
                    });
                } else {
                    showAlertDialog(LoginActivity.this, "Internet Connection", "Please Check your Internet Connection .You don't have internet Connection");
                }
            }
        });

        //On clicking SignUp move to SignUpActivity
        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });

    }

    // finding Size and density of screen
    public double getScreenSize() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        double density1 = dm.density;
        Log.e("Density", String.valueOf(density1));
        int dens = dm.densityDpi;
        Log.e("DensityDpi", String.valueOf(dens));
        double wi = (double) width / (double) dens;
        double hi = (double) height / (double) dens;
        double x = Math.pow(wi, 2);
        double y = Math.pow(hi, 2);
        double screenInches = Math.sqrt(x + y);
        return screenInches;
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

    public void save_value_session(String email, String password, String id, String name, String accessToken, String completeURL, boolean userLoggedIn, String firstName, String lastName, String birthday, String gender,String mobile) {
        {
            editor.putString(Prefs_Registration.get_user_email, email);
            editor.putString(Prefs_Registration.get_user_password, password);
            editor.putString(Prefs_Registration.get_user_id, id);
            editor.putString(Prefs_Registration.get_user_name, name);
            editor.putString(Prefs_Registration.get_access_token, accessToken);
            editor.putString(Prefs_Registration.get_user_complete_url, completeURL);
            editor.putBoolean(Prefs_Registration.get_user_logged_in, userLoggedIn);
            editor.putString(Prefs_Registration.get_user_first_name, firstName);
            editor.putString(Prefs_Registration.get_user_lastName, lastName);
            editor.putString(Prefs_Registration.get_user_birthday, birthday);
            editor.putString(Prefs_Registration.get_user_gender, gender);
            editor.putString(Prefs_Registration.get_user_mobile,mobile);
            editor.commit();
        }

    }

    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);//  On clicking back button ,android application will be minimized
    }
}

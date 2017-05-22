package com.almabay.almachat.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.almabay.almachat.AndroidMultipartEntity.AndroidMultiPartEntity;
import com.almabay.almachat.R;
import com.almabay.almachat.apiConfiguration.ApiConfiguration;
import com.almabay.almachat.gcm.Config;
import com.almabay.almachat.gcm.RegistrationIntentService;
import com.almabay.almachat.sharedPreference.Prefs_Registration;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.squareup.picasso.Picasso;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

/**
 * Created by deepakr on 1/29/2016.
 */
public class WelcomeActivity extends Activity {
    ImageView imgProfilePic;
    TextView txtName;
    String name, thumbnailURL, completeURL;
    SharedPreferences preferences;
    ImageView iv_camera;
    String userID, accessToken;
    Bitmap bitmap = null;
    Uri fileUri;
    Button btnContinue;
    private ProgressBar progressBar;
    private TextView txtPercentage;
    long totalSize = 0;
    int val = 0;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private int RESULT_LOAD_IMAGE = 200;
    private Uri imagevalue;
    private String picturePath;
    private int photosize = 0;
    ApiConfiguration apiConfiguration;
    private String file_upload_url;
    private String base_url;
    private ProgressDialog progressDialog;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String TAG = WelcomeActivity.class.getSimpleName();
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    // public static String FILE_UPLOAD_URL = "http://phpstack-11819-25991-62288.cloudwaysapps.com/webservice/media?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //findViewById
        imgProfilePic = (ImageView) findViewById(R.id.profilePic);
        txtName = (TextView) findViewById(R.id.txtName);
        iv_camera = (ImageView) findViewById(R.id.iv_camera);
        btnContinue = (Button) findViewById(R.id.btnContinue);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        txtPercentage = (TextView) findViewById(R.id.txtPercentage);

        //initialization
        preferences = getApplicationContext().getSharedPreferences(Prefs_Registration.prefsName, Context.MODE_PRIVATE);
        apiConfiguration = new ApiConfiguration();
        progressDialog = new ProgressDialog(this);
        sharedPreferences = getApplicationContext().getSharedPreferences(Prefs_Registration.prefsName, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        //Getting values form Shared Preferences
        name = preferences.getString(Prefs_Registration.get_user_name, null);
        Log.e("Name", name);
        // String upperName = upperCase(name); //Converting first lowecase letter in uppercase
        //  Log.e("UpperName", upperName);
        // thumbnailURL = preferences.getString(Prefs_Registration.get_user_thumbnail, null);
        //Log.e("thumbnail URL", thumbnailURL);
        completeURL = preferences.getString(Prefs_Registration.get_user_complete_url, null);
        //Log.e("Complete URL", completeURL);
        userID = preferences.getString(Prefs_Registration.get_user_id, null);
        Log.e("UserID", userID);
        accessToken = preferences.getString(Prefs_Registration.get_access_token, null);
        Log.e("Access token", accessToken);

        //Getting BAse URL for uploading pic
        base_url = apiConfiguration.getApi();

        file_upload_url = base_url + "/webservice/media?" + "user_id=" + userID + "&type=photo&active=1&access_token=" + accessToken;
        //FILE_UPLOAD_URL = FILE_UPLOAD_URL + "user_id=479&type=photo&active=1&access_token=TwxqCJvgYfNeh6AeEZvjHWpjJMtH9va22ItErYVqeIEvOeKsegLWcbgVYJD7fOai";
        Log.e("File_Upload_URL", file_upload_url);

        //Complete URL will be null when the user is registering for the first time in almachat
        if (completeURL == null) {
            Log.e("Complete URL1", "is null");
            imgProfilePic.setImageResource(R.drawable.default_avatar);
        } else {
            Log.e("Complete URL1", completeURL);
            // Loading thumbnailURL into circular image view using Picasso
            Picasso.with(WelcomeActivity.this).load(completeURL).error(R.drawable.default_avatar).into(imgProfilePic);
        }
        txtName.setText(name);

        //On clicking Camera
        iv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Checking camera availability
                if (!isDeviceSupportCamera()) {
                    Toast.makeText(getApplicationContext(),
                            "Sorry! Your device doesn't support camera",
                            Toast.LENGTH_LONG).show();
                    // will close the app if the device does't have camera
                    finish();
                } else {
                    //cameraClick(view);
                    captureImage();
                }
            }
        });

        // On clicking Continue button
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fileUri != null) {
                    new UploadFileToServer().execute();
                } else {
                    startActivity(new Intent(WelcomeActivity.this, FriendsListActivity.class));
                }
               /* if (photosize == 0) {
                    new UploadFileToServer().execute();
                } else {
                    startActivity(new Intent(WelcomeActivity.this,FriendsListActivity.class));
                }*/
            }
        });

        // BroadcastReceiver will be called when GCM registration is completed
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                boolean sentToken = sharedPreferences.getBoolean(Config.SENT_TOKEN_TO_SERVER, false);
                if (sentToken) {
                    Log.e("Send Token", "Token retrieved and sent to server! You can now use gcmsender to\n" +
                            "        send downstream messages to this app.");
                } else {
                    Log.e("Send Token", "An error occurred while either fetching the InstanceID token,\n" + "sending the fetched token to the server or subscribing to the PubSub topic. Please try\n" + "running the sample again.");
                }
            }
        };

        if (checkPlayServices()) {
            // Start IntentService to register this application with GCM.
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }

    }//End of onCreate Method

    @Override
    protected void onResume() {
        super.onResume();
        // Register a receive for any local broadcasts that match the given IntentFilter.
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter(Config.REGISTRATION_COMPLETE));
    }

    @Override
    protected void onPause() {
        // Unregister a previously registered BroadcastReceiver.
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }


    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }

    // Upload Class
    private class UploadFileToServer extends AsyncTask<Void, Integer, String> {
        @Override
        protected void onPreExecute() {
            // setting progress bar to zero
            // progressBar.setProgress(0);
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            // Making progress bar visible
            //progressBar.setVisibility(View.VISIBLE);
            //Making progress dialog visible
            progressDialog.show();

            // updating progress bar value
            // progressBar.setProgress(progress[0]);

            // updating percentage value
            //txtPercentage.setText(String.valueOf(progress[0]) + "%");
        }

        @Override
        protected String doInBackground(Void... params) {
            return uploadFile();
        }

        @SuppressWarnings("deprecation")
        private String uploadFile() {
            Log.e("upload File", "called");
            String responseString = null;

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(file_upload_url);

            try {
                AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
                        new AndroidMultiPartEntity.ProgressListener() {

                            @Override
                            public void transferred(long num) {
                                publishProgress((int) ((num / (float) totalSize) * 100));
                            }
                        });


                // Adding file data to http body
                if (val == 0) {

                    Log.e("val = ", String.valueOf(val));
                    File sourceFile = new File(fileUri.getPath());
                    if (sourceFile != null) {
                        Log.e("SoureFile", String.valueOf(sourceFile));
                        photosize = 0;
                        entity.addPart("image", new FileBody(sourceFile));
                    } else {
                        photosize = 1;
                        Toast.makeText(WelcomeActivity.this, "Please Upload Your Image", Toast.LENGTH_LONG).show();
                    }


                } else {

                    File sourceFile = new File(picturePath);
                    if (sourceFile != null) {
                        photosize = 0;
                        entity.addPart("image", new FileBody(sourceFile));
                    } else {
                        photosize = 1;
                        Toast.makeText(WelcomeActivity.this, "Please Upload Your Image", Toast.LENGTH_LONG).show();
                    }

                }

                totalSize = entity.getContentLength();
                Log.e("Total Size", String.valueOf(totalSize));
                httppost.setEntity(entity);

                // Making server call
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity r_entity = response.getEntity();

                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    // Server response
                    responseString = EntityUtils.toString(r_entity);
                } else {
                    responseString = "Error occurred! Http Status Code: "
                            + statusCode;
                }

            } catch (ClientProtocolException e) {
                responseString = e.toString();
            } catch (IOException e) {
                responseString = e.toString();
            }

            return responseString;

        }

        @Override
        protected void onPostExecute(String result) {
            Log.e("UpLoad Server", "Response from server: " + result);
            try {
                JSONObject jsonObject = new JSONObject(result);
                String statusCode = jsonObject.getString("statusCode");
                if (statusCode.equals("200")) {
                    JSONObject detail = jsonObject.getJSONObject("detail");
                    String relativePath = detail.getString("relative_path");
                    JSONObject uploadData = detail.getJSONObject("upload_data");
                    String fileName = uploadData.getString("file_name");
                    //Getting Base URL
                    base_url = apiConfiguration.getApi();
                    completeURL = base_url + relativePath + "/" + fileName;
                    Log.e("Complete URl", completeURL);
                    //Entering the new value of complete url in shared preference
                    editor.putString(Prefs_Registration.get_user_complete_url, completeURL);
                    editor.commit();
                    startActivity(new Intent(WelcomeActivity.this, FriendsListActivity.class));
                } else
                    Toast.makeText(getApplicationContext(), "Image not uploaded", Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            progressDialog.dismiss();

            // showing the server response in an alert dialog
            //showAlert(result);

            super.onPostExecute(result);
        }

        /**
         * Method to show alert dialog
         */
        private void showAlert(String message) {
            AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);
            builder.setMessage(message)
                    .setTitle("Response from Servers")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // do nothing
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }

    }


    private void captureImage() {
        Log.e("Capture Image", "Called");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // creating Dir to save clicked Photo
        String root = Environment.getExternalStorageDirectory().toString();
        Log.e("root", root);
        String directory_path = root + "/cam_intent/";
        File myDir = new File(directory_path);
        myDir.mkdirs();
        // save clicked pic to given dir with given name
        File file = new File(myDir, "MyPhoto.jpg");
        fileUri = Uri.fromFile(file);
        Log.e("Uri of File", String.valueOf(fileUri));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }

    /**
     * Checking device has camera hardware or not
     */
    private boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.e("onSavedInstanceState", "called");
        super.onSaveInstanceState(outState);
        // save file url in bundle as it will be null on screen orientation
        // changes
        outState.putParcelable("file_uri", fileUri);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.e("OnRestoreInstanceState", "called");
        super.onRestoreInstanceState(savedInstanceState);
        //Get the file Uri
        fileUri = savedInstanceState.getParcelable("file_uri");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            launchUploadActivity(true);
        } /*else {
            if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK
                    && null != data) {
                val = 1;
                imagevalue = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(imagevalue, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                picturePath = cursor.getString(columnIndex);
                Log.e("File Path", picturePath);
                cursor.close();
                imgProfilePic.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            }
        }*/


    }

    private void launchUploadActivity(boolean isImage) {
        if (fileUri.getPath() != null) {
            // Displaying the image or video on the screen

            Log.e("Launch Upload Activity", "Called");
            previewMedia(isImage);
        } else {
            Toast.makeText(getApplicationContext(),
                    "Sorry, file path is missing!", Toast.LENGTH_LONG).show();
        }
    }

    private void previewMedia(boolean isImage) {
        // Checking whether captured media is image or video
        if (isImage) {
            // bimatp factory

            Log.e("Preview Media", "Called");
            BitmapFactory.Options options = new BitmapFactory.Options();

            // down sizing image as it throws OutOfMemory Exception for larger images
            options.inSampleSize = 8;

            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),
                    options);
            Log.e("DownSizing image", "Called");
            imgProfilePic.setImageBitmap(bitmap);

        }
    }

    private boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true); //  On clicking back button ,android application will be minimized
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Destroy", "------called--------");
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

} // End of Welcome Activity


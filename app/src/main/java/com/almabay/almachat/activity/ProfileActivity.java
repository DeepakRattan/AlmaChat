package com.almabay.almachat.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.almabay.almachat.AndroidMultipartEntity.AndroidMultiPartEntity;
import com.almabay.almachat.R;
import com.almabay.almachat.apiConfiguration.ApiConfiguration;
import com.almabay.almachat.sharedPreference.Prefs_Registration;

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
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by deepakr on 2/18/2016.
 */
public class ProfileActivity extends Activity {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String firstName, lastName, birthday, gender, email, mobile, name, completeURL;
    private TextView txtFirstName, txtLastName, txtBirthday, txtGender, txtEmail, txtMobile, txtName;
    private RelativeLayout relativeLayout;
    private ImageView imgCamera;
    private Uri fileUri;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private ApiConfiguration apiConfiguration;
    private String base_url, userID, accessToken, file_upload_url;
    private ProgressDialog progressDialog;
    private long totalSize = 0;
    private int val = 0;
    private int photosize = 0;
    private String picturePath;
    private ImageView imgArrow;
    private ImageView imgEditStatus, imgEditEmail, imgEditMobile;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //findViewByID
        txtFirstName = (TextView) findViewById(R.id.firstNameProfile);
        txtLastName = (TextView) findViewById(R.id.lastNameProfile);
        txtBirthday = (TextView) findViewById(R.id.birthday);
        txtGender = (TextView) findViewById(R.id.gender);
        txtEmail = (TextView) findViewById(R.id.emailProfile);
        txtMobile = (TextView) findViewById(R.id.mobile);
        txtName = (TextView) findViewById(R.id.userName);
        relativeLayout = (RelativeLayout) findViewById(R.id.rel);
        imgCamera = (ImageView) findViewById(R.id.cam);
        imgArrow = (ImageView) findViewById(R.id.arrowProfile);
        imgEditStatus = (ImageView) findViewById(R.id.imgEditStatus);
        imgEditEmail = (ImageView) findViewById(R.id.imgEditEmail);
        imgEditMobile = (ImageView) findViewById(R.id.imgEditPhone);


        //Initializing shared preference
        sharedPreferences = getApplicationContext().getSharedPreferences(Prefs_Registration.prefsName, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        firstName = sharedPreferences.getString(Prefs_Registration.get_user_first_name, null);
        String fName = upperCase(firstName);
        lastName = sharedPreferences.getString(Prefs_Registration.get_user_lastName, null);
        String lName = upperCase(lastName);
        birthday = sharedPreferences.getString(Prefs_Registration.get_user_birthday, null);
        gender = sharedPreferences.getString(Prefs_Registration.get_user_gender, null);
        String gendr = upperCase(gender);
        email = sharedPreferences.getString(Prefs_Registration.get_user_email, null);
        mobile = sharedPreferences.getString(Prefs_Registration.get_user_mobile, null);
        name = sharedPreferences.getString(Prefs_Registration.get_user_name, null);
        String nam = upperCase(name);
        completeURL = sharedPreferences.getString(Prefs_Registration.get_user_complete_url, null);
        Log.e("Complete URL", completeURL);

        accessToken = sharedPreferences.getString(Prefs_Registration.get_access_token, null);
        userID = sharedPreferences.getString(Prefs_Registration.get_user_id, null);

        apiConfiguration = new ApiConfiguration();
        base_url = apiConfiguration.getApi();
        file_upload_url = base_url + "/webservice/media?" + "user_id=" + userID + "&type=photo&active=1&access_token=" + accessToken;

        progressDialog = new ProgressDialog(ProfileActivity.this);
        //Setting the values
        txtFirstName.setText(fName);
        txtLastName.setText(lName);
        txtBirthday.setText(birthday);
        txtGender.setText(gendr);
        txtEmail.setText(email);
        txtMobile.setText(mobile);
        txtName.setText(nam);

        //Getting image from URL
        new GetBitmapFromURL().execute();

        //On clicking Camera
        imgCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Checking camera availability
                if (!isDeviceSupportCamera()) {
                    Toast.makeText(getApplicationContext(),
                            "Sorry! Your device doesn't support camera",
                            Toast.LENGTH_LONG).show();
                    // will close the app if the device doesn't have camera
                    finish();
                } else {
                    //cameraClick(view);
                    captureImage();
                }
            }
        });

        imgArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, FriendsListActivity.class));
            }
        });


        //On clicking edit status icon
        imgEditStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View dialogView = inflater.inflate(R.layout.dialog_edit_status, null);
                builder.setView(dialogView);
                final AlertDialog dialog = builder.create();
                dialog.show();

                Button btnOK = (Button) dialogView.findViewById(R.id.btnOK);
                btnOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "OK clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                Button btnCancel = (Button) dialogView.findViewById(R.id.btnCancel);
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
            }
        });

        //On Clicking Edit Email icon
        imgEditEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View dialogView = inflater.inflate(R.layout.dialog_edit_email, null);
                builder.setView(dialogView);
                final AlertDialog dialog = builder.create();
                dialog.show();

                Button btnOK = (Button) dialogView.findViewById(R.id.btnOK);
                btnOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "OK clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                Button btnCancel = (Button) dialogView.findViewById(R.id.btnCancel);
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });


            }
        });

        imgEditMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View dialogView = inflater.inflate(R.layout.dialog_edit_mobile, null);
                builder.setView(dialogView);
                final AlertDialog dialog = builder.create();
                dialog.show();

                Button btnOK = (Button) dialogView.findViewById(R.id.btnOK);
                btnOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "OK clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                Button btnCancel = (Button) dialogView.findViewById(R.id.btnCancel);
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });


            }
        });


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
        Log.e("onActivityResult", "Called");
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            launchUploadActivity(true);
        }
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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void previewMedia(boolean isImage) {
        // Checking whether captured media is image or video
        if (isImage) {
            Log.e("Preview Media", "Called");
           /* BitmapFactory.Options options = new BitmapFactory.Options();

            // down sizing image as it throws OutOfMemory Exception for larger images
            options.inSampleSize = 8;
            //options.inScaled = false;

            // Log.e("File URI", String.valueOf(fileUri));
            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(), options);
            int w = bitmap.getWidth();
            int h = bitmap.getHeight();
            int screen_width = getResources().getDisplayMetrics().widthPixels;
            float scaleX = (float) screen_width / w;
            float height = scaleX * h;
            android.view.ViewGroup.LayoutParams params = relativeLayout.getLayoutParams();
            params.height = (int) height;
            relativeLayout.setLayoutParams(params);*/

           /* File f = new File(getRealPathFromURI(fileUri));
            Drawable d = Drawable.createFromPath(f.getAbsolutePath());
            relativeLayout.setBackground(d);*/

            //Uploading image to server
            new UploadFileToServer().execute();
           /* Log.e("Uploading","Image Uploading........");
            Log.e("DownSizing image", "Called");
            Drawable dr = new BitmapDrawable(bitmap);
            Log.e("DrawableN", String.valueOf(dr));
            relativeLayout.setBackgroundDrawable(dr);*/
        }
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
                        Toast.makeText(ProfileActivity.this, "Please Upload Your Image", Toast.LENGTH_LONG).show();
                    }


                } else {

                    File sourceFile = new File(picturePath);
                    if (sourceFile != null) {
                        photosize = 0;
                        entity.addPart("image", new FileBody(sourceFile));
                    } else {
                        photosize = 1;
                        Toast.makeText(ProfileActivity.this, "Please Upload Your Image", Toast.LENGTH_LONG).show();
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
                    //editor.remove(Prefs_Registration.get_user_complete_url);
                    editor.putString(Prefs_Registration.get_user_complete_url, completeURL);
                    editor.commit();
                    new GetBitmapFromURL().execute();
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
            AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
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

    // Get Image from Thumbnail URL
    class GetBitmapFromURL extends AsyncTask<String, String, Bitmap> {
        Bitmap myBitmap;

        @Override
        protected Bitmap doInBackground(String... strings) {
            URL url = null;
            try {
                url = new URL(completeURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                myBitmap = BitmapFactory.decodeStream(input);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return myBitmap;

        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {

            Drawable dr = new BitmapDrawable(bitmap);
            Log.e("DrawableN", String.valueOf(dr));
            //Setting the image
            relativeLayout.setBackgroundDrawable(dr);
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

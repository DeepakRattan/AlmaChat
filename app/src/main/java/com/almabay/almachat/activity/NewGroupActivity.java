package com.almabay.almachat.activity;

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
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.almabay.almachat.AndroidMultipartEntity.AndroidMultiPartEntity;
import com.almabay.almachat.HTTPRequestProcessor.HttpRequestProcessor;
import com.almabay.almachat.R;
import com.almabay.almachat.adapter.Adapter_Friends_Group;
import com.almabay.almachat.apiConfiguration.ApiConfiguration;
import com.almabay.almachat.beans.Bean_Friends;
import com.almabay.almachat.networkConnectionDetector.NetworkConnectionDetector;
import com.almabay.almachat.sharedPreference.Prefs_Registration;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by deepakr on 2/15/2016.
 */
public class NewGroupActivity extends AppCompatActivity {
    private ImageView groupPic;
    private ListView lv_friends;
    private Adapter_Friends_Group adapter_friends_group;
    private ArrayList<String> arrayListFriendsID;

    private List<Bean_Friends> listBeanFriends;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String url_feed_contact_list, api, uid, accessToken;
    private ApiConfiguration apiConfiguration;
    private NetworkConnectionDetector networkConnectionDetector;
    private Toolbar toolbarNewGroup;
    private ImageView iv_camera_group, iv_group_pic;
    private PopupWindow popupWindow;
    private FrameLayout frameLayout;
    private Uri fileUri;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final int RESULT_LOAD_IMG = 200;
    private Bean_Friends bean_friends;
    String imgDecodableString;
    private ProgressDialog progressDialog;
    private String file_upload_url;
    private TextView txtCreate;
    private String url_create_new_group;
    private StringBuffer groupID = new StringBuffer();
    private EditText edtGroupName;
    private String groupName;
    long totalSize = 0;
    int val = 0;
    private int photosize = 0;
    private String base_url;
    String completeURL;
    private String picturePath;
    private HttpRequestProcessor httpRequestProcessor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);

        //findViewById
        lv_friends = (ListView) findViewById(R.id.lv_friends);
        toolbarNewGroup = (Toolbar) findViewById(R.id.toolBarNewGroup);
        txtCreate = (TextView) toolbarNewGroup.findViewById(R.id.txtCreate);
        iv_camera_group = (ImageView) findViewById(R.id.iv_camera_group);
        iv_group_pic = (ImageView) findViewById(R.id.groupPic);
        frameLayout = (FrameLayout) findViewById(R.id.frame);
        edtGroupName = (EditText) findViewById(R.id.edtGroupName);

        toolbarNewGroup.setTitle("New Group");
        setSupportActionBar(toolbarNewGroup);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Initialization
        listBeanFriends = new ArrayList<Bean_Friends>();
        adapter_friends_group = new Adapter_Friends_Group(getApplicationContext(), listBeanFriends);
        httpRequestProcessor = new HttpRequestProcessor();
        apiConfiguration = new ApiConfiguration();
        networkConnectionDetector = new NetworkConnectionDetector(getApplicationContext());
        sharedPreferences = getApplicationContext().getSharedPreferences(Prefs_Registration.prefsName, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        bean_friends = new Bean_Friends();
        progressDialog = new ProgressDialog(this);
        lv_friends.setAdapter(adapter_friends_group);

        //On clicking the item of list view
        lv_friends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Toast.makeText(NewGroupActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                Bean_Friends bean_friends = (Bean_Friends) adapterView.getItemAtPosition(pos);
                Toast.makeText(NewGroupActivity.this, bean_friends.getName() + "is selected", Toast.LENGTH_SHORT).show();
            }
        });
        api = apiConfiguration.getApi();

        //Getting userID and accessToken from shared preference
        uid = sharedPreferences.getString(Prefs_Registration.get_user_id, null);
        accessToken = sharedPreferences.getString(Prefs_Registration.get_access_token, null);

        url_feed_contact_list = api + "webservice/listMyFriends?user_id=" + uid + "&access_token=" + accessToken;
        if (networkConnectionDetector != null) {
            new getContactsTask().execute();
        } else {
            showAlertDialog(getApplicationContext(), "Internet Connection", "Please Check your internet connection.You don't have internet Connection");
        }

        //adding default pic to group
        iv_group_pic.setImageResource(R.drawable.default_avatar);

        //On clicking camera
        iv_camera_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();

                LinearLayout viewGroup = (LinearLayout) NewGroupActivity.this.findViewById(R.id.popup);
                LayoutInflater inflater = (LayoutInflater) NewGroupActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.popup_camera_click_group, viewGroup);
                popupWindow = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

                //Close the popup when touch outside
                popupWindow.setOutsideTouchable(true);
                popupWindow.setFocusable(true);
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                popupWindow.showAsDropDown(frameLayout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

                //Getting views of PopupWindow

                LinearLayout layout_gallery = (LinearLayout) layout.findViewById(R.id.layout_gallery);
                LinearLayout layout_camera = (LinearLayout) layout.findViewById(R.id.layout_camera);

                layout_gallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
                        loadImageFromGallery();
                    }
                });


                //On clicking camera
                layout_camera.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                        Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();

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

                //On clicking Gallery
                layout_gallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loadImageFromGallery();
                    }
                });


            }
        });

        //On clicking the create icon on the toolbar
        txtCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(NewGroupActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                createNewGroup();
            }
        });

        // http://phpstack-11819-25991-62288.cloudwaysapps.com/webservice/chatGroups?user_id=784&type=group&attribute=create&access_token=yrpRA9NI0dOp1EDjoiD9QSk59VmdVHUt9kkJt8stlgTnVxGjPjtGcNMlI8yEQt7Z&source=mobile_api&members=1771,491,476,481&group_name=lorem lipsam is test group&group_id=1


    }

    public void loadImageFromGallery() {
        popupWindow.dismiss();
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        String root = Environment.getExternalStorageDirectory().toString();
        Log.e("root", root);
        String directory_path = root + "/cam_intent/";
        File myDir = new File(directory_path);
        myDir.mkdirs();
        File file = new File(myDir, "MyPhoto.jpg");
        fileUri = Uri.fromFile(file);
        Log.e("Uri of File", String.valueOf(fileUri));
        galleryIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        // Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
                launchUploadActivity(true);
            } else if (requestCode == RESULT_LOAD_IMG) {
                Uri uri = data.getData();
                //File file = new File(getRealPathFromURI(NewGroupActivity.this,uri));
                //fileUri = Uri.fromFile(file);
                Log.e("FileUriImageGallery", String.valueOf(uri));
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                // Get the cursor
                Cursor cursor = getContentResolver().query(uri,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                ImageView imgView = (ImageView) findViewById(R.id.groupPic);
                // Set the Image in ImageView after decoding the String
                imgView.setImageBitmap(BitmapFactory
                        .decodeFile(imgDecodableString));
            }
        } else {
            Toast.makeText(this, "You haven't picked Image",
                    Toast.LENGTH_LONG).show();
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

    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
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
            iv_group_pic.setImageBitmap(bitmap);
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
                Log.e("URLContacts", url);
                String extension = object.getString("extension");
                String friendID = object.getString("ID");
                Log.e("FriendID", friendID);

                //Initializing Bean_Friends
                Bean_Friends bean_friends = new Bean_Friends();
                //Setting name,url,extension in the bean
                bean_friends.setName(name);
                bean_friends.setUrl(url);
                bean_friends.setExtension(extension);
                bean_friends.setFriendsID(friendID);

                // adding Bean_Friends object to ListBeanFriends
                listBeanFriends.add(bean_friends);
            }
            adapter_friends_group.notifyDataSetChanged();

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

    //Create new Group
    public void createNewGroup() {
        // Assigning the list inside adapter to list bean friends
        listBeanFriends = adapter_friends_group.listBeanFriends;
        //  Log.e("Size of adapter_friends", String.valueOf(adapter_friends_group.listBeanFriends.size()));
        Log.e("Size of listbeanFriends", String.valueOf(listBeanFriends.size()));
        arrayListFriendsID = new ArrayList<>();
        for (int i = 0; i < listBeanFriends.size(); i++) {
            Log.e("Loop Working", String.valueOf(i));
            Bean_Friends bean_friends = listBeanFriends.get(i);
            String friendID = bean_friends.getFriendsID();
            String friendName = bean_friends.getName();
            Log.e("FriendsName", friendName);
            Log.e("FriendID", friendID);
            Log.e("FriendSelected", String.valueOf(bean_friends.isSelected()));
            if (bean_friends.isSelected()) {
                // groupID.append("\n" + bean_friends.getName() + "  " + bean_friends.getFriendsID());
                String friendsID = bean_friends.getFriendsID();
                //Adding friendID to ArrayList
                arrayListFriendsID.add(friendID);
            }
        }


        //Accessing the elements of arraylist containing the id's of selected checkbox
       /* for (String s : arrayListFriendsID) {
            Log.e("ELement", s);
            groupID.append(s + ",");
        }*/

        //Deleting the elements of String Buffer groupID
        if (groupID != null)
            groupID.delete(0, groupID.length());
        //Accessing the elements of arraylist containing the id's of selected checkbox
        for (int i = 0; i < arrayListFriendsID.size(); i++) {
            String fID;
            //Clear the string buffer content
            // groupID.delete(0,groupID.length());
            if (i < arrayListFriendsID.size() - 1) {
                fID = arrayListFriendsID.get(i);
                groupID.append(fID + ",");
            } else {
                fID = arrayListFriendsID.get(i);
                groupID.append(fID);
            }
        }
        Log.e("GroupID", String.valueOf(groupID));
        groupName = edtGroupName.getText().toString().trim();
        // http://phpstack-11819-25991-62288.cloudwaysapps.com/webservice/chatGroups?user_id=784&type=group&attribute=create&access_token=yrpRA9NI0dOp1EDjoiD9QSk59VmdVHUt9kkJt8stlgTnVxGjPjtGcNMlI8yEQt7Z&source=mobile_api&members=1771,491,476,481&group_name=lorem lipsam is test group&group_id=1

        url_create_new_group = api + "webservice/chatGroups?user_id=" + uid + "&type=group&attribute=create&access_token=" + accessToken + "&source=mobile_api&members=" + groupID + "&group_name=" + groupName;
        Log.e("url_create_new_group", url_create_new_group);


        //Sorting the Arraylist containing ID's of friends
        //  Collections.sort(arrayListFriendsID);
       /* StringBuffer friendID = new StringBuffer();
        for (String s : arrayListFriendsID) {
            Log.e("ELement Sorted", s);
            friendID.append("," + s);
        }
        Log.e("Buffer", String.valueOf(friendID));*/
        //Appending logged_in_user_id to buffer
        // String combinedID = uid + friendID;
        //Log.e("ConcatenatedID", combinedID);
        // Encrypting the concatenated id to md5 string
        // String md5StringRoomID = convertPassMd5(combinedID); // Encrypting the combinedID to generate Room ID
        //Log.e("RoomID", md5StringRoomID);

        new UploadFileToServer().execute();

    }


    // encrypting string into MD5
   /* public static String convertPassMd5(String pass) {
        String password = null;
        MessageDigest mdEnc;
        try {
            mdEnc = MessageDigest.getInstance("MD5");
            mdEnc.update(pass.getBytes(), 0, pass.length());
            pass = new BigInteger(1, mdEnc.digest()).toString(16);
            while (pass.length() < 32) {
                pass = "0" + pass;
            }
            password = pass;
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }
        return password;
    }*/

    //Uploading image to server
    private class UploadFileToServer extends AsyncTask<Void, Integer, String> {
        @Override
        protected void onPreExecute() {
            // setting progress bar to zero
            // progressBar.setProgress(0);
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {

            //Making progress dialog visible
            progressDialog.show();
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
            Log.e("URL", url_create_new_group);
            HttpPost httppost = new HttpPost(url_create_new_group);

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
                    if (fileUri != null) {
                        Log.e("val = ", String.valueOf(val));
                        File sourceFile = new File(fileUri.getPath());
                        if (sourceFile != null) {
                            Log.e("SoureFile", String.valueOf(sourceFile));
                            photosize = 0;
                            entity.addPart("image", new FileBody(sourceFile));
                        } else {
                            photosize = 1;
                            Toast.makeText(NewGroupActivity.this, "Please Upload Your Image", Toast.LENGTH_LONG).show();
                        }
                    }


                } else {

                    File sourceFile = new File(picturePath);
                    if (sourceFile != null) {
                        photosize = 0;
                        entity.addPart("image", new FileBody(sourceFile));
                    } else {
                        photosize = 1;
                        Toast.makeText(NewGroupActivity.this, "Please Upload Your Image", Toast.LENGTH_LONG).show();
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

            progressDialog.dismiss();

            // showing the server response in an alert dialog
            //showAlert(result);

            super.onPostExecute(result);
        }

        /**
         * Method to show alert dialog
         */
        private void showAlert(String message) {
            AlertDialog.Builder builder = new AlertDialog.Builder(NewGroupActivity.this);
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

}
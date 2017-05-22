package com.almabay.almachat.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.ActionMode;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.almabay.almachat.Constants;
import com.almabay.almachat.HTTPRequestProcessor.HttpRequestProcessor;
import com.almabay.almachat.R;
import com.almabay.almachat.adapter.Adapter_Group_Message;
import com.almabay.almachat.apiConfiguration.ApiConfiguration;
import com.almabay.almachat.beans.Bean_Group_Message;
import com.almabay.almachat.networkConnectionDetector.NetworkConnectionDetector;
import com.almabay.almachat.sharedPreference.Prefs_Registration;
import com.rockerhieu.emojicon.EmojiconEditText;
import com.rockerhieu.emojicon.EmojiconGridFragment;
import com.rockerhieu.emojicon.EmojiconsFragment;
import com.rockerhieu.emojicon.emoji.Emojicon;

import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by deepakr on 2/29/2016.
 */
public class GroupChatActivity extends AppCompatActivity implements EmojiconGridFragment.OnEmojiconClickedListener, EmojiconsFragment.OnEmojiconBackspaceClickedListener {
    private Socket mSocket;
    private Toolbar toolbarGroupChat;
    private SharedPreferences sharedPreferences;
    private String roomID, message;
    private EmojiconEditText edMessage;
    private boolean showEmoji = false;
    private boolean hideEmoji;
    int deviceHeight;
    FrameLayout frameLayout;
    private Bean_Group_Message bean_group_message;
    private List<Bean_Group_Message> listBeanGroupMessage;
    private String md5LoggedInID;
    private boolean isSelf;
    private Adapter_Group_Message adapter_group_message;
    private String url_old_messages_group;
    private ApiConfiguration apiConfiguration;
    private HttpRequestProcessor httpRequestProcessor;
    private ProgressDialog progressDialog;
    private NetworkConnectionDetector networkConnectionDetector;
    private ListView lv_groupMessage;
    private PopupWindow popupWindow;

    // instance initialization block
    {
        try {
            mSocket = IO.socket(Constants.CHAT_SERVER_URL);
            Log.e("Socket", String.valueOf(mSocket));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);

        //FIndViewByID
        toolbarGroupChat = (Toolbar) findViewById(R.id.toolbarGroupChat);
        edMessage = (EmojiconEditText) findViewById(R.id.edtMessageGroup);
        lv_groupMessage = (ListView) findViewById(R.id.lv_group_message);


        //initialization
        sharedPreferences = getApplicationContext().getSharedPreferences(Prefs_Registration.prefsName, Context.MODE_PRIVATE);
        listBeanGroupMessage = new ArrayList<Bean_Group_Message>();
        adapter_group_message = new Adapter_Group_Message(GroupChatActivity.this, listBeanGroupMessage);
        apiConfiguration = new ApiConfiguration();
        httpRequestProcessor = new HttpRequestProcessor();
        progressDialog = new ProgressDialog(GroupChatActivity.this);
        networkConnectionDetector = new NetworkConnectionDetector(GroupChatActivity.this);

        lv_groupMessage.setAdapter(adapter_group_message);

        //inflate the popupwindow_attachment.xml
        LinearLayout viewGroup = (LinearLayout) GroupChatActivity.this.findViewById(R.id.popup_element);
        LayoutInflater inflater = (LayoutInflater) GroupChatActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.popupwindow_attachment, viewGroup);
        popupWindow = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        //Close the popup when touch outside
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Bundle bundle = getIntent().getExtras();
        String group_members = bundle.getString("group_members", null);
        String group_name = bundle.getString("group_name", null);
        String owner_id = bundle.getString("owner_id", null);
        String group_imge = bundle.getString("group_imege", null);
        String chat_group_id = bundle.getString("chat_group_id", null);
        Log.e("ChatGroupID", chat_group_id);

        //Adding title to the toolbar
        toolbarGroupChat.setTitle(group_name);
        setSupportActionBar(toolbarGroupChat);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String[] membersID = group_members.split(",");
        StringBuffer appendMembers = new StringBuffer();
        for (String s : membersID) {
            appendMembers.append(s);
        }
        Log.e("MembersID", String.valueOf(appendMembers));

        //Combining id of owner and membersID and group name
        String combinedID = owner_id + appendMembers + group_name;
        Log.e("Combined ID", combinedID);
        roomID = convertPassMd5(combinedID);
        Log.e("RoomID", roomID);

        //Load old messages in the chat room
        String api_message = apiConfiguration.getApi_message();
        url_old_messages_group = api_message + roomID;
        Log.e("URl old messages group", url_old_messages_group);

        //Load old messages of the group chat
        //Checking Internet Connection
        if (networkConnectionDetector.isConnectedToInternet()) {
            new GetOldMessages().execute();
        } else {
            showAlertDialog(GroupChatActivity.this, "Internet Connection", "Please Check your Internet Connection .You don't have internet Connection");
        }

        //Listening on Events
        mSocket.on(Socket.EVENT_CONNECT, onConnect);
        mSocket.on(Socket.EVENT_CONNECT_ERROR, onConnectionError);
        mSocket.on(Socket.EVENT_DISCONNECT, onDisconnect);
        mSocket.on("send:notice", onReceive); // Listening event for receiving messages
        mSocket.connect(); // Explicitly call connect method to establish connection here
        mSocket.emit("subscribe", roomID);

        // Getting the ID of logged in user and convert it into md5
        String loggedInUserID = sharedPreferences.getString(Prefs_Registration.get_user_id, null);
        md5LoggedInID = convertPassMd5(loggedInUserID);

        //Handling click event on drawableLeft and drawableRight inside  EmojiconEditText
        edMessage.setOnTouchListener(new View.OnTouchListener() {
                                         @Override
                                         public boolean onTouch(View view, MotionEvent event) {
                                             final int DRAWABLE_LEFT = 0;
                                             final int DRAWABLE_TOP = 1;
                                             final int DRAWABLE_RIGHT = 2;
                                             final int DRAWABLE_BOTTOM = 3;
                                             if (event.getAction() == MotionEvent.ACTION_UP) {
                                                 //Handling drawableRight
                                                 int leftEdgeOfRightDrawable = edMessage.getRight() - edMessage.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width();
                                                 //EditText has padding here.So adjust left edge
                                                 leftEdgeOfRightDrawable -= getResources().getDimension(R.dimen.padding10); //Here we have set the padding of 10 from left and right in edittext
                                                 if (event.getRawX() >= leftEdgeOfRightDrawable) {
                                                     // your action here
                                                     Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
                                                     message = edMessage.getText().toString().trim();


                                                     // Spannable s = getSmiledText(getApplicationContext(),message);
                                                     //  Log.e("Spannable", String.valueOf(s));
                                                     // Encoding emoji into unicode characters
                                                     String toServer = StringEscapeUtils.escapeJava(message);
                                                     //To find the current time in timestamp format
                                                     Date d = new Date();
                                                     final long time = d.getTime();
                                                     Log.e("Time", String.valueOf(time));
                                                     Log.e("To Server", toServer);
                                                     Log.e("Sending", "Sending data-----" + toServer);
                                                     if (!message.equals("")) {
                                                         edMessage.setText(" ");
                                                         JSONObject jsonObject = new JSONObject();
                                                         try {
                                                             jsonObject.put("room_id", roomID);
                                                             // jsonObject.put("user", loggedInUpper);
                                                             jsonObject.put("id", md5LoggedInID);
                                                             jsonObject.put("message", toServer);
                                                             jsonObject.put("date", time);
                                                             jsonObject.put("status", "sent");

                                                         } catch (JSONException e) {
                                                             e.printStackTrace();
                                                         }
                                                         // isSelf = true; // Boolean isSelf is set to be true as sender of the message is logged in user i.e. you
                                                         //attemptToSend(loggedInUpper, message, isSelf);
                                                         mSocket.emit("send", jsonObject); // owner i.e LoggedIn user is sending the message
                   /* msg = new Bean_Message();
                    msg.setMessageStatus(Status.SENT);
                    listBeanMessages.add(msg);
                    adapter.notifyDataSetChanged();*/
                                                     } else {
                                                         runOnUiThread(new Runnable() {
                                                             @Override
                                                             public void run() {
                                                                 Toast.makeText(getApplicationContext(), "Please enter some text", Toast.LENGTH_LONG).show();

                                                             }
                                                         });
                                                     }
                                                     return true;
                                                 }

                                                 //Handling drawable Left
                                                 if (event.getRawX() <= (edMessage.getCompoundDrawables()[DRAWABLE_LEFT].getBounds().width())) {
                                                     // your action here
                                                     Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
                                                     hideKeyboard();  // hiding the keyboard
                                                     showEmojiPopUp(!showEmoji);
                                                     return true;

                                                 }
                                             }
                                             return false;
                                         }
                                     }

        );

        // On clicking the edit text Emoji panel will be hidden
        edMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideEmoji = true;
                hideEmojiPopUp(hideEmoji);
                showKeyboard(edMessage);
            }
        });
        // Hiding soft keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setEmojiconFragment(false);

        //Disabling cut,copy and paste
        edMessage.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {

            }
        });
    }

    // This method will set a panel of emoticons in the fragment
    private void setEmojiconFragment(boolean useSystemDefault) {
        // Replacing the existing frame having id emojicons with the fragment of emoticons library containing emoticons
        getSupportFragmentManager().beginTransaction().replace(R.id.emojicons, EmojiconsFragment.newInstance(useSystemDefault)).commit();
    }

    // Hiding the keyboard
    public void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public void showEmojiPopUp(boolean showEmoji) {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        deviceHeight = size.y;
        Log.e("Device Height", String.valueOf(deviceHeight));
        frameLayout = (FrameLayout) findViewById(R.id.emojicons);
        frameLayout.getLayoutParams().height = (int) (deviceHeight / 2.5); // Setting the height of FrameLayout
        frameLayout.requestLayout();
        frameLayout.setVisibility(View.VISIBLE);
        hideKeyboard();
    }

    // Hiding the FrameLayout containing the list of Emoticons
    public void hideEmojiPopUp(boolean hideEmoji) {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.emojicons);
        frameLayout.setVisibility(View.GONE);
    }

    //Show the soft keyboard
    public void showKeyboard(EditText editText) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
        hideEmojiPopUp(true);
        //setHeightOfEmojiEditText();
    }

    // Event Listeners
    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.e("Socket", "Connected");
        }
    };


    private Emitter.Listener onConnectionError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.e("Error", "Error in connecting server");
        }
    };
    private Emitter.Listener onDisconnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.e("Disconnect", "Socket Disconnected");
        }
    };

    private Emitter.Listener onReceive = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    Log.e("DATA", String.valueOf(data));
                    try {
                        JSONArray ops = data.getJSONArray("ops");
                        for (int i = 0; i < ops.length(); i++) {
                            JSONObject object = ops.getJSONObject(i);
                            String room_id = object.getString("room_id"); // Getting room_id from json array
                            Log.e("room_id", room_id);
                            //Comparing the received room_id with the roomID generated here
                            if (roomID.equals(room_id)) {
                                String date = object.getString("date");
                                long time = Long.parseLong(date); //Converting String into long
                                String t = getDateFromTimestamp(time); // Getting date and time from timestamp
                                Log.e("DateReceived", date);
                                String unicodeMessageReceived = object.getString("message");
                                Log.e("UnicodeMessageReceived", unicodeMessageReceived);
                                //Decoding unicode characters for emoji
                                String messageReceived = StringEscapeUtils.unescapeJava(unicodeMessageReceived);
                                Log.e("DecodedMessageReceived", messageReceived);
                                // String loggedInUSerNAme = sharedPreferences.getString(Prefs_Registration.get_user_name, null);
                                String id = object.getString("id");
                                Log.e("ID", id);
                                Log.e("md5LoggedInID", md5LoggedInID);

                                //If the message is owned by the loggedInUser
                                if (id.equals(md5LoggedInID)) {
                                    isSelf = true;
                                    bean_group_message = new Bean_Group_Message();
                                    bean_group_message.setMessage(messageReceived);
                                    bean_group_message.setDate(t);
                                    bean_group_message.setIsSelf(isSelf);

                                    listBeanGroupMessage.add(bean_group_message);
                                    adapter_group_message.notifyDataSetChanged();
                                    playBeep();
                                } else {
                                    isSelf = false;
                                    bean_group_message = new Bean_Group_Message();
                                    bean_group_message.setMessage(messageReceived);
                                    bean_group_message.setDate(t);
                                    bean_group_message.setIsSelf(isSelf);

                                    listBeanGroupMessage.add(bean_group_message);
                                    adapter_group_message.notifyDataSetChanged();
                                    playBeep();
                                }
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };

    // Playing sound when the message is sent by other
    public void playBeep() {
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Converting timestamp to string
    private String getDateFromTimestamp(long time) {
        //String date = (String) DateFormat.format("dd-MM-yyyy hh:mm a", time);
        String date = (String) DateFormat.format("hh:mm a", time);
        return date;
    }


    // encrypting string into MD5
    public static String convertPassMd5(String pass) {
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
    }

    @Override
    public void onEmojiconBackspaceClicked(View view) {
        EmojiconsFragment.backspace(edMessage);
    }

    @Override
    public void onEmojiconClicked(Emojicon emojicon) {
        EmojiconsFragment.input(edMessage, emojicon);
    }

    //Get old messages of group chat
    public class GetOldMessages extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            // progressDialog.show();
            String response = httpRequestProcessor.gETRequestProcessor(url_old_messages_group);
            return response;
        }

        @Override
        protected void onPostExecute(String response) {
            // progressDialog.dismiss();
            try {
                JSONArray jsonArray = new JSONArray(response);
                parseJsonFeed(jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void parseJsonFeed(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String roomID_oldMessage = jsonObject.getString("room_id");
                Log.e("RoomIDOldMessages", roomID_oldMessage);
                Log.e("RoomID", roomID);
                if (roomID_oldMessage.equals(roomID)) {
                    Log.e("RoomIDs", "Are equal");
                    JSONObject id = jsonObject.getJSONObject("_id");
                    String md5IDSender = id.getString("$id");
                    Log.e("md5IDSender", md5IDSender);
                    if (md5IDSender.equals(md5LoggedInID)) {
                        Log.e("Message", "is owned by the loggedin user");
                        String message = jsonObject.getString("message");
                        Log.e("message", message);
                        String date = jsonObject.getString("date");
                        Log.e("Date", date);
                        String messageReceived = StringEscapeUtils.unescapeJava(message);
                        Long time = Long.parseLong(date);
                        String t = getDateFromTimestamp(time);
                        Log.e("TimeC", t);
                        Log.e("Date", date);
                        isSelf = true;
                        bean_group_message = new Bean_Group_Message();
                        bean_group_message.setMessage(messageReceived);
                        bean_group_message.setDate(t);
                        bean_group_message.setIsSelf(true);
                        listBeanGroupMessage.add(bean_group_message);
                        adapter_group_message.notifyDataSetChanged();
                    } else {
                        Log.e("Message", "is not owned by the loggedin user");
                        String message = jsonObject.getString("message");
                        Log.e("message", message);
                        String date = jsonObject.getString("date");
                        Log.e("Date", date);
                        String messageReceived = StringEscapeUtils.unescapeJava(message);
                        Long time = Long.parseLong(date);
                        String t = getDateFromTimestamp(time);
                        Log.e("TimeC", t);
                        Log.e("Date", date);
                        isSelf = false;
                        bean_group_message = new Bean_Group_Message();
                        bean_group_message.setMessage(messageReceived);
                        bean_group_message.setDate(t);
                        bean_group_message.setIsSelf(false);
                        listBeanGroupMessage.add(bean_group_message);
                        adapter_group_message.notifyDataSetChanged();
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.e("Option Menu", "called");
        menu.clear();
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_group_chat_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.groupInfo:
                return true;
            case R.id.viewContact:
                return true;
            case R.id.groupMedia:
                return true;
            case R.id.search:
                return true;
            case R.id.mute:
                return true;
            case R.id.emailchat:
                return true;
            case R.id.clear_chat:
                return true;
            case R.id.action_attach:
                initializePopUpWindow();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initializePopUpWindow() {
        //Placing the popup window below the toolbar
        popupWindow.showAsDropDown(toolbarGroupChat, 0, 0);
    }

}

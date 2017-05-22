package com.almabay.almachat.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.almabay.almachat.R;
import com.almabay.almachat.sharedPreference.Prefs_Registration;

/**
 * Created by deepakr on 2/24/2016.
 */
public class MediaAutoDownloadActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private Toolbar toolbarMediaAutoDownload;
    private LinearLayout layout_mobile_data, layout_wiFi, layout_roaming;
    private TextView txtMobileData, txtWifi, txtRoaming;
    private String mobileData, wifi, roaming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_auto_download);

        //findViewByID
        toolbarMediaAutoDownload = (Toolbar) findViewById(R.id.toolbarMediaAuto);
        layout_mobile_data = (LinearLayout) findViewById(R.id.layout_mobile_data);
        layout_wiFi = (LinearLayout) findViewById(R.id.layout_wiFi);
        layout_roaming = (LinearLayout) findViewById(R.id.layout_roaming);
        txtMobileData = (TextView) findViewById(R.id.txtMobileData);
        txtWifi = (TextView) findViewById(R.id.txtWiFi);
        txtRoaming = (TextView) findViewById(R.id.txtRoaming);


        toolbarMediaAutoDownload.setTitle("Media auto download");
        setSupportActionBar(toolbarMediaAutoDownload);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //getting values from shared preference which will be displayed under "When using mobile data"
        mobileData = getTextFromSP("mobileData");
        if (mobileData != null)
            txtMobileData.setText(mobileData);
        else
            txtMobileData.setText("No Media");

        //getting values from shared preferences which will be displayed under "When Connected on Wi-Fi"
        wifi = getTextFromSP("wifi");
        if (wifi != null)
            txtWifi.setText(wifi);
        else
            txtWifi.setText("No Media");

        // getting values from shared preferences which will be displayed under "Ehen Roaming"
        roaming = getTextFromSP("roaming");
        if (roaming != null)
            txtRoaming.setText(roaming);
        else
            txtRoaming.setText("No Media");

        //On clicking mobile data layout
        layout_mobile_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MediaAutoDownloadActivity.this);

                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View dialogView = inflater.inflate(R.layout.dialog_mobile_data, null);

                builder.setView(dialogView);

                final AlertDialog dialog = builder.create();
                dialog.show();

                //Getting view of Alert Dialog
                final CheckBox chkImages = (CheckBox) dialogView.findViewById(R.id.chkImages);
                final CheckBox chkAudio = (CheckBox) dialogView.findViewById(R.id.chkAudio);
                final CheckBox chkVideo = (CheckBox) dialogView.findViewById(R.id.chkVideo);
                TextView txtCancel = (TextView) dialogView.findViewById(R.id.txtCancel);
                TextView txtOK = (TextView) dialogView.findViewById(R.id.txtOK);

                //Setting check boxes as per value obtained from shared preference
                chkImages.setChecked(getFromSP("chkImages"));
                chkAudio.setChecked(getFromSP("chkAudio"));
                chkVideo.setChecked(getFromSP("chkVideo"));

                chkImages.setOnCheckedChangeListener(MediaAutoDownloadActivity.this);
                chkAudio.setOnCheckedChangeListener(MediaAutoDownloadActivity.this);
                chkVideo.setOnCheckedChangeListener(MediaAutoDownloadActivity.this);

                txtOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mobileData = " ";
                        if (chkImages.isChecked()) {
                            mobileData += "Images";
                        }
                        if (chkAudio.isChecked()) {
                            if (mobileData.equals(" ")) {
                                mobileData += "Audio";
                            } else
                                mobileData += "," + "Audio";
                        }
                        if (chkVideo.isChecked()) {
                            if (mobileData.equals(" ")) {
                                mobileData += "Video";
                            } else {
                                mobileData += "," + "Video";
                            }
                        }
                        if (!chkImages.isChecked() && !chkAudio.isChecked() && !chkVideo.isChecked()) {
                            chkImages.setChecked(false);
                            chkAudio.setChecked(false);
                            chkVideo.setChecked(false);
                            mobileData = "No Media";
                        }
                        saveTextinSP("mobileData", mobileData);
                        txtMobileData.setText(mobileData);
                        dialog.dismiss();

                    }
                });

                txtCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

        //On clicking layout of "When connected on Wi-Fi

        layout_wiFi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MediaAutoDownloadActivity.this);

                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View dialogView = inflater.inflate(R.layout.dialog_wifi_connection, null);

                builder.setView(dialogView);

                final AlertDialog dialog = builder.create();
                dialog.show();


                //Getting view of Alert Dialog
                final CheckBox chkImages1 = (CheckBox) dialogView.findViewById(R.id.chkImages1);
                final CheckBox chkAudio1 = (CheckBox) dialogView.findViewById(R.id.chkAudio1);
                final CheckBox chkVideo1 = (CheckBox) dialogView.findViewById(R.id.chkVideo1);
                TextView txtCancel1 = (TextView) dialogView.findViewById(R.id.txtCancel1);
                TextView txtOK1 = (TextView) dialogView.findViewById(R.id.txtOK1);

                //Setting check boxes as per value obtained from shared preference
                chkImages1.setChecked(getFromSP("chkImages1"));
                chkAudio1.setChecked(getFromSP("chkAudio1"));
                chkVideo1.setChecked(getFromSP("chkVideo1"));

                chkImages1.setOnCheckedChangeListener(MediaAutoDownloadActivity.this);
                chkAudio1.setOnCheckedChangeListener(MediaAutoDownloadActivity.this);
                chkVideo1.setOnCheckedChangeListener(MediaAutoDownloadActivity.this);

                txtOK1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        wifi = " ";
                        if (chkImages1.isChecked()) {
                            wifi += "Images";
                        }
                        if (chkAudio1.isChecked()) {
                            if (wifi.equals(" ")) {
                                wifi += "Audio";
                            } else
                                wifi += "," + "Audio";
                        }
                        if (chkVideo1.isChecked()) {
                            if (wifi.equals(" ")) {
                                wifi += "Video";
                            } else {
                                wifi += "," + "Video";
                            }
                        }
                        if (!chkImages1.isChecked() && !chkAudio1.isChecked() && !chkVideo1.isChecked()) {
                            chkImages1.setChecked(false);
                            chkAudio1.setChecked(false);
                            chkVideo1.setChecked(false);
                            wifi = "No Media";
                        }
                        saveTextinSP("wifi", wifi);
                        txtWifi.setText(wifi);
                        dialog.dismiss();

                    }
                });

                txtCancel1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

            }
        });

        //On clicking layout of "When roaming"
        layout_roaming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MediaAutoDownloadActivity.this);

                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View dialogView = inflater.inflate(R.layout.dialog_roaming, null);

                builder.setView(dialogView);

                final AlertDialog dialog = builder.create();
                dialog.show();

                //Getting view of Alert Dialog
                final CheckBox chkImages2 = (CheckBox) dialogView.findViewById(R.id.chkImages2);
                final CheckBox chkAudio2 = (CheckBox) dialogView.findViewById(R.id.chkAudio2);
                final CheckBox chkVideo2 = (CheckBox) dialogView.findViewById(R.id.chkVideo2);
                TextView txtCancel2 = (TextView) dialogView.findViewById(R.id.txtCancel2);
                TextView txtOK2 = (TextView) dialogView.findViewById(R.id.txtOK2);

                //Setting check boxes as per value obtained from shared preference
                chkImages2.setChecked(getFromSP("chkImages2"));
                chkAudio2.setChecked(getFromSP("chkAudio2"));
                chkVideo2.setChecked(getFromSP("chkVideo2"));

                chkImages2.setOnCheckedChangeListener(MediaAutoDownloadActivity.this);
                chkAudio2.setOnCheckedChangeListener(MediaAutoDownloadActivity.this);
                chkVideo2.setOnCheckedChangeListener(MediaAutoDownloadActivity.this);

                txtOK2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        roaming = " ";
                        if (chkImages2.isChecked()) {
                            roaming += "Images";
                        }
                        if (chkAudio2.isChecked()) {
                            if (roaming.equals(" ")) {
                                roaming += "Audio";
                            } else
                                roaming += "," + "Audio";
                        }
                        if (chkVideo2.isChecked()) {
                            if (roaming.equals(" ")) {
                                roaming += "Video";
                            } else {
                                roaming += "," + "Video";
                            }
                        }
                        if (!chkImages2.isChecked() && !chkAudio2.isChecked() && !chkVideo2.isChecked()) {
                            chkImages2.setChecked(false);
                            chkAudio2.setChecked(false);
                            chkVideo2.setChecked(false);
                            roaming = "No Media";
                        }
                        saveTextinSP("roaming", roaming);
                        txtRoaming.setText(roaming);
                        dialog.dismiss();
                    }
                });

                txtCancel2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

            }
        });


    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

        switch (compoundButton.getId()) {
            case R.id.chkImages:
                saveInSP("chkImages", isChecked);
                break;
            case R.id.chkAudio:
                saveInSP("chkAudio", isChecked);
                break;
            case R.id.chkVideo:
                saveInSP("chkVideo", isChecked);
                break;
            case R.id.chkImages1:
                saveInSP("chkImages1", isChecked);
                break;
            case R.id.chkAudio1:
                saveInSP("chkAudio1", isChecked);
                break;
            case R.id.chkVideo1:
                saveInSP("chkVideo1", isChecked);
                break;
            case R.id.chkImages2:
                saveInSP("chkImages2", isChecked);
                break;
            case R.id.chkAudio2:
                saveInSP("chkAudio2", isChecked);
                break;
            case R.id.chkVideo2:
                saveInSP("chkVideo2", isChecked);
                break;
        }
    }

    //Saving boolean value of check box in shared preference
    private void saveInSP(String key, boolean value) {
        SharedPreferences prefs = MediaAutoDownloadActivity.this.getSharedPreferences(Prefs_Registration.prefsName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    private boolean getFromSP(String key) {
        SharedPreferences prefs = MediaAutoDownloadActivity.this.getSharedPreferences(Prefs_Registration.prefsName, Context.MODE_PRIVATE);
        return prefs.getBoolean(key, false);
    }

    // Saving string value corresponding to check box selected in the shared preference
    private void saveTextinSP(String key, String value) {
        SharedPreferences preferences = MediaAutoDownloadActivity.this.getSharedPreferences(Prefs_Registration.prefsName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    private String getTextFromSP(String key) {
        SharedPreferences preferences = MediaAutoDownloadActivity.this.getSharedPreferences(Prefs_Registration.prefsName, Context.MODE_PRIVATE);
        return preferences.getString(key, null);
    }


}

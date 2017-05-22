package com.almabay.almachat.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.almabay.almachat.R;

import java.util.Random;

/**
 * Created by deepakr on 2/25/2016.
 */
public class ChatBackupActivity extends AppCompatActivity {
    private Toolbar toolbarChatBackup;
    private LinearLayout layout_backup_googleDrive, layout_backup_over;
    private RadioGroup radioGroupBackUpgoogleDrive;
    private TextView txtBackUpToGooogle, txtBackupOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_backup);

        //findViewByID
        toolbarChatBackup = (Toolbar) findViewById(R.id.toolbarChatBackup);
        layout_backup_googleDrive = (LinearLayout) findViewById(R.id.layout_backup_googleDrive);
        radioGroupBackUpgoogleDrive = (RadioGroup) findViewById(R.id.radioGroupBackUpGoogleDrive);
        txtBackUpToGooogle = (TextView) findViewById(R.id.backupToGoogle);
        layout_backup_over = (LinearLayout) findViewById(R.id.layout_backup_over);
        txtBackupOver = (TextView) findViewById(R.id.backupOver);

        toolbarChatBackup.setTitle("Chat backup");
        setSupportActionBar(toolbarChatBackup);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        layout_backup_googleDrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ChatBackupActivity.this);
                LayoutInflater inflater = (LayoutInflater) ChatBackupActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View dialogView = inflater.inflate(R.layout.dialog_backup_googledrive, null);
                builder.setView(dialogView);
                final AlertDialog dialog = builder.create();
                dialog.show();

                //Finding views of dialog
                radioGroupBackUpgoogleDrive = (RadioGroup) dialogView.findViewById(R.id.radioGroupBackUpGoogleDrive);
                RadioButton never = (RadioButton) dialogView.findViewById(R.id.radioNever);
                RadioButton tapBackUp = (RadioButton) dialogView.findViewById(R.id.radioTapBackUp);
                RadioButton daily = (RadioButton) dialogView.findViewById(R.id.radioDaily);
                RadioButton weekly = (RadioButton) dialogView.findViewById(R.id.radioWeekly);
                RadioButton monthly = (RadioButton) dialogView.findViewById(R.id.radioMonthly);
                Button btnCancel = (Button) dialogView.findViewById(R.id.btnCancelBackUp);


                radioGroupBackUpgoogleDrive.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {
                        switch (checkedID) {
                            case R.id.radioNever:
                                txtBackUpToGooogle.setText("Never");
                                break;
                            case R.id.radioTapBackUp:
                                txtBackUpToGooogle.setText("Only when i tap \"Back up to Google \" ");
                                break;
                            case R.id.radioDaily:
                                txtBackUpToGooogle.setText("Daily");
                                break;
                            case R.id.radioWeekly:
                                txtBackUpToGooogle.setText("Weekly");
                                break;
                            case R.id.radioMonthly:
                                txtBackUpToGooogle.setText("Monthly");
                                break;
                        }
                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

            }
        });

        layout_backup_over.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ChatBackupActivity.this);
                LayoutInflater inflater = (LayoutInflater) ChatBackupActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View dialogView = inflater.inflate(R.layout.dialog_backup_over, null);
                builder.setView(dialogView);
                final AlertDialog dialog = builder.create();
                dialog.show();

                //finding views of dialog view
                RadioGroup radioGroup = (RadioGroup) dialogView.findViewById(R.id.radioGroupBackOver);
                RadioButton wifi = (RadioButton) dialogView.findViewById(R.id.wifi);
                RadioButton wifi_cellular = (RadioButton) dialogView.findViewById(R.id.wifi_cellular);
                Button cancel = (Button) dialogView.findViewById(R.id.btnCancelBackUpOver);

                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {
                        switch (checkedID) {
                            case R.id.wifi:
                                txtBackupOver.setText("Wi-Fi");
                                break;
                            case R.id.wifi_cellular:
                                txtBackupOver.setText("Wi-Fi or cellular ");
                                break;
                        }

                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

            }
        });


    }
}

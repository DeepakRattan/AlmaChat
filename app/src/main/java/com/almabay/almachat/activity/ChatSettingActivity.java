package com.almabay.almachat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.almabay.almachat.R;

/**
 * Created by deepakr on 2/23/2016.
 */
public class ChatSettingActivity extends AppCompatActivity {
    private Toolbar toolbarChatSetting;
    private LinearLayout layout_mediaAutoDownload, layout_chatBackup, layout_chatHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_setting);

        //findViewByID
        toolbarChatSetting = (Toolbar) findViewById(R.id.toolbarChatSetting);
        layout_mediaAutoDownload = (LinearLayout) findViewById(R.id.layout_mediaAutoDownload);
        layout_chatBackup = (LinearLayout) findViewById(R.id.layout_chatBackup);
        layout_chatHistory = (LinearLayout) findViewById(R.id.layout_chatHistory);

        toolbarChatSetting.setTitle("Chat Setting");
        setSupportActionBar(toolbarChatSetting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //On clicking Chat History layout
        layout_chatHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChatSettingActivity.this, ChatHistoryActivity.class));
            }
        });

        //On clicking Media auto download layout
        layout_mediaAutoDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChatSettingActivity.this, MediaAutoDownloadActivity.class));
            }
        });

        //on clicking chat backup layout
        layout_chatBackup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChatSettingActivity.this, ChatBackupActivity.class));
            }
        });
    }
}

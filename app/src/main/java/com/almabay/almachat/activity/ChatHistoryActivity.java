package com.almabay.almachat.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.almabay.almachat.R;

import org.w3c.dom.Text;

/**
 * Created by deepakr on 2/23/2016.
 */
public class ChatHistoryActivity extends AppCompatActivity {
    private Toolbar toolbarChatHistory;
    private LinearLayout layout_email, layout_archive_all_chats, layout_clear_all_chats, layout_delete_all_chats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_history);

        //findViewByID
        toolbarChatHistory = (Toolbar) findViewById(R.id.toolbarChatHistory);
        layout_email = (LinearLayout) findViewById(R.id.layout_email_chat);
        layout_archive_all_chats = (LinearLayout) findViewById(R.id.layout_archive_all_chats);
        layout_clear_all_chats = (LinearLayout) findViewById(R.id.layout_clear_all_chat);
        layout_delete_all_chats = (LinearLayout) findViewById(R.id.layout_delete_all_chat);


        toolbarChatHistory.setTitle("Chat History");
        setSupportActionBar(toolbarChatHistory);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //On clicking clear all chats
        layout_clear_all_chats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ChatHistoryActivity.this);

                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View dialogView = inflater.inflate(R.layout.dialog_clear_all_chat, null);

                builder.setView(dialogView);
                final AlertDialog dialog = builder.create();
                dialog.show();

                //Find views in dialog
                TextView cancel = (TextView) dialog.findViewById(R.id.cancl);
                TextView clear = (TextView) findViewById(R.id.clear);

                //On clicking cancel button
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

        //On clicking archive all chats
        layout_archive_all_chats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ChatHistoryActivity.this);

                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View dialogView = inflater.inflate(R.layout.dialog_archive_all_chats, null);

                builder.setView(dialogView);
                final AlertDialog dialog = builder.create();
                dialog.show();

                //finding views of dialog
                TextView cancel = (TextView)dialog.findViewById(R.id.Cancl);
                TextView ok = (TextView)dialog.findViewById(R.id.OK);

                //On clicking cancel
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

            }
        });

        // On clicking delete all chats

        layout_delete_all_chats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ChatHistoryActivity.this);

                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View dialogView = inflater.inflate(R.layout.dialog_delete_all_chats, null);

                builder.setView(dialogView);
                final AlertDialog dialog = builder.create();
                dialog.show();

                //finding views of dialog
                TextView cancel = (TextView)dialog.findViewById(R.id.cancell);
                TextView ok = (TextView)dialog.findViewById(R.id.okk);

                //On clicking cancel
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

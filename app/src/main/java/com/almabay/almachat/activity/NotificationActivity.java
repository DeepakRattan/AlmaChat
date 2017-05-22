package com.almabay.almachat.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.almabay.almachat.R;

/**
 * Created by deepakr on 2/23/2016.
 */
public class NotificationActivity extends AppCompatActivity {
    private Toolbar toolbarNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        //findViewByID

        toolbarNotification = (Toolbar)findViewById(R.id.toolbarNotification);
        toolbarNotification.setTitle("Notification");
        setSupportActionBar(toolbarNotification);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}

package com.almabay.almachat.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.almabay.almachat.R;

/**
 * Created by deepakr on 2/9/2016.
 */
public class WelcomeOneActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_one);
    }

    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);//  On clicking back button ,android application will be minimized
    }
}

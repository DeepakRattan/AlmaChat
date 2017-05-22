package com.almabay.almachat.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.almabay.almachat.R;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread timer = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000); // Activity sleep for 2 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }
            }
        };
        timer.start();
    }
    // When the LoginActivity comes into foreground ,SplashActivity will be destroyed
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}

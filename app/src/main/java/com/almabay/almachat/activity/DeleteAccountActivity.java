package com.almabay.almachat.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.almabay.almachat.R;

/**
 * Created by deepakr on 2/23/2016.
 */
public class DeleteAccountActivity extends AppCompatActivity {
    private Toolbar toolbarDeleteAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);

        //findViewByID
        toolbarDeleteAccount = (Toolbar) findViewById(R.id.toolbarDeleteAccount);
        toolbarDeleteAccount.setTitle("Delete Account");
        setSupportActionBar(toolbarDeleteAccount);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}

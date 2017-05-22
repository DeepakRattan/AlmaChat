package com.almabay.almachat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.almabay.almachat.R;

import org.w3c.dom.Text;

/**
 * Created by deepakr on 2/22/2016.
 */
public class AccountActivity extends AppCompatActivity {
    private Toolbar toolbarAccount;
    private TextView txtPrivacy, txtDeleteAccount;
    private LinearLayout layout_privacy, layout_delete_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        //findViewById
        toolbarAccount = (Toolbar) findViewById(R.id.toolbarAccount);
        txtPrivacy = (TextView) findViewById(R.id.txtPrivacy);
        txtDeleteAccount = (TextView) findViewById(R.id.txtDeleteMyAccount);
        layout_privacy = (LinearLayout) findViewById(R.id.layout_privacy);
        layout_delete_account = (LinearLayout) findViewById(R.id.layout_delete_account);

        toolbarAccount.setTitle("Accounts");
        setSupportActionBar(toolbarAccount);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       /* //On clicking Privacy
        txtPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccountActivity.this, PrivacyActivity.class));
            }
        });*/

        //On clicking Privacy
        layout_privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccountActivity.this, PrivacyActivity.class));
            }
        });

        //On clicking delete my account

        layout_delete_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccountActivity.this, DeleteAccountActivity.class));
            }
        });
    }
}

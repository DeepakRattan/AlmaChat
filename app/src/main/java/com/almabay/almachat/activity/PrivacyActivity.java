package com.almabay.almachat.activity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.almabay.almachat.R;

import java.util.TooManyListenersException;

/**
 * Created by deepakr on 2/22/2016.
 */
public class PrivacyActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RelativeLayout relLastSeen, relProfilePhoto, relStatus;
    private RadioGroup radioGroupLastSeen;
    private LayoutInflater inflater;
    private RadioButton rdEveryone, rdMyContacts, rdNobody;
    private Button btnCancel;
    private AlertDialog.Builder builder;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);

        //findViewByID
        toolbar = (Toolbar) findViewById(R.id.toolbarPrivacy);
        relLastSeen = (RelativeLayout) findViewById(R.id.relLastSeen);
        relProfilePhoto = (RelativeLayout) findViewById(R.id.relProfilePhoto);
        relStatus = (RelativeLayout) findViewById(R.id.relStatus);

        toolbar.setTitle("Privacy");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        relLastSeen.setOnClickListener(new View.OnClickListener() {
                                           @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                                           @Override
                                           public void onClick(View view) {
                                               Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
                                               createDialogLastSeen();
                                           }
                                       }
        );

        relProfilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
                createDialogProfilePhoto();
            }
        });

        relStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialogStatus();
            }
        });
    }

    public void createDialogLastSeen() {

        Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();

        builder = new AlertDialog.Builder(PrivacyActivity.this);
        inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_last_seen, null);
        builder.setView(dialogView);
        builder.setTitle("Last Seen");
        dialog = builder.create();
        dialog.show();

        radioGroupLastSeen = (RadioGroup) dialogView.findViewById(R.id.radioGroupLastSeen);
        rdEveryone = (RadioButton) dialogView.findViewById(R.id.everyone);
        rdMyContacts = (RadioButton) dialogView.findViewById(R.id.myContacts);
        rdNobody = (RadioButton) dialogView.findViewById(R.id.nobody);
        btnCancel = (Button) dialogView.findViewById(R.id.cancel);

        int id = radioGroupLastSeen.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) radioGroupLastSeen.findViewById(id);
        Log.e("selected", radioButton.getText().toString());

        radioGroupLastSeen.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton checkedRadioButton = (RadioButton) radioGroupLastSeen.findViewById(i);
                boolean isChecked = checkedRadioButton.isChecked();
                if (isChecked) {
                    Log.e("Checked Button", checkedRadioButton.getText().toString());
                    Toast.makeText(getApplicationContext(), checkedRadioButton.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Clicked", "-------------");
                dialog.dismiss();
            }
        });

    }

    public void createDialogProfilePhoto() {

        Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();

        builder = new AlertDialog.Builder(PrivacyActivity.this);
        inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_last_seen, null);
        builder.setView(dialogView);
        builder.setTitle("Profile Photo");
        dialog = builder.create();
        dialog.show();

        radioGroupLastSeen = (RadioGroup) dialogView.findViewById(R.id.radioGroupLastSeen);
        rdEveryone = (RadioButton) dialogView.findViewById(R.id.everyone);
        rdMyContacts = (RadioButton) dialogView.findViewById(R.id.myContacts);
        rdNobody = (RadioButton) dialogView.findViewById(R.id.nobody);
        btnCancel = (Button) dialogView.findViewById(R.id.cancel);

        int id = radioGroupLastSeen.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) radioGroupLastSeen.findViewById(id);
        Log.e("selected", radioButton.getText().toString());

        radioGroupLastSeen.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton checkedRadioButton = (RadioButton) radioGroupLastSeen.findViewById(i);
                boolean isChecked = checkedRadioButton.isChecked();
                if (isChecked) {
                    Log.e("Checked Button", checkedRadioButton.getText().toString());
                    Toast.makeText(getApplicationContext(), checkedRadioButton.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Clicked", "-------------");
                dialog.dismiss();
            }
        });

    }

    public void createDialogStatus() {

        Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();

        builder = new AlertDialog.Builder(PrivacyActivity.this);
        inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_last_seen, null);
        builder.setView(dialogView);
        builder.setTitle("Status");
        dialog = builder.create();
        dialog.show();

        radioGroupLastSeen = (RadioGroup) dialogView.findViewById(R.id.radioGroupLastSeen);
        rdEveryone = (RadioButton) dialogView.findViewById(R.id.everyone);
        rdMyContacts = (RadioButton) dialogView.findViewById(R.id.myContacts);
        rdNobody = (RadioButton) dialogView.findViewById(R.id.nobody);
        btnCancel = (Button) dialogView.findViewById(R.id.cancel);

        int id = radioGroupLastSeen.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) radioGroupLastSeen.findViewById(id);
        Log.e("selected", radioButton.getText().toString());

        radioGroupLastSeen.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton checkedRadioButton = (RadioButton) radioGroupLastSeen.findViewById(i);
                boolean isChecked = checkedRadioButton.isChecked();
                if (isChecked) {
                    Log.e("Checked Button", checkedRadioButton.getText().toString());
                    Toast.makeText(getApplicationContext(), checkedRadioButton.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Clicked", "-------------");
                dialog.dismiss();
            }
        });

    }
}

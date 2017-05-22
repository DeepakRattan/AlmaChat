package com.almabay.almachat.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.almabay.almachat.R;
import com.almabay.almachat.adapter.PagerAdapter;
import com.almabay.almachat.sharedPreference.Prefs_Registration;
import com.squareup.picasso.Picasso;

/**
 * Created by deepakr on 2/4/2016.
 */
public class FriendsListActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private LayoutInflater inflater;
    private ImageView iv_profilePic;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private TextView txtUserName, txtEmailID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);

        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //TabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Chat"));
        tabLayout.addTab(tabLayout.newTab().setText("Groups"));
        tabLayout.addTab(tabLayout.newTab().setText("Contacts"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //ViewPager
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        //Initializing PagerAdapter
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //findViewByID
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        sharedPreferences = getApplicationContext().getSharedPreferences(Prefs_Registration.prefsName, Context.MODE_PRIVATE);

        //Initializing the Layout Inflater
        inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Inflating the navigation_drawer_header.xml file and finding different views
        View v = inflater.inflate(R.layout.navigation_drawer_header, null);
        iv_profilePic = (ImageView) v.findViewById(R.id.imgProfilePic);
        txtUserName = (TextView) v.findViewById(R.id.txtName);
        txtEmailID = (TextView) v.findViewById(R.id.txtEmailID);

        //Getting loggedIn user's complete URL,name and emalID from shared preference
        String completeURL = sharedPreferences.getString(Prefs_Registration.get_user_complete_url, null);
        String name = sharedPreferences.getString(Prefs_Registration.get_user_name, null);
        String emailID = sharedPreferences.getString(Prefs_Registration.get_user_email, null);

        //Setting the image url,user's name,user's emailID in the header of Navigation view
        Picasso.with(FriendsListActivity.this).load(completeURL).error(R.drawable.default_avatar).into(iv_profilePic);
        Drawable d = iv_profilePic.getDrawable();
        Log.e("Drawable", String.valueOf(d));
        txtUserName.setText(name);
        txtEmailID.setText(emailID);

        //Dynamically adding the header view to navigation view
        navigationView.addHeaderView(v);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked())
                    menuItem.setChecked(false);
                else
                    menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {

                    case R.id.profile:
                        Toast.makeText(getApplicationContext(), "Profile Selected", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(FriendsListActivity.this,ProfileActivity.class));
                        return true;
                    case R.id.accounts:
                        Toast.makeText(getApplicationContext(), "Account Selected", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(FriendsListActivity.this,AccountActivity.class));
                        return true;
                    case R.id.notification:
                        Toast.makeText(getApplicationContext(), "Notification Selected", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(FriendsListActivity.this,NotificationActivity.class));
                        return true;
                    case R.id.contacts:
                        Toast.makeText(getApplicationContext(), "Contact Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.chat:
                        Toast.makeText(getApplicationContext(), "Chat Selected", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(FriendsListActivity.this,ChatSettingActivity.class));
                        return true;
                    case R.id.help:
                        Toast.makeText(getApplicationContext(), "Help Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.logOut:
                        Toast.makeText(getApplicationContext(), "Logout Selected", Toast.LENGTH_SHORT).show();
                        editor = sharedPreferences.edit();
                        editor.remove(Prefs_Registration.get_user_password);
                        editor.remove(Prefs_Registration.get_user_name);
                        editor.remove(Prefs_Registration.get_user_id);
                        editor.remove(Prefs_Registration.get_user_complete_url);
                        editor.remove(Prefs_Registration.get_user_thumbnail);
                        editor.remove(Prefs_Registration.get_user_logged_in);

                        //Setting boolean get_user_logged_in as false as the user is logged out
                        editor.putBoolean(Prefs_Registration.get_user_logged_in, false);
                        editor.commit();

                        //Redirecting to Login Screen
                        startActivity(new Intent(FriendsListActivity.this,LoginActivity.class));
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
                        return true;
                }
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_friend_list_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(getApplicationContext(), "Setting clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_search:
                Toast.makeText(getApplicationContext(), "Search button clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}

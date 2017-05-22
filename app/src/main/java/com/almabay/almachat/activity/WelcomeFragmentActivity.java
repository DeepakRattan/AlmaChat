package com.almabay.almachat.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.almabay.almachat.R;
import com.almabay.almachat.adapter.PagerAdapterWelcome;
import com.almabay.almachat.fragment.WelcomeOneFragment;
import com.almabay.almachat.fragment.WelcomeThreeFragment;
import com.almabay.almachat.fragment.WelcomeTwoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deepakr on 2/12/2016.
 */
public class WelcomeFragmentActivity extends FragmentActivity {
    private List<Fragment> listFragments;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity_welcome);

        //FindViewByID
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        Button btnNext = (Button) findViewById(R.id.btnNext);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        final RadioButton radioBtnOne = (RadioButton) findViewById(R.id.radioBtnOne);
        final RadioButton radioBtnTwo = (RadioButton) findViewById(R.id.radioBtnTwo);
        final RadioButton radioBtnThree = (RadioButton) findViewById(R.id.radioBtnThree);

        //Initializing the List
        listFragments = new ArrayList<Fragment>();

        //initializing the fragments
        WelcomeOneFragment welcomeOneFragment = new WelcomeOneFragment();
        WelcomeTwoFragment welcomeTwoFragment = new WelcomeTwoFragment();
        WelcomeThreeFragment welcomeThreeFragment = new WelcomeThreeFragment();

        //Adding Fragments to List
        listFragments.add(welcomeOneFragment);
        listFragments.add(welcomeTwoFragment);
        listFragments.add(welcomeThreeFragment);

        //initializing PagerAdapterWelcome
        PagerAdapterWelcome pagerAdapterWelcome = new PagerAdapterWelcome(getSupportFragmentManager(), listFragments);
        viewPager.setAdapter(pagerAdapterWelcome);

        //On clicking next button move to next fragment
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Current position", String.valueOf(viewPager.getCurrentItem()));
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                // If view pager is displaying the 3rd fragment ,move to WelcomeActivity
                if (viewPager.getCurrentItem() == 2) {
                    Log.e("Curent position", String.valueOf(viewPager.getCurrentItem()));
                    startActivity(new Intent(WelcomeFragmentActivity.this, WelcomeActivity.class));
                }
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onPageSelected(int position) {
                radioGroup.check(radioGroup.getChildAt(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}

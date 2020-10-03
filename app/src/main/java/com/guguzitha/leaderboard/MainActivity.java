package com.guguzitha.leaderboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.zip.Inflater;

import static com.guguzitha.leaderboard.R.layout.actionbar;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private TabItem mLearning_tab;
    private TabItem mSkillIq_tab;
    private PagerAdapter mPagerAdapter;
    private Toolbar toolbar;
    private View mView;
    private LayoutInflater inflater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);


        Button button = (Button)findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();

            }

            private void openActivity() {
                Intent i = new Intent(MainActivity.this,
                        SubmissionActivity.class);
                startActivity(i);

            }
        });

        mTabLayout = findViewById(R.id.tabLayout);
        mLearning_tab = findViewById(R.id.learning_tab);
        mSkillIq_tab = findViewById(R.id.skillIq_tab);
        mViewPager = findViewById(R.id.viewpager);


        mPagerAdapter = new PagerAdapter(getSupportFragmentManager(), mTabLayout.
                getTabCount());

        mViewPager.setAdapter(mPagerAdapter);

        //change the tab view
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    mPagerAdapter.notifyDataSetChanged();
                } else if (tab.getPosition() == 1) {
                    mPagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

    }


}








package com.guguzitha.leaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private TabItem mLearning_tab;
    private TabItem mSkillIq_tab;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                if(tab.getPosition()== 0){
                    mPagerAdapter.notifyDataSetChanged();
                }else if (tab.getPosition() == 1){
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
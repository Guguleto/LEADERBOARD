package com.guguzitha.leaderboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.guguzitha.leaderboard.model.LearnersHours;
import com.guguzitha.leaderboard.services.LearningService;
import com.guguzitha.leaderboard.services.ServiceBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private TabItem mLearning_tab;
    private TabItem mSkillIq_tab;
    private PagerAdapter mPagerAdapter;
    private Adapter mAdapter;
    private RecyclerView listLearners;
    private MenuItem mSubmit;


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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_submit) {
            Intent intent = new Intent(MainActivity.this, SubmissionActivity.class);
            startActivity(intent);
            return true;

        }
        return onOptionsItemSelected(item);
    }
}








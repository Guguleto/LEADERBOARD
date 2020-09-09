package com.guguzitha.leaderboard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guguzitha.leaderboard.model.SkillIq;
import com.guguzitha.leaderboard.services.ServiceBuilder;
import com.guguzitha.leaderboard.services.SkillIqService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SkillIqListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private SkillIqAdapter mAdapter;
    View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_skill_iq_leader, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.skill_iq_recyclerView);

        fetchSkillIq();
    }

    private void fetchSkillIq() {
        //initialize service
        SkillIqService service = ServiceBuilder.createService(SkillIqService.class);
        //getData
        Call<List<SkillIq>> skillIq = service.getSkillIq();
        skillIq.enqueue(new Callback<List<SkillIq>>() {
            @Override
            public void onResponse(Call<List<SkillIq>> call, Response<List<SkillIq>> response) {
                Log.d("Gugu", "onResponse: loading data" + response.message());
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<SkillIq>> call, Throwable t) {

            }
        });
    }
    private void generateDataList(List<SkillIq> dataList){
        mRecyclerView = mView.findViewById(R.id.skill_iq_recyclerView);
        SkillIqAdapter skillIqAdapter = new SkillIqAdapter(getContext(),dataList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(skillIqAdapter);
    }
}
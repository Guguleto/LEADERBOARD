package com.guguzitha.leaderboard;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import com.guguzitha.leaderboard.model.LearnersHours;
import com.guguzitha.leaderboard.services.LearningService;
import com.guguzitha.leaderboard.services.ServiceBuilder;

import java.security.Provider;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.guguzitha.leaderboard.services.ServiceBuilder.createService;


public class LearnerHoursFragment extends Fragment {
    View mView;
    private RecyclerView listLearners;
    private LearnerAdapter mAdapter;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_learning_leader, container, false);

        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listLearners = view.findViewById(R.id.learners);
        fetchHours();
    }



    private void fetchHours() {
        //initialize service
        LearningService service = ServiceBuilder.createService(LearningService.class);
        //Get data
        Call<List<LearnersHours>> hours = service.getHours();
        hours.enqueue(new Callback<List<LearnersHours>>() {
            @Override
            public void onResponse(Call<List<LearnersHours>> call, Response<List<LearnersHours>> response) {
                Log.d("Gugu", "onResponse: loading data" + response.message());
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<LearnersHours>> call, Throwable t) {


            }
        });
    }
    private void generateDataList(List<LearnersHours> dataList){
        listLearners =mView.findViewById(R.id.learners);
        LearnerAdapter learnerAdapter = new LearnerAdapter(getContext(),dataList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        listLearners.setLayoutManager(layoutManager);
        listLearners.setAdapter(learnerAdapter);

    }
}
package com.guguzitha.leaderboard.services;



import com.guguzitha.leaderboard.model.LearnersHours;
import com.guguzitha.leaderboard.model.SkillIq;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LearningService {
    @GET("api/hours")
    Call<List<LearnersHours>> getHours();
}

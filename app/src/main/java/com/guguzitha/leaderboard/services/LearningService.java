package com.guguzitha.leaderboard.services;

import java.util.List;

import javax.xml.transform.Result;

import retrofit2.CallAdapter;
import retrofit2.http.GET;

public interface LearningService {
    @GET("/api/hours")
    List<Result> getAllData();
}

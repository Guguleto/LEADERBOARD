package com.guguzitha.leaderboard.services;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {
    private static final String URL = "https://gadsapi.herokuapp.com/";

    //Creating a logger
    private static HttpLoggingInterceptor logger = new HttpLoggingInterceptor().
            setLevel(HttpLoggingInterceptor.Level.BODY);

    //Create okhttp client
    private static OkHttpClient.Builder okHttp = new OkHttpClient.Builder().addInterceptor(logger);

    private static Retrofit.Builder sBuilder = new Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create());


    private static Retrofit sRetrofit = sBuilder.build();

    public static <S> S createService(Class<S> serviceClass) {
        return sRetrofit.create(serviceClass);
    }

}

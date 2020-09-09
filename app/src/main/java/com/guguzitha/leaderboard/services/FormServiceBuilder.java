package com.guguzitha.leaderboard.services;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FormServiceBuilder {
    private static final String URL = "https://docs.google.com/forms/d/e/";

    //Creating a logger
    private static HttpLoggingInterceptor logger = new HttpLoggingInterceptor().
            setLevel(HttpLoggingInterceptor.Level.BODY);

    //Create okhttp client
    private static OkHttpClient.Builder okHttp = new OkHttpClient.Builder().addInterceptor(logger);

    private static Retrofit.Builder sBuilder = new Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create());


    private static Retrofit sRetrofit = sBuilder.build();

    public static <service> service createService(Class<service> serviceClass) {
        return sRetrofit.create(serviceClass);
    }
}

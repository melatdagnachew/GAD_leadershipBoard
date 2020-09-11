package com.example.leaderboard.services;

import android.app.Application;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class googleFormService extends Application {


    private static String BASE_URL =  "https://docs.google.com/forms/d/e/";
    private static googleFormService mInstance;

    private static Retrofit retrofit;

    private static HttpLoggingInterceptor logger =
            new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    // Create OkHttp Client
    private static OkHttpClient.Builder okHttp =
            new OkHttpClient.Builder().addInterceptor(logger);


    public static Retrofit getConnection(){

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttp.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }

        return retrofit;

    }

    public static synchronized googleFormService getInstance(){

        if (mInstance == null){
            mInstance = new googleFormService();
        }
        return mInstance;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

}

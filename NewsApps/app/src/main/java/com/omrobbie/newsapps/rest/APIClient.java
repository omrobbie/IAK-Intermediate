package com.omrobbie.newsapps.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// TODO: (11) Buat file APIClient
public class APIClient {

    private static final String BASE_URL = "https://newsapi.org/v1/";
    private Retrofit mRetrofit;

    public Retrofit getmRetrofitClient() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
}

package com.omrobbie.newsapps.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// TODO: (11) Buat file APIClient
public class APIClient {

    private static final String BASE_URL = "http://newsapi.org/v1/";
    private static Retrofit mRetrofit;

    // TODO: (12) Deklarasikan retrofit instance
    public static Retrofit getmRetrofitClient() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
}

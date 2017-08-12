package com.omrobbie.newsapps.rest;

import com.omrobbie.newsapps.model.APIResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

// TODO: (13) Buat file APIService
public interface APIService {

    // TODO: (14) Daftar API Request GET
    @GET("articles")
    Call<APIResponse> getArticle(
            @Query("source") String source,
            @Query("apiKey") String apiKey
    );

}

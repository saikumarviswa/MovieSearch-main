package com.bvc.myapplication.network;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET("/")
    Call<JsonElement> getMovies(@Query("apikey") String apikey,@Query("s") String movie,@Query("type") String type);
}

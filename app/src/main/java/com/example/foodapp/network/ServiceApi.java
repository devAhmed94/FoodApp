package com.example.foodapp.network;

import com.example.foodapp.network.response.DetailsResponse;
import com.example.foodapp.network.response.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceApi {

    @GET("api/search")
    Call<SearchResponse> getSearchList(@Query("q") String query
                                      , @Query("page") int page);
    @GET("/api/get")
    Call<DetailsResponse> getDetailsRecipe(@Query("rId") String rId);
}


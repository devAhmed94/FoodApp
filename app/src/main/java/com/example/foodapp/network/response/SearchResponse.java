package com.example.foodapp.network.response;

import com.example.foodapp.model.SearchModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse  {
    @SerializedName("count")
    @Expose()
    private int count;
    @SerializedName("recipes")
    @Expose()
    private List<SearchModel>recipes;

    public int getCount() {
        return count;
    }

    public List<SearchModel> getRecipes() {
        return recipes;
    }

    @Override
    public String toString() {
        return "SearchResponse{" +
                "count=" + count +
                ", recipes=" + recipes +
                '}';
    }
}

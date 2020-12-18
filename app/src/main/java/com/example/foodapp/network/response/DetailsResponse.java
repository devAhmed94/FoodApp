package com.example.foodapp.network.response;

import com.example.foodapp.model.DetailsModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DetailsResponse {
    @SerializedName("recipe")
    @Expose()
    DetailsModel recipe;

    public DetailsModel getRecipe() {
        return recipe;
    }

    public void setRecipe(DetailsModel recipe) {
        this.recipe = recipe;
    }
}

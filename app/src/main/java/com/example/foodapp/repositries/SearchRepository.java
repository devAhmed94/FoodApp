package com.example.foodapp.repositries;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.foodapp.model.SearchModel;
import com.example.foodapp.network.ServiceClient;
import com.example.foodapp.network.response.SearchResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchRepository {
    private static SearchRepository repository;
    ServiceClient serviceClient;
    MutableLiveData<List<SearchModel>> mLiveSearchList  =new MutableLiveData<>();

    public SearchRepository() {
        serviceClient = ServiceClient.getInstance();

    }

    public static SearchRepository getInstance(){
        if (repository==null)
            repository = new SearchRepository();
        return repository;
    }

    public MutableLiveData<List<SearchModel>> get_mLiveSearchList() {
        if (mLiveSearchList.getValue()==null)
        Log.d("Aly", "onResponse: equal null ");
        return mLiveSearchList;
    }

    public void getSearchResponse(String query, int page){
        serviceClient.getSearchResponse(query, page).enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if(response.code()==200){
                    Log.d("Aly", "on success ");
                }

                List<SearchModel> recipes = ((SearchResponse) response.body()).getRecipes();
                if (recipes!=null)
                mLiveSearchList.setValue(recipes);
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Log.d("Aly", "onFailure: "+t.getMessage());
            }
        });

    }


}

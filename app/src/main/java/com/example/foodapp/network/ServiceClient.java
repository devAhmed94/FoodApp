package com.example.foodapp.network;

import com.example.foodapp.network.response.DetailsResponse;
import com.example.foodapp.network.response.SearchResponse;
import com.example.foodapp.util.Constants;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceClient {
    private static ServiceClient instanceClient;
    private  Retrofit retrofit;
    private ServiceApi serviceApi;
    private   ServiceClient()
    {
        retrofit = new Retrofit.Builder()
                   .baseUrl(Constants.BASE_URL)
                   .addConverterFactory(GsonConverterFactory.create())
                   .build();
        serviceApi = retrofit.create(ServiceApi.class);

    }

    public static ServiceClient getInstance(){
        if (instanceClient==null){
            instanceClient =new ServiceClient();
        }

        return instanceClient;
    }
    public Call<SearchResponse> getSearchResponse(String query, int page){
        return serviceApi.getSearchList(query, page);
    }

    public Call<DetailsResponse> getDetails(String rid){
        return serviceApi.getDetailsRecipe(rid);
    }

}

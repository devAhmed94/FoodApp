package com.example.foodapp.repositries;

import android.util.Log;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.foodapp.model.DetailsModel;
import com.example.foodapp.network.ServiceClient;
import com.example.foodapp.network.response.DetailsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsRepository {
    private static final String TAG = "DetailsRepository";
    private static DetailsRepository detailsRepository;
    private MutableLiveData<DetailsModel> dMlive = new MutableLiveData<>();

    public DetailsRepository() {

    }

    public static DetailsRepository getInstance(){
        if (detailsRepository==null){
            detailsRepository = new DetailsRepository();
        }
        return detailsRepository;
    }

    public MutableLiveData<DetailsModel> getdMlive() {

        if (dMlive.getValue() == null)
            Log.d("Ebaid", "onResponse: equal null ");
        return dMlive;
    }

    public void getDetails(String rid){
        ServiceClient.getInstance().getDetails(rid).enqueue(new Callback<DetailsResponse>() {
            @Override
            public void onResponse(Call<DetailsResponse> call, Response<DetailsResponse> response) {
                if (response.code()==200){
                    Log.d("Ebaid", " success ");
                }
                DetailsModel body = response.body().getRecipe();
                 if(body !=null) {
                     dMlive.postValue(body);

                 }
            }

            @Override
            public void onFailure(Call<DetailsResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }


}

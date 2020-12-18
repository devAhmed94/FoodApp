package com.example.foodapp.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodapp.model.CategoryModel;
import com.example.foodapp.model.DetailsModel;
import com.example.foodapp.model.SearchModel;
import com.example.foodapp.network.response.SearchResponse;
import com.example.foodapp.repositries.CategoryRepository;
import com.example.foodapp.repositries.DetailsRepository;
import com.example.foodapp.repositries.SearchRepository;

import java.util.List;

import retrofit2.Call;

public class FoodViewModel extends ViewModel {
    CategoryRepository categoryRepository;
    SearchRepository searchRepository;
    DetailsRepository detailsRepository;
    MutableLiveData<List<SearchModel>>  mutableLiveData = new MutableLiveData<>();
    MutableLiveData<DetailsModel>  detailLiveData = new MutableLiveData<>();

    public FoodViewModel() {
        this.categoryRepository = CategoryRepository.getInstance();
        this.searchRepository = SearchRepository.getInstance();
        this.detailsRepository = DetailsRepository.getInstance();

    }
    public LiveData<List<CategoryModel>> getListCategory(){
        return categoryRepository.get_mLiveDataCategory();
    }

    public MutableLiveData<List<SearchModel>> getMutableLiveData() {
      mutableLiveData = SearchRepository.getInstance().get_mLiveSearchList();
        return mutableLiveData;
    }

    public void getSearchList(String query, int page){
        searchRepository.getSearchResponse(query, page);
    }
    public MutableLiveData<DetailsModel> getDetailLiveData() {
        detailLiveData = DetailsRepository.getInstance().getdMlive();
        return detailLiveData;
    }
    public void getDetails(String rid){
        detailsRepository.getDetails(rid);
    }


}

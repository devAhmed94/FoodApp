package com.example.foodapp.repositries;

import androidx.lifecycle.MutableLiveData;

import com.example.foodapp.model.CategoryModel;
import com.example.foodapp.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {

    private static CategoryRepository instance;
    MutableLiveData<List<CategoryModel>>mLiveDataCategory ;

    public CategoryRepository() {
        this.mLiveDataCategory = new MutableLiveData<>();
    }

    public static CategoryRepository getInstance() {
        if (instance == null)
            instance = new CategoryRepository();
        return instance;
    }
   public MutableLiveData<List<CategoryModel>> get_mLiveDataCategory(){
        mLiveDataCategory.setValue(getListCategory());
        return mLiveDataCategory;
    }

    private List<CategoryModel> getListCategory() {
        List<CategoryModel> categoryModels = new ArrayList<>();
        for (int i = 0; i < Constants.DEFAULT_SEARCH_CATEGORIES.length; i++) {
            categoryModels.add(new CategoryModel(Constants.DEFAULT_SEARCH_CATEGORIES[i]
                    , Constants.DEFAULT_SEARCH_CATEGORY_IMAGES[i]));
        }
        //mLiveDataCategory.setValue(categoryModels);
        return categoryModels;
    }
}

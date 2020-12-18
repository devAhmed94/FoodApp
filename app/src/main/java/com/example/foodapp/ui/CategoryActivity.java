package com.example.foodapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.foodapp.R;
import com.example.foodapp.adapter.CategoryAdapter;
import com.example.foodapp.model.CategoryModel;
import com.example.foodapp.viewModel.FoodViewModel;

import java.util.List;

public class CategoryActivity extends AppCompatActivity implements CategoryAdapter.OnClickCategoryItem {

    SearchView searchView;
    RecyclerView recyclerView;
    FoodViewModel foodViewModel;
    CategoryAdapter categoryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        setSupportActionBar(findViewById(R.id.toolbar_category));
        searchView = findViewById(R.id.search_category);
        recyclerView = findViewById(R.id.recycler_category);
        initRecycler();
        foodViewModel = ViewModelProviders.of(this).get(FoodViewModel.class);

        foodViewModel.getListCategory().observe(this, new Observer<List<CategoryModel>>() {
            @Override
            public void onChanged(List<CategoryModel> categoryModels) {
                categoryAdapter.setCategoryModelList(categoryModels);
            }
        });
        initSearchView();
    }

    private void initSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(CategoryActivity.this,SearchListActivity.class);
                intent.putExtra("title",query);
                startActivity(intent);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void initRecycler() {

        categoryAdapter = new CategoryAdapter(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(categoryAdapter);
        categoryAdapter.setCategoryItemListener(this::onClickCategory);

    }

    @Override
    public void onClickCategory(CategoryModel model) {
        Intent intent = new Intent(CategoryActivity.this,SearchListActivity.class);
        intent.putExtra("categoryModel",model);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.category_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.category_menu){
            searchView.clearFocus();
        }
        return super.onOptionsItemSelected(item);
    }
}
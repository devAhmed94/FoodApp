package com.example.foodapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.adapter.SearchAdapter;
import com.example.foodapp.model.CategoryModel;
import com.example.foodapp.model.SearchModel;
import com.example.foodapp.network.response.SearchResponse;
import com.example.foodapp.viewModel.FoodViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchListActivity extends AppCompatActivity implements SearchAdapter.OnSearchItemListener {

    RecyclerView recyclerView;
    SearchView searchView;
    SearchAdapter searchAdapter;
    FoodViewModel foodViewModel;
    List<SearchModel> recipes;
    LiveData<List<SearchModel>> liveData = new MutableLiveData<>();
    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);
        recipes = new ArrayList<>();
        setSupportActionBar(findViewById(R.id.toolbar_searchList));
        recyclerView = findViewById(R.id.recycler_searchList);
        searchView = findViewById(R.id.search_searchList);
        CategoryModel categoryModel = (CategoryModel) getIntent().getExtras().get("categoryModel");
        if(categoryModel !=null){
        title = categoryModel.getTitle();
        }
        if(getIntent().getExtras().get("title") !=null) {
            title = getIntent().getExtras().getString("title");
        }
        foodViewModel = ViewModelProviders.of(this).get(FoodViewModel.class);
        liveData =foodViewModel.getMutableLiveData();

        foodViewModel.getSearchList(this.title, 1);
        initSearchView();
        initRecycler();

    }

    private void initSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                foodViewModel.getSearchList(query,1);
                searchAdapter.setSearchModelList(recipes);
                searchAdapter.notifyDataSetChanged();
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void initRecycler() {
        searchAdapter = new SearchAdapter(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(searchAdapter);
        searchAdapter.setSearchModelList(recipes);
        searchAdapter.setOnSearchItemListener(this);
        liveData.observe(this, new Observer<List<SearchModel>>() {
            @Override
            public void onChanged(List<SearchModel> searchResponse) {
                recipes = searchResponse;
                Log.d("Aly Data", "onChanged: "+recipes.get(0).getTitle());
                searchAdapter.setSearchModelList(recipes);
                searchAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.category_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.category_menu){
            startActivity(new Intent(SearchListActivity.this,CategoryActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClickSearchItem(SearchModel model) {
        Intent intent = new Intent(this,DetailsActivity.class);
        intent.putExtra("searchModel",model);
        startActivity(intent);
    }
}
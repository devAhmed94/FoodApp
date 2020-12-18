package com.example.foodapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodapp.R;
import com.example.foodapp.model.DetailsModel;
import com.example.foodapp.model.SearchModel;
import com.example.foodapp.viewModel.FoodViewModel;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    FoodViewModel foodViewModel;
    ImageView imageView;
    TextView title,gradient,score;
    LinearLayout gradient_container;
    DetailsModel model ;
    LiveData<DetailsModel> detailsActivityLiveData = new MutableLiveData<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initView();
        model = new DetailsModel();
        SearchModel searchModel = (SearchModel) getIntent().getExtras().get("searchModel");
        String recipe_id = searchModel.getRecipe_id();

        foodViewModel = ViewModelProviders.of(this).get(FoodViewModel.class);
        detailsActivityLiveData = foodViewModel.getDetailLiveData();

        foodViewModel.getDetails(recipe_id);

       // model =detailsActivityLiveData.getValue();
        detailsActivityLiveData.observe(this, new Observer<DetailsModel>() {
            @Override
            public void onChanged(DetailsModel detailsModel) {
                if(detailsModel !=null)
                 model = detailsModel;
                setVales(model);
            }
        });




    }



    private void initView() {
        imageView = findViewById(R.id.details_imageView);
        title = findViewById(R.id.details_title);
        gradient = findViewById(R.id.details_gradient);
        score = findViewById(R.id.details_score);
        gradient_container = findViewById(R.id.ingredients_container);
    }

    private void setVales(DetailsModel model) {

        if (model !=null) {
            Glide.with(this)
                    .load(model.getImage_url())
                    .apply(RequestOptions.centerCropTransform())
                    .into(imageView);
            title.setText(model.getTitle());
            gradient.setText("gradient");
            score.setText(String.valueOf(Math.round(model.getSocial_rank())));

            gradient_container.removeAllViews();
            for(String ingredient: model.getIngredients()){
                TextView textView = new TextView(this);
                textView.setText(ingredient);
                textView.setTextSize(15);
                textView.setLayoutParams(new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
                ));
                gradient_container.addView(textView);
            }

        }
    }
}
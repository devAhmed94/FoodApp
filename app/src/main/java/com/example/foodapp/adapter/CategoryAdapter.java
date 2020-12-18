package com.example.foodapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.R;
import com.example.foodapp.model.CategoryModel;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    List<CategoryModel> categoryModelList = new ArrayList<>();
    Context context;
    OnClickCategoryItem categoryItemListener;

    public CategoryAdapter( Context context) {
        this.context = context;
    }

    public void setCategoryModelList(List<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
        notifyDataSetChanged();
    }
    public void setCategoryItemListener(OnClickCategoryItem listener){
        this.categoryItemListener = listener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryItemListener.onClickCategory(categoryModelList.get(position));
            }
        });

        Uri path = Uri.parse("android.resource://com.example.foodapp/drawable/"+categoryModelList.get(position).getImage());
        Glide.with(context)
                .load(path)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.circleImageView);
        holder.title.setText(categoryModelList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder{

        CircleImageView circleImageView;
        TextView title;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.item_category_circleView);
            title = itemView.findViewById(R.id.item_category_title);
        }
    }

    public interface OnClickCategoryItem{
        void onClickCategory(CategoryModel model);
    }
}

package com.example.foodapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodapp.R;
import com.example.foodapp.model.SearchModel;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter  extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    List<SearchModel> searchModelList = new ArrayList<>();
    Context context;
    OnSearchItemListener onSearchItemListener;

    public SearchAdapter( Context context) {
        this.context = context;
    }
    public void setSearchModelList(List<SearchModel> searchModelList){
        this.searchModelList = searchModelList;
        notifyDataSetChanged();
    }

    public void setOnSearchItemListener(OnSearchItemListener onSearchItemListener) {
        this.onSearchItemListener = onSearchItemListener;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {


        Glide.with(context)
                .load(searchModelList.get(position).getImage_url())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.imageView);
        holder.title.setText(searchModelList.get(position).getTitle());
        holder.publish.setText(searchModelList.get(position).getPublisher());
        long score = Math.round(searchModelList.get(position).getSocial_rank());
        holder.score.setText(String.valueOf(score));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearchItemListener.onClickSearchItem(searchModelList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchModelList.size();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title,publish,score;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_search_imageView);
            title = itemView.findViewById(R.id.item_search_title);
            publish = itemView.findViewById(R.id.item_search_publisher);
            score = itemView.findViewById(R.id.item_search_score);
        }
    }

    public interface OnSearchItemListener{
        void onClickSearchItem(SearchModel model);
    }
}

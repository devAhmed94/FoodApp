package com.example.foodapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DetailsModel implements Parcelable {
    private String [] ingredients;
    private String image_url;
    private double social_rank;
    private String _id;
    private String publisher;
    private String source_url;
    private String recipe_id;
    private String publisher_url;
    private String title;

    public DetailsModel() {
    }

    public DetailsModel(String[] ingredients, String image_url, double social_rank,
                        String _id, String publisher, String source_url, String recipe_id, String publisher_url, String title) {
        this.ingredients = ingredients;
        this.image_url = image_url;
        this.social_rank = social_rank;
        this._id = _id;
        this.publisher = publisher;
        this.source_url = source_url;
        this.recipe_id = recipe_id;
        this.publisher_url = publisher_url;
        this.title = title;
    }

    protected DetailsModel(Parcel in) {
        ingredients = in.createStringArray();
        image_url = in.readString();
        social_rank = in.readDouble();
        _id = in.readString();
        publisher = in.readString();
        source_url = in.readString();
        recipe_id = in.readString();
        publisher_url = in.readString();
        title = in.readString();
    }

    public static final Creator<DetailsModel> CREATOR = new Creator<DetailsModel>() {
        @Override
        public DetailsModel createFromParcel(Parcel in) {
            return new DetailsModel(in);
        }

        @Override
        public DetailsModel[] newArray(int size) {
            return new DetailsModel[size];
        }
    };

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public double getSocial_rank() {
        return social_rank;
    }

    public void setSocial_rank(double social_rank) {
        this.social_rank = social_rank;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    public String getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(String recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getPublisher_url() {
        return publisher_url;
    }

    public void setPublisher_url(String publisher_url) {
        this.publisher_url = publisher_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(ingredients);
        dest.writeString(image_url);
        dest.writeDouble(social_rank);
        dest.writeString(_id);
        dest.writeString(publisher);
        dest.writeString(source_url);
        dest.writeString(recipe_id);
        dest.writeString(publisher_url);
        dest.writeString(title);
    }
}



package com.example.foodapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CategoryModel  implements Parcelable {
    private String title;
    private String image;

    public CategoryModel(String title, String image) {
        this.title = title;
        this.image = image;
    }

    protected CategoryModel(Parcel in) {
        title = in.readString();
        image = in.readString();
    }

    public static final Creator<CategoryModel> CREATOR = new Creator<CategoryModel>() {
        @Override
        public CategoryModel createFromParcel(Parcel in) {
            return new CategoryModel(in);
        }

        @Override
        public CategoryModel[] newArray(int size) {
            return new CategoryModel[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(image);
    }
}

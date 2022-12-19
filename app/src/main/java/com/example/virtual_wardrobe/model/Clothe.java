package com.example.virtual_wardrobe.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Clothe implements Parcelable {

    public String name;
    public String image;
    public String description;
    public String category;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.image);
        dest.writeString(this.description);
        dest.writeString(this.category);
    }

    public Clothe() {
    }

    protected Clothe(Parcel in) {
        this.name = in.readString();
        this.image = in.readString();
        this.description = in.readString();
        this.category = in.readString();
    }

    public static final Creator<Clothe> CREATOR = new Creator<Clothe>() {
        @Override
        public Clothe createFromParcel(Parcel source) {
            return new Clothe(source);
        }

        @Override
        public Clothe[] newArray(int size) {
            return new Clothe[size];
        }
    };
}

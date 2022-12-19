package com.example.virtual_wardrobe.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.virtual_wardrobe.screens.profile.ProfileType;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User implements Parcelable {
    @SerializedName("username")
    public String username;
    @SerializedName("email")
    public String mail;
    @SerializedName("password")
    public String password;

    public ProfileType type;


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.password);
        dest.writeString(this.mail);
    }

    public User(String mail, String username) {
        this.mail = mail;
        this.username = username;
    }

    public User() {
    }

    protected User(Parcel in) {
        this.username = in.readString();
        this.password = in.readString();
        this.mail = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}

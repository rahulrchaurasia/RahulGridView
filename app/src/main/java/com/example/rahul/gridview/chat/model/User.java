package com.example.rahul.gridview.chat.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rahul on 27/05/2019.
 */
public class User implements Parcelable {


   //Note : Default Constructor Required ,OtherWise Error come on Firbase Side
    private String id;
    private String username;
    private String imageUrl;

    public User(String id, String username, String imageUrl) {
        this.id = id;
        this.username = username;
        this.imageUrl = imageUrl;
    }

    public User() {       //no argument constructor
    }


    protected User(Parcel in) {
        id = in.readString();
        username = in.readString();
        imageUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(username);
        dest.writeString(imageUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }



}

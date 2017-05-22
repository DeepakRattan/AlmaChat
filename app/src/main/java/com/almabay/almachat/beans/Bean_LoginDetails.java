package com.almabay.almachat.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by deepakr on 1/28/2016.
 */
public class Bean_LoginDetails implements Parcelable {
    private String user_ID, access_token, user_name, thumbnail_url;

    // Constructor that initialises the Bean_LoginDetails object
    public Bean_LoginDetails(String user_ID, String access_token, String user_name, String thumbnail_url) {
        this.user_ID = user_ID;
        this.access_token = access_token;
        this.user_name = user_name;
        this.thumbnail_url = thumbnail_url;
    }

    //Retrieving Bean_LoginDetails data from parcel object.This constructor is invoked by the method createFromParcel() of the object CREATOR
    protected Bean_LoginDetails(Parcel in) {
        this.user_ID = in.readString();
        this.access_token = in.readString();
        this.user_name = in.readString();
        this.thumbnail_url = in.readString();
    }

    public static final Creator<Bean_LoginDetails> CREATOR = new Creator<Bean_LoginDetails>() {
        @Override
        public Bean_LoginDetails createFromParcel(Parcel in) {
            return new Bean_LoginDetails(in);
        }

        @Override
        public Bean_LoginDetails[] newArray(int size) {
            return new Bean_LoginDetails[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    //Storing Bean_LoginDetails in Parcel object
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(user_ID);
        parcel.writeString(access_token);
        parcel.writeString(user_name);
        parcel.writeString(thumbnail_url);
    }

    //getter and Setter
    public String getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(String user_ID) {
        this.user_ID = user_ID;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }
}

package com.almabay.almachat.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by deepakr on 1/22/2016.
 */
public class UserLogin {
    @SerializedName("email")
    String email;

    @SerializedName("password")
    String password;

    public UserLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }
    //Overriding toString() method to create JSON format of object
    public String toString() {
        return "UserLogin {email =" + email + ",password = " + password + "}";
    }
}

package com.almabay.almachat.apiConfiguration;

/**
 * Created by deepakr on 1/27/2016.
 */
public class ApiConfiguration {
    private String api = "http://phpstack-11819-25991-62288.cloudwaysapps.com/"; //Base URL for Login
    //private String api_message = "http://pms.vebific.com:81/chat/index/"; // Base URL    for old messages
    private String api_message = "http://192.168.2.250:82/chat/index/";
    public String getApi() {
        return api;
    }
    public String getApi_message() {
        return api_message;
    }
}

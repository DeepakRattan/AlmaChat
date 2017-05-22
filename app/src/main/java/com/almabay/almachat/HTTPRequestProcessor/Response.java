package com.almabay.almachat.HTTPRequestProcessor;

/**
 * Created by deepakr on 9/9/2015.
 */

// This class contains response String and Response Code of HTTP request
public class Response {
    String jsonResponseString;
    int responseCode;

    public String getJsonResponseString() {
        return jsonResponseString;
    }

    public void setJsonResponseString(String jsonResponseString) {
        this.jsonResponseString = jsonResponseString;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
}

package com.almabay.almachat.pojo.upload_image_response;

/**
 * Created by deepakr on 2/1/2016.
 */
public class ResponseUploadImage {
    private Detail detail;
    private String statusCode;

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}

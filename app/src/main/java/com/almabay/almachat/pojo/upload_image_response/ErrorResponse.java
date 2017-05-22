package com.almabay.almachat.pojo.upload_image_response;

/**
 * Created by deepakr on 2/3/2016.
 */
public class ErrorResponse {

    private String statusCode;
    private ErrorDetail errorDetail;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public ErrorDetail getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(ErrorDetail errorDetail) {
        this.errorDetail = errorDetail;
    }
}

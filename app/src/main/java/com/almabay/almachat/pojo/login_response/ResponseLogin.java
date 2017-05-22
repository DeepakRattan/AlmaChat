package com.almabay.almachat.pojo.login_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by deepakr on 1/25/2016.
 */
public class ResponseLogin {

    @SerializedName("detail")
    @Expose
    private Detail detail;
    @SerializedName("statusCode")
    @Expose
    private String statusCode;

    /**
     *
     * @return
     * The detail
     */
    public Detail getDetail() {
        return detail;
    }

    /**
     *
     * @param detail
     * The detail
     */
    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    /**
     *
     * @return
     * The statusCode
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     *
     * @param statusCode
     * The statusCode
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

}

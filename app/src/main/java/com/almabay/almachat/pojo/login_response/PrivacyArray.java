package com.almabay.almachat.pojo.login_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by deepakr on 1/25/2016.
 */
public class PrivacyArray {

    @SerializedName("privacy_option_id")
    @Expose
    private String privacyOptionId;
    @SerializedName("privacy_option_name")
    @Expose
    private String privacyOptionName;

    /**
     *
     * @return
     * The privacyOptionId
     */
    public String getPrivacyOptionId() {
        return privacyOptionId;
    }

    /**
     *
     * @param privacyOptionId
     * The privacy_option_id
     */
    public void setPrivacyOptionId(String privacyOptionId) {
        this.privacyOptionId = privacyOptionId;
    }

    /**
     *
     * @return
     * The privacyOptionName
     */
    public String getPrivacyOptionName() {
        return privacyOptionName;
    }

    /**
     *
     * @param privacyOptionName
     * The privacy_option_name
     */
    public void setPrivacyOptionName(String privacyOptionName) {
        this.privacyOptionName = privacyOptionName;
    }

}

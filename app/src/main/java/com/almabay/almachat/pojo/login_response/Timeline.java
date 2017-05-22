package com.almabay.almachat.pojo.login_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by deepakr on 1/25/2016.
 */
public class Timeline {

    @SerializedName("wall_user_id")
    @Expose
    private String wallUserId;

    /**
     *
     * @return
     * The wallUserId
     */
    public String getWallUserId() {
        return wallUserId;
    }

    /**
     *
     * @param wallUserId
     * The wall_user_id
     */
    public void setWallUserId(String wallUserId) {
        this.wallUserId = wallUserId;
    }

}

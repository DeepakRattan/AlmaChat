package com.almabay.almachat.pojo.login_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by deepakr on 1/25/2016.
 */
public class UserInfo_ {

    @SerializedName("timeline")
    @Expose
    private Timeline timeline;
    @SerializedName("logged")
    @Expose
    private Boolean logged;
    @SerializedName("user")
    @Expose
    private User user;

    /**
     *
     * @return
     * The timeline
     */
    public Timeline getTimeline() {
        return timeline;
    }

    /**
     *
     * @param timeline
     * The timeline
     */
    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    /**
     *
     * @return
     * The logged
     */
    public Boolean getLogged() {
        return logged;
    }

    /**
     *
     * @param logged
     * The logged
     */
    public void setLogged(Boolean logged) {
        this.logged = logged;
    }

    /**
     *
     * @return
     * The user
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     * The user
     */
    public void setUser(User user) {
        this.user = user;
    }

}


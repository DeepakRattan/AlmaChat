package com.almabay.almachat.pojo.login_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by deepakr on 1/25/2016.
 */
public class Avatar {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("active")
    @Expose
    private String active;
    @SerializedName("album_id")
    @Expose
    private String albumId;
    @SerializedName("extension")
    @Expose
    private String extension;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("post_id")
    @Expose
    private String postId;
    @SerializedName("temp")
    @Expose
    private String temp;
    @SerializedName("timeline_id")
    @Expose
    private String timelineId;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("complete_url")
    @Expose
    private String completeUrl;
    @SerializedName("post_url")
    @Expose
    private String postUrl;

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The active
     */
    public String getActive() {
        return active;
    }

    /**
     *
     * @param active
     * The active
     */
    public void setActive(String active) {
        this.active = active;
    }

    /**
     *
     * @return
     * The albumId
     */
    public String getAlbumId() {
        return albumId;
    }

    /**
     *
     * @param albumId
     * The album_id
     */
    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    /**
     *
     * @return
     * The extension
     */
    public String getExtension() {
        return extension;
    }

    /**
     *
     * @param extension
     * The extension
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The postId
     */
    public String getPostId() {
        return postId;
    }

    /**
     *
     * @param postId
     * The post_id
     */
    public void setPostId(String postId) {
        this.postId = postId;
    }

    /**
     *
     * @return
     * The temp
     */
    public String getTemp() {
        return temp;
    }

    /**
     *
     * @param temp
     * The temp
     */
    public void setTemp(String temp) {
        this.temp = temp;
    }

    /**
     *
     * @return
     * The timelineId
     */
    public String getTimelineId() {
        return timelineId;
    }

    /**
     *
     * @param timelineId
     * The timeline_id
     */
    public void setTimelineId(String timelineId) {
        this.timelineId = timelineId;
    }

    /**
     *
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     * The completeUrl
     */
    public String getCompleteUrl() {
        return completeUrl;
    }

    /**
     *
     * @param completeUrl
     * The complete_url
     */
    public void setCompleteUrl(String completeUrl) {
        this.completeUrl = completeUrl;
    }

    /**
     *
     * @return
     * The postUrl
     */
    public String getPostUrl() {
        return postUrl;
    }

    /**
     *
     * @param postUrl
     * The post_url
     */
    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

}

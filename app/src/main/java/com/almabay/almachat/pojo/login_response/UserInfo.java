package com.almabay.almachat.pojo.login_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deepakr on 1/25/2016.
 */
public class UserInfo {

    @SerializedName("userInfo")
    @Expose
    private UserInfo_ userInfo;
    @SerializedName("totalRecords")
    @Expose
    private String totalRecords;
    @SerializedName("timelineJobInfo")
    @Expose
    private Boolean timelineJobInfo;
    @SerializedName("privacyArray")
    @Expose
    private List<PrivacyArray> privacyArray = new ArrayList<PrivacyArray>();
    @SerializedName("educationInfo")
    @Expose
    private List<EducationInfo> educationInfo = new ArrayList<EducationInfo>();
    @SerializedName("professionalInfo")
    @Expose
    private List<ProfessionalInfo> professionalInfo = new ArrayList<ProfessionalInfo>();
    @SerializedName("friendWithYou")
    @Expose
    private Boolean friendWithYou;
    @SerializedName("mutualFriends")
    @Expose
    private Object mutualFriends;
    @SerializedName("photos")
    @Expose
    private Object photos;

    /**
     *
     * @return
     * The userInfo
     */
    public UserInfo_ getUserInfo() {
        return userInfo;
    }

    /**
     *
     * @param userInfo
     * The userInfo
     */
    public void setUserInfo(UserInfo_ userInfo) {
        this.userInfo = userInfo;
    }

    /**
     *
     * @return
     * The totalRecords
     */
    public String getTotalRecords() {
        return totalRecords;
    }

    /**
     *
     * @param totalRecords
     * The totalRecords
     */
    public void setTotalRecords(String totalRecords) {
        this.totalRecords = totalRecords;
    }

    /**
     *
     * @return
     * The timelineJobInfo
     */
    public Boolean getTimelineJobInfo() {
        return timelineJobInfo;
    }

    /**
     *
     * @param timelineJobInfo
     * The timelineJobInfo
     */
    public void setTimelineJobInfo(Boolean timelineJobInfo) {
        this.timelineJobInfo = timelineJobInfo;
    }

    /**
     *
     * @return
     * The privacyArray
     */
    public List<PrivacyArray> getPrivacyArray() {
        return privacyArray;
    }

    /**
     *
     * @param privacyArray
     * The privacyArray
     */
    public void setPrivacyArray(List<PrivacyArray> privacyArray) {
        this.privacyArray = privacyArray;
    }

    /**
     *
     * @return
     * The educationInfo
     */
    public List<EducationInfo> getEducationInfo() {
        return educationInfo;
    }

    /**
     *
     * @param educationInfo
     * The educationInfo
     */
    public void setEducationInfo(List<EducationInfo> educationInfo) {
        this.educationInfo = educationInfo;
    }

    /**
     *
     * @return
     * The professionalInfo
     */
    public List<ProfessionalInfo> getProfessionalInfo() {
        return professionalInfo;
    }

    /**
     *
     * @param professionalInfo
     * The professionalInfo
     */
    public void setProfessionalInfo(List<ProfessionalInfo> professionalInfo) {
        this.professionalInfo = professionalInfo;
    }

    /**
     *
     * @return
     * The friendWithYou
     */
    public Boolean getFriendWithYou() {
        return friendWithYou;
    }

    /**
     *
     * @param friendWithYou
     * The friendWithYou
     */
    public void setFriendWithYou(Boolean friendWithYou) {
        this.friendWithYou = friendWithYou;
    }

    /**
     *
     * @return
     * The mutualFriends
     */
    public Object getMutualFriends() {
        return mutualFriends;
    }

    /**
     *
     * @param mutualFriends
     * The mutualFriends
     */
    public void setMutualFriends(Object mutualFriends) {
        this.mutualFriends = mutualFriends;
    }

    /**
     *
     * @return
     * The photos
     */
    public Object getPhotos() {
        return photos;
    }

    /**
     *
     * @param photos
     * The photos
     */
    public void setPhotos(Object photos) {
        this.photos = photos;
    }

}


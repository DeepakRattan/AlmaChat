package com.almabay.almachat.pojo.login_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by deepakr on 1/25/2016.
 */
public class User {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("comment_privacy")
    @Expose
    private String commentPrivacy;
    @SerializedName("confirm_followers")
    @Expose
    private String confirmFollowers;
    @SerializedName("current_city")
    @Expose
    private String currentCity;
    @SerializedName("follow_privacy")
    @Expose
    private String followPrivacy;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("hometown")
    @Expose
    private Object hometown;
    @SerializedName("message_privacy")
    @Expose
    private String messagePrivacy;
    @SerializedName("timeline_post_privacy")
    @Expose
    private String timelinePostPrivacy;
    @SerializedName("feed_section")
    @Expose
    private String feedSection;
    @SerializedName("post_privacy")
    @Expose
    private String postPrivacy;
    @SerializedName("current_city_id")
    @Expose
    private String currentCityId;
    @SerializedName("userSkills")
    @Expose
    private String userSkills;
    @SerializedName("birth")
    @Expose
    private Birth birth;
    @SerializedName("about")
    @Expose
    private String about;
    @SerializedName("active")
    @Expose
    private String active;
    @SerializedName("avatar_id")
    @Expose
    private String avatarId;
    @SerializedName("cover_id")
    @Expose
    private String coverId;
    @SerializedName("cover_position")
    @Expose
    private String coverPosition;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("email_verification_key")
    @Expose
    private Object emailVerificationKey;
    @SerializedName("email_verified")
    @Expose
    private String emailVerified;
    @SerializedName("language")
    @Expose
    private Object language;
    @SerializedName("last_logged")
    @Expose
    private String lastLogged;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("timezone")
    @Expose
    private Object timezone;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("username")
    @Expose
    private Object username;
    @SerializedName("verified")
    @Expose
    private Boolean verified;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("middle_name")
    @Expose
    private Object middleName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("title")
    @Expose
    private Object title;
    @SerializedName("marital_status")
    @Expose
    private String maritalStatus;
    @SerializedName("childrens")
    @Expose
    private String childrens;
    @SerializedName("hobbies")
    @Expose
    private String hobbies;
    @SerializedName("interests")
    @Expose
    private String interests;
    @SerializedName("feed_back")
    @Expose
    private Object feedBack;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("profile_pic")
    @Expose
    private Object profilePic;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("date_modified")
    @Expose
    private String dateModified;
    @SerializedName("activation_code")
    @Expose
    private Object activationCode;
    @SerializedName("social_login_type")
    @Expose
    private String socialLoginType;
    @SerializedName("facebook_id")
    @Expose
    private String facebookId;
    @SerializedName("linked_in_id")
    @Expose
    private Object linkedInId;
    @SerializedName("google_id")
    @Expose
    private Object googleId;
    @SerializedName("employment_status")
    @Expose
    private Object employmentStatus;
    @SerializedName("is_deleted")
    @Expose
    private String isDeleted;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("reference_institute")
    @Expose
    private String referenceInstitute;
    @SerializedName("industry")
    @Expose
    private String industry;
    @SerializedName("proffesion_type")
    @Expose
    private String proffesionType;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("cover")
    @Expose
    private Cover cover;
    @SerializedName("actual_cover_url")
    @Expose
    private String actualCoverUrl;
    @SerializedName("cover_url")
    @Expose
    private String coverUrl;
    @SerializedName("avatar")
    @Expose
    private Avatar avatar;
    @SerializedName("thumbnail_url")
    @Expose
    private String thumbnailUrl;
    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;
    @SerializedName("online")
    @Expose
    private Boolean online;

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
     * The birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     *
     * @param birthday
     * The birthday
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     *
     * @return
     * The commentPrivacy
     */
    public String getCommentPrivacy() {
        return commentPrivacy;
    }

    /**
     *
     * @param commentPrivacy
     * The comment_privacy
     */
    public void setCommentPrivacy(String commentPrivacy) {
        this.commentPrivacy = commentPrivacy;
    }

    /**
     *
     * @return
     * The confirmFollowers
     */
    public String getConfirmFollowers() {
        return confirmFollowers;
    }

    /**
     *
     * @param confirmFollowers
     * The confirm_followers
     */
    public void setConfirmFollowers(String confirmFollowers) {
        this.confirmFollowers = confirmFollowers;
    }

    /**
     *
     * @return
     * The currentCity
     */
    public String getCurrentCity() {
        return currentCity;
    }

    /**
     *
     * @param currentCity
     * The current_city
     */
    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    /**
     *
     * @return
     * The followPrivacy
     */
    public String getFollowPrivacy() {
        return followPrivacy;
    }

    /**
     *
     * @param followPrivacy
     * The follow_privacy
     */
    public void setFollowPrivacy(String followPrivacy) {
        this.followPrivacy = followPrivacy;
    }

    /**
     *
     * @return
     * The gender
     */
    public String getGender() {
        return gender;
    }

    /**
     *
     * @param gender
     * The gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     *
     * @return
     * The hometown
     */
    public Object getHometown() {
        return hometown;
    }

    /**
     *
     * @param hometown
     * The hometown
     */
    public void setHometown(Object hometown) {
        this.hometown = hometown;
    }

    /**
     *
     * @return
     * The messagePrivacy
     */
    public String getMessagePrivacy() {
        return messagePrivacy;
    }

    /**
     *
     * @param messagePrivacy
     * The message_privacy
     */
    public void setMessagePrivacy(String messagePrivacy) {
        this.messagePrivacy = messagePrivacy;
    }

    /**
     *
     * @return
     * The timelinePostPrivacy
     */
    public String getTimelinePostPrivacy() {
        return timelinePostPrivacy;
    }

    /**
     *
     * @param timelinePostPrivacy
     * The timeline_post_privacy
     */
    public void setTimelinePostPrivacy(String timelinePostPrivacy) {
        this.timelinePostPrivacy = timelinePostPrivacy;
    }

    /**
     *
     * @return
     * The feedSection
     */
    public String getFeedSection() {
        return feedSection;
    }

    /**
     *
     * @param feedSection
     * The feed_section
     */
    public void setFeedSection(String feedSection) {
        this.feedSection = feedSection;
    }

    /**
     *
     * @return
     * The postPrivacy
     */
    public String getPostPrivacy() {
        return postPrivacy;
    }

    /**
     *
     * @param postPrivacy
     * The post_privacy
     */
    public void setPostPrivacy(String postPrivacy) {
        this.postPrivacy = postPrivacy;
    }

    /**
     *
     * @return
     * The currentCityId
     */
    public String getCurrentCityId() {
        return currentCityId;
    }

    /**
     *
     * @param currentCityId
     * The current_city_id
     */
    public void setCurrentCityId(String currentCityId) {
        this.currentCityId = currentCityId;
    }

    /**
     *
     * @return
     * The userSkills
     */
    public String getUserSkills() {
        return userSkills;
    }

    /**
     *
     * @param userSkills
     * The userSkills
     */
    public void setUserSkills(String userSkills) {
        this.userSkills = userSkills;
    }

    /**
     *
     * @return
     * The birth
     */
    public Birth getBirth() {
        return birth;
    }

    /**
     *
     * @param birth
     * The birth
     */
    public void setBirth(Birth birth) {
        this.birth = birth;
    }

    /**
     *
     * @return
     * The about
     */
    public String getAbout() {
        return about;
    }

    /**
     *
     * @param about
     * The about
     */
    public void setAbout(String about) {
        this.about = about;
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
     * The avatarId
     */
    public String getAvatarId() {
        return avatarId;
    }

    /**
     *
     * @param avatarId
     * The avatar_id
     */
    public void setAvatarId(String avatarId) {
        this.avatarId = avatarId;
    }

    /**
     *
     * @return
     * The coverId
     */
    public String getCoverId() {
        return coverId;
    }

    /**
     *
     * @param coverId
     * The cover_id
     */
    public void setCoverId(String coverId) {
        this.coverId = coverId;
    }

    /**
     *
     * @return
     * The coverPosition
     */
    public String getCoverPosition() {
        return coverPosition;
    }

    /**
     *
     * @param coverPosition
     * The cover_position
     */
    public void setCoverPosition(String coverPosition) {
        this.coverPosition = coverPosition;
    }

    /**
     *
     * @return
     * The email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     * The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     * The emailVerificationKey
     */
    public Object getEmailVerificationKey() {
        return emailVerificationKey;
    }

    /**
     *
     * @param emailVerificationKey
     * The email_verification_key
     */
    public void setEmailVerificationKey(Object emailVerificationKey) {
        this.emailVerificationKey = emailVerificationKey;
    }

    /**
     *
     * @return
     * The emailVerified
     */
    public String getEmailVerified() {
        return emailVerified;
    }

    /**
     *
     * @param emailVerified
     * The email_verified
     */
    public void setEmailVerified(String emailVerified) {
        this.emailVerified = emailVerified;
    }

    /**
     *
     * @return
     * The language
     */
    public Object getLanguage() {
        return language;
    }

    /**
     *
     * @param language
     * The language
     */
    public void setLanguage(Object language) {
        this.language = language;
    }

    /**
     *
     * @return
     * The lastLogged
     */
    public String getLastLogged() {
        return lastLogged;
    }

    /**
     *
     * @param lastLogged
     * The last_logged
     */
    public void setLastLogged(String lastLogged) {
        this.lastLogged = lastLogged;
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
     * The time
     */
    public String getTime() {
        return time;
    }

    /**
     *
     * @param time
     * The time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     *
     * @return
     * The timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     *
     * @param timestamp
     * The timestamp
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     *
     * @return
     * The timezone
     */
    public Object getTimezone() {
        return timezone;
    }

    /**
     *
     * @param timezone
     * The timezone
     */
    public void setTimezone(Object timezone) {
        this.timezone = timezone;
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
     * The username
     */
    public Object getUsername() {
        return username;
    }

    /**
     *
     * @param username
     * The username
     */
    public void setUsername(Object username) {
        this.username = username;
    }

    /**
     *
     * @return
     * The verified
     */
    public Boolean getVerified() {
        return verified;
    }

    /**
     *
     * @param verified
     * The verified
     */
    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    /**
     *
     * @return
     * The userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     * The user_id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     *
     * @return
     * The firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     * The first_name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     * The middleName
     */
    public Object getMiddleName() {
        return middleName;
    }

    /**
     *
     * @param middleName
     * The middle_name
     */
    public void setMiddleName(Object middleName) {
        this.middleName = middleName;
    }

    /**
     *
     * @return
     * The lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     * The last_name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     * The dob
     */
    public String getDob() {
        return dob;
    }

    /**
     *
     * @param dob
     * The dob
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     *
     * @return
     * The mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     *
     * @param mobile
     * The mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     *
     * @return
     * The title
     */
    public Object getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(Object title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The maritalStatus
     */
    public String getMaritalStatus() {
        return maritalStatus;
    }

    /**
     *
     * @param maritalStatus
     * The marital_status
     */
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    /**
     *
     * @return
     * The childrens
     */
    public String getChildrens() {
        return childrens;
    }

    /**
     *
     * @param childrens
     * The childrens
     */
    public void setChildrens(String childrens) {
        this.childrens = childrens;
    }

    /**
     *
     * @return
     * The hobbies
     */
    public String getHobbies() {
        return hobbies;
    }

    /**
     *
     * @param hobbies
     * The hobbies
     */
    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    /**
     *
     * @return
     * The interests
     */
    public String getInterests() {
        return interests;
    }

    /**
     *
     * @param interests
     * The interests
     */
    public void setInterests(String interests) {
        this.interests = interests;
    }

    /**
     *
     * @return
     * The feedBack
     */
    public Object getFeedBack() {
        return feedBack;
    }

    /**
     *
     * @param feedBack
     * The feed_back
     */
    public void setFeedBack(Object feedBack) {
        this.feedBack = feedBack;
    }

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The profilePic
     */
    public Object getProfilePic() {
        return profilePic;
    }

    /**
     *
     * @param profilePic
     * The profile_pic
     */
    public void setProfilePic(Object profilePic) {
        this.profilePic = profilePic;
    }

    /**
     *
     * @return
     * The dateCreated
     */
    public String getDateCreated() {
        return dateCreated;
    }

    /**
     *
     * @param dateCreated
     * The date_created
     */
    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     *
     * @return
     * The dateModified
     */
    public String getDateModified() {
        return dateModified;
    }

    /**
     *
     * @param dateModified
     * The date_modified
     */
    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    /**
     *
     * @return
     * The activationCode
     */
    public Object getActivationCode() {
        return activationCode;
    }

    /**
     *
     * @param activationCode
     * The activation_code
     */
    public void setActivationCode(Object activationCode) {
        this.activationCode = activationCode;
    }

    /**
     *
     * @return
     * The socialLoginType
     */
    public String getSocialLoginType() {
        return socialLoginType;
    }

    /**
     *
     * @param socialLoginType
     * The social_login_type
     */
    public void setSocialLoginType(String socialLoginType) {
        this.socialLoginType = socialLoginType;
    }

    /**
     *
     * @return
     * The facebookId
     */
    public String getFacebookId() {
        return facebookId;
    }

    /**
     *
     * @param facebookId
     * The facebook_id
     */
    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    /**
     *
     * @return
     * The linkedInId
     */
    public Object getLinkedInId() {
        return linkedInId;
    }

    /**
     *
     * @param linkedInId
     * The linked_in_id
     */
    public void setLinkedInId(Object linkedInId) {
        this.linkedInId = linkedInId;
    }

    /**
     *
     * @return
     * The googleId
     */
    public Object getGoogleId() {
        return googleId;
    }

    /**
     *
     * @param googleId
     * The google_id
     */
    public void setGoogleId(Object googleId) {
        this.googleId = googleId;
    }

    /**
     *
     * @return
     * The employmentStatus
     */
    public Object getEmploymentStatus() {
        return employmentStatus;
    }

    /**
     *
     * @param employmentStatus
     * The employment_status
     */
    public void setEmploymentStatus(Object employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    /**
     *
     * @return
     * The isDeleted
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     *
     * @param isDeleted
     * The is_deleted
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     *
     * @return
     * The website
     */
    public String getWebsite() {
        return website;
    }

    /**
     *
     * @param website
     * The website
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     *
     * @return
     * The referenceInstitute
     */
    public String getReferenceInstitute() {
        return referenceInstitute;
    }

    /**
     *
     * @param referenceInstitute
     * The reference_institute
     */
    public void setReferenceInstitute(String referenceInstitute) {
        this.referenceInstitute = referenceInstitute;
    }

    /**
     *
     * @return
     * The industry
     */
    public String getIndustry() {
        return industry;
    }

    /**
     *
     * @param industry
     * The industry
     */
    public void setIndustry(String industry) {
        this.industry = industry;
    }

    /**
     *
     * @return
     * The proffesionType
     */
    public String getProffesionType() {
        return proffesionType;
    }

    /**
     *
     * @param proffesionType
     * The proffesion_type
     */
    public void setProffesionType(String proffesionType) {
        this.proffesionType = proffesionType;
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
     * The cover
     */
    public Cover getCover() {
        return cover;
    }

    /**
     *
     * @param cover
     * The cover
     */
    public void setCover(Cover cover) {
        this.cover = cover;
    }

    /**
     *
     * @return
     * The actualCoverUrl
     */
    public String getActualCoverUrl() {
        return actualCoverUrl;
    }

    /**
     *
     * @param actualCoverUrl
     * The actual_cover_url
     */
    public void setActualCoverUrl(String actualCoverUrl) {
        this.actualCoverUrl = actualCoverUrl;
    }

    /**
     *
     * @return
     * The coverUrl
     */
    public String getCoverUrl() {
        return coverUrl;
    }

    /**
     *
     * @param coverUrl
     * The cover_url
     */
    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    /**
     *
     * @return
     * The avatar
     */
    public Avatar getAvatar() {
        return avatar;
    }

    /**
     *
     * @param avatar
     * The avatar
     */
    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    /**
     *
     * @return
     * The thumbnailUrl
     */
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    /**
     *
     * @param thumbnailUrl
     * The thumbnail_url
     */
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    /**
     *
     * @return
     * The avatarUrl
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     *
     * @param avatarUrl
     * The avatar_url
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     *
     * @return
     * The online
     */
    public Boolean getOnline() {
        return online;
    }

    /**
     *
     * @param online
     * The online
     */
    public void setOnline(Boolean online) {
        this.online = online;
    }

}

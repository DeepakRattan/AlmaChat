package com.almabay.almachat.pojo.login_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by deepakr on 1/25/2016.
 */
public class ProfessionalInfo {

    @SerializedName("user_profession_id")
    @Expose
    private String userProfessionId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("industry")
    @Expose
    private Object industry;
    @SerializedName("organisation")
    @Expose
    private String organisation;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("city")
    @Expose
    private Object city;
    @SerializedName("month_from")
    @Expose
    private String monthFrom;
    @SerializedName("month_to")
    @Expose
    private String monthTo;
    @SerializedName("year_from")
    @Expose
    private String yearFrom;
    @SerializedName("year_to")
    @Expose
    private String yearTo;
    @SerializedName("profession_type")
    @Expose
    private Object professionType;
    @SerializedName("job_profile")
    @Expose
    private Object jobProfile;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("date_modified")
    @Expose
    private String dateModified;
    @SerializedName("org_type")
    @Expose
    private Object orgType;
    @SerializedName("currently_working_here")
    @Expose
    private Object currentlyWorkingHere;
    @SerializedName("functional_area")
    @Expose
    private Object functionalArea;
    @SerializedName("on_level")
    @Expose
    private Object onLevel;
    @SerializedName("work_meta")
    @Expose
    private Object workMeta;
    @SerializedName("migrated")
    @Expose
    private String migrated;

    /**
     *
     * @return
     * The userProfessionId
     */
    public String getUserProfessionId() {
        return userProfessionId;
    }

    /**
     *
     * @param userProfessionId
     * The user_profession_id
     */
    public void setUserProfessionId(String userProfessionId) {
        this.userProfessionId = userProfessionId;
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
     * The industry
     */
    public Object getIndustry() {
        return industry;
    }

    /**
     *
     * @param industry
     * The industry
     */
    public void setIndustry(Object industry) {
        this.industry = industry;
    }

    /**
     *
     * @return
     * The organisation
     */
    public String getOrganisation() {
        return organisation;
    }

    /**
     *
     * @param organisation
     * The organisation
     */
    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    /**
     *
     * @return
     * The designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     *
     * @param designation
     * The designation
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     *
     * @return
     * The city
     */
    public Object getCity() {
        return city;
    }

    /**
     *
     * @param city
     * The city
     */
    public void setCity(Object city) {
        this.city = city;
    }

    /**
     *
     * @return
     * The monthFrom
     */
    public String getMonthFrom() {
        return monthFrom;
    }

    /**
     *
     * @param monthFrom
     * The month_from
     */
    public void setMonthFrom(String monthFrom) {
        this.monthFrom = monthFrom;
    }

    /**
     *
     * @return
     * The monthTo
     */
    public String getMonthTo() {
        return monthTo;
    }

    /**
     *
     * @param monthTo
     * The month_to
     */
    public void setMonthTo(String monthTo) {
        this.monthTo = monthTo;
    }

    /**
     *
     * @return
     * The yearFrom
     */
    public String getYearFrom() {
        return yearFrom;
    }

    /**
     *
     * @param yearFrom
     * The year_from
     */
    public void setYearFrom(String yearFrom) {
        this.yearFrom = yearFrom;
    }

    /**
     *
     * @return
     * The yearTo
     */
    public String getYearTo() {
        return yearTo;
    }

    /**
     *
     * @param yearTo
     * The year_to
     */
    public void setYearTo(String yearTo) {
        this.yearTo = yearTo;
    }

    /**
     *
     * @return
     * The professionType
     */
    public Object getProfessionType() {
        return professionType;
    }

    /**
     *
     * @param professionType
     * The profession_type
     */
    public void setProfessionType(Object professionType) {
        this.professionType = professionType;
    }

    /**
     *
     * @return
     * The jobProfile
     */
    public Object getJobProfile() {
        return jobProfile;
    }

    /**
     *
     * @param jobProfile
     * The job_profile
     */
    public void setJobProfile(Object jobProfile) {
        this.jobProfile = jobProfile;
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
     * The orgType
     */
    public Object getOrgType() {
        return orgType;
    }

    /**
     *
     * @param orgType
     * The org_type
     */
    public void setOrgType(Object orgType) {
        this.orgType = orgType;
    }

    /**
     *
     * @return
     * The currentlyWorkingHere
     */
    public Object getCurrentlyWorkingHere() {
        return currentlyWorkingHere;
    }

    /**
     *
     * @param currentlyWorkingHere
     * The currently_working_here
     */
    public void setCurrentlyWorkingHere(Object currentlyWorkingHere) {
        this.currentlyWorkingHere = currentlyWorkingHere;
    }

    /**
     *
     * @return
     * The functionalArea
     */
    public Object getFunctionalArea() {
        return functionalArea;
    }

    /**
     *
     * @param functionalArea
     * The functional_area
     */
    public void setFunctionalArea(Object functionalArea) {
        this.functionalArea = functionalArea;
    }

    /**
     *
     * @return
     * The onLevel
     */
    public Object getOnLevel() {
        return onLevel;
    }

    /**
     *
     * @param onLevel
     * The on_level
     */
    public void setOnLevel(Object onLevel) {
        this.onLevel = onLevel;
    }

    /**
     *
     * @return
     * The workMeta
     */
    public Object getWorkMeta() {
        return workMeta;
    }

    /**
     *
     * @param workMeta
     * The work_meta
     */
    public void setWorkMeta(Object workMeta) {
        this.workMeta = workMeta;
    }

    /**
     *
     * @return
     * The migrated
     */
    public String getMigrated() {
        return migrated;
    }

    /**
     *
     * @param migrated
     * The migrated
     */
    public void setMigrated(String migrated) {
        this.migrated = migrated;
    }

}
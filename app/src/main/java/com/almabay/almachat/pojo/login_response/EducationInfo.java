package com.almabay.almachat.pojo.login_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by deepakr on 1/25/2016.
 */
public class EducationInfo {

    @SerializedName("user_qualification_id")
    @Expose
    private String userQualificationId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("institute_name")
    @Expose
    private String instituteName;
    @SerializedName("qualification_type")
    @Expose
    private String qualificationType;
    @SerializedName("from_month")
    @Expose
    private Object fromMonth;
    @SerializedName("to_month")
    @Expose
    private Object toMonth;
    @SerializedName("from_year")
    @Expose
    private String fromYear;
    @SerializedName("to_year")
    @Expose
    private String toYear;
    @SerializedName("other_qualification_type_description")
    @Expose
    private String otherQualificationTypeDescription;
    @SerializedName("field_of_study")
    @Expose
    private String fieldOfStudy;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("date_modified")
    @Expose
    private String dateModified;
    @SerializedName("currently_studing_here")
    @Expose
    private Object currentlyStudingHere;
    @SerializedName("program_id")
    @Expose
    private Object programId;
    @SerializedName("institute_id")
    @Expose
    private String instituteId;
    @SerializedName("logo")
    @Expose
    private String logo;

    /**
     *
     * @return
     * The userQualificationId
     */
    public String getUserQualificationId() {
        return userQualificationId;
    }

    /**
     *
     * @param userQualificationId
     * The user_qualification_id
     */
    public void setUserQualificationId(String userQualificationId) {
        this.userQualificationId = userQualificationId;
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
     * The instituteName
     */
    public String getInstituteName() {
        return instituteName;
    }

    /**
     *
     * @param instituteName
     * The institute_name
     */
    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    /**
     *
     * @return
     * The qualificationType
     */
    public String getQualificationType() {
        return qualificationType;
    }

    /**
     *
     * @param qualificationType
     * The qualification_type
     */
    public void setQualificationType(String qualificationType) {
        this.qualificationType = qualificationType;
    }

    /**
     *
     * @return
     * The fromMonth
     */
    public Object getFromMonth() {
        return fromMonth;
    }

    /**
     *
     * @param fromMonth
     * The from_month
     */
    public void setFromMonth(Object fromMonth) {
        this.fromMonth = fromMonth;
    }

    /**
     *
     * @return
     * The toMonth
     */
    public Object getToMonth() {
        return toMonth;
    }

    /**
     *
     * @param toMonth
     * The to_month
     */
    public void setToMonth(Object toMonth) {
        this.toMonth = toMonth;
    }

    /**
     *
     * @return
     * The fromYear
     */
    public String getFromYear() {
        return fromYear;
    }

    /**
     *
     * @param fromYear
     * The from_year
     */
    public void setFromYear(String fromYear) {
        this.fromYear = fromYear;
    }

    /**
     *
     * @return
     * The toYear
     */
    public String getToYear() {
        return toYear;
    }

    /**
     *
     * @param toYear
     * The to_year
     */
    public void setToYear(String toYear) {
        this.toYear = toYear;
    }

    /**
     *
     * @return
     * The otherQualificationTypeDescription
     */
    public String getOtherQualificationTypeDescription() {
        return otherQualificationTypeDescription;
    }

    /**
     *
     * @param otherQualificationTypeDescription
     * The other_qualification_type_description
     */
    public void setOtherQualificationTypeDescription(String otherQualificationTypeDescription) {
        this.otherQualificationTypeDescription = otherQualificationTypeDescription;
    }

    /**
     *
     * @return
     * The fieldOfStudy
     */
    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    /**
     *
     * @param fieldOfStudy
     * The field_of_study
     */
    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
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
     * The currentlyStudingHere
     */
    public Object getCurrentlyStudingHere() {
        return currentlyStudingHere;
    }

    /**
     *
     * @param currentlyStudingHere
     * The currently_studing_here
     */
    public void setCurrentlyStudingHere(Object currentlyStudingHere) {
        this.currentlyStudingHere = currentlyStudingHere;
    }

    /**
     *
     * @return
     * The programId
     */
    public Object getProgramId() {
        return programId;
    }

    /**
     *
     * @param programId
     * The program_id
     */
    public void setProgramId(Object programId) {
        this.programId = programId;
    }

    /**
     *
     * @return
     * The instituteId
     */
    public String getInstituteId() {
        return instituteId;
    }

    /**
     *
     * @param instituteId
     * The institute_id
     */
    public void setInstituteId(String instituteId) {
        this.instituteId = instituteId;
    }

    /**
     *
     * @return
     * The logo
     */
    public String getLogo() {
        return logo;
    }

    /**
     *
     * @param logo
     * The logo
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

}
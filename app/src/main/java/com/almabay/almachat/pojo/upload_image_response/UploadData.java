package com.almabay.almachat.pojo.upload_image_response;

/**
 * Created by deepakr on 2/1/2016.
 */
public class UploadData {
    private String fileName;
    private String fileType;
    private String filePath;
    private String fullPath;
    private String rawName;
    private String origName;
    private String clientName;
    private String fileExt;
    private Double fileSize;
    private Boolean isImage;
    private Integer imageWidth;
    private Integer imageHeight;
    private String imageType;
    private String imageSizeStr;

    @Override
    public String toString() {
        return "UploadData{fileName = " + fileName + ",filetype = " + fileType + "}";
    }

    /**
     * @return The fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName The file_name
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return The fileType
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * @param fileType The file_type
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * @return The filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath The file_path
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @return The fullPath
     */
    public String getFullPath() {
        return fullPath;
    }

    /**
     * @param fullPath The full_path
     */
    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    /**
     * @return The rawName
     */
    public String getRawName() {
        return rawName;
    }

    /**
     * @param rawName The raw_name
     */
    public void setRawName(String rawName) {
        this.rawName = rawName;
    }

    /**
     * @return The origName
     */
    public String getOrigName() {
        return origName;
    }

    /**
     * @param origName The orig_name
     */
    public void setOrigName(String origName) {
        this.origName = origName;
    }

    /**
     * @return The clientName
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * @param clientName The client_name
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * @return The fileExt
     */
    public String getFileExt() {
        return fileExt;
    }

    /**
     * @param fileExt The file_ext
     */
    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }

    /**
     * @return The fileSize
     */
    public Double getFileSize() {
        return fileSize;
    }

    /**
     * @param fileSize The file_size
     */
    public void setFileSize(Double fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * @return The isImage
     */
    public Boolean getIsImage() {
        return isImage;
    }

    /**
     * @param isImage The is_image
     */
    public void setIsImage(Boolean isImage) {
        this.isImage = isImage;
    }

    /**
     * @return The imageWidth
     */
    public Integer getImageWidth() {
        return imageWidth;
    }

    /**
     * @param imageWidth The image_width
     */
    public void setImageWidth(Integer imageWidth) {
        this.imageWidth = imageWidth;
    }

    /**
     * @return The imageHeight
     */
    public Integer getImageHeight() {
        return imageHeight;
    }

    /**
     * @param imageHeight The image_height
     */
    public void setImageHeight(Integer imageHeight) {
        this.imageHeight = imageHeight;
    }

    /**
     * @return The imageType
     */
    public String getImageType() {
        return imageType;
    }

    /**
     * @param imageType The image_type
     */
    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    /**
     * @return The imageSizeStr
     */
    public String getImageSizeStr() {
        return imageSizeStr;
    }

    /**
     * @param imageSizeStr The image_size_str
     */
    public void setImageSizeStr(String imageSizeStr) {
        this.imageSizeStr = imageSizeStr;
    }
}

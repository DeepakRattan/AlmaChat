package com.almabay.almachat.beans;

/**
 * Created by deepakr on 2/5/2016.
 */
public class Bean_Friends {
    private String name, url, extension, friendsID;
    boolean isSelected;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getFriendsID() {
        return friendsID;
    }

    public void setFriendsID(String friendsID) {
        this.friendsID = friendsID;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}

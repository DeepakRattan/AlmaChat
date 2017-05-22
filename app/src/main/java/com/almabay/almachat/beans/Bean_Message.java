package com.almabay.almachat.beans;

import com.almabay.almachat.Status;

/**
 * Created by deepakr on 2/6/2016.
 */
public class Bean_Message {
    private String fromName;
    private String message;
    private String time;
    private boolean isSelf; // isSelf is used to check whether the message is owned by you or not
    private Status messageStatus;

    public Bean_Message(String fromName, String message, boolean isSelf) {
        this.fromName = fromName;
        this.message = message;
        this.isSelf = isSelf;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isSelf() {
        return isSelf;
    }

    public void setIsSelf(boolean isSelf) {
        this.isSelf = isSelf;
    }

    public Status getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(Status messageStatus) {
        this.messageStatus = messageStatus;
    }
}

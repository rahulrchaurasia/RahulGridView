package com.example.rahul.gridview.chat.model;

/**
 * Created by Rahul on 28/05/2019.
 */
public class Chat {

    private String sender;
    private String receiver;
    private String message;
    private String chatTime;
    private String isActive;
    private boolean isSelected;

    public Chat() {
    }

    public Chat(String sender, String receiver, String message, String chatTime, String isActive) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.chatTime = chatTime;
        this.isActive = isActive;
    }



    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }




    public String getChatTime() {
        return chatTime;
    }

    public void setChatTime(String chatTime) {
        this.chatTime = chatTime;
    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }





    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }




}

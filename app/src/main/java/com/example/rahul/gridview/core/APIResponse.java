package com.example.rahul.gridview.core;

/**
 * Created by IN-RB on 21-02-2017.
 */

public class APIResponse {

    private String msg;
    private String status_Id;
    private String status;


    private int status_code;

    private String message;
    private int statusId;



    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus_Id() {
        return status_Id;
    }

    public void setStatus_Id(String status_Id) {
        this.status_Id = status_Id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    ///**********************
    ///////////////////////////////
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }


    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }
}

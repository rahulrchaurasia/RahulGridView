package com.example.rahul.gridview.model;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class LeadEntity extends RealmObject {
    /**
     * ProdType :
     * custName : hahah
     * leadId : 365481
     * loanAmnt : 545454
     * mobNo : 9121338219
     * status : Already Taken
     */

    private String ProdType;
    private String custName;
    @PrimaryKey
    private int leadId;
    private int loanAmnt;
    private String mobNo;
    private String status;
    private boolean isSelected;


    private int color = -1;

    public String getProdType() {
        return ProdType;
    }

    public void setProdType(String ProdType) {
        this.ProdType = ProdType;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public int getLeadId() {
        return leadId;
    }

    public void setLeadId(int leadId) {
        this.leadId = leadId;
    }

    public int getLoanAmnt() {
        return loanAmnt;
    }

    public void setLoanAmnt(int loanAmnt) {
        this.loanAmnt = loanAmnt;
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }



}
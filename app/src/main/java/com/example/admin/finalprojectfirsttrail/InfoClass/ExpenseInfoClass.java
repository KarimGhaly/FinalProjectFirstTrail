package com.example.admin.finalprojectfirsttrail.InfoClass;

import java.util.Date;

import java.util.Date;


/**
 * Created by Admin on 10/18/2017.
 */

public class ExpenseInfoClass {

    private float amount;
    private String description;
    private String photoUrl;
    private Date date;
    private String status;

    public ExpenseInfoClass() {
    }

    public ExpenseInfoClass(float amount, String description, String photoUrl, Date date, String status) {
        this.amount = amount;
        this.description = description;
        this.photoUrl = photoUrl;
        this.date = date;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

package com.example.admin.finalprojectfirsttrail.InfoClass;

import java.util.Date;

/**
 * Created by Admin on 10/17/2017.
 */

public class AdvanceInfoClass {
    float amount;
    String descriction;
    String status;
    Date date;

    public AdvanceInfoClass() {
    }

    public AdvanceInfoClass(float amount, String descriction, String status, Date date) {
        this.amount = amount;
        this.descriction = descriction;
        this.status = status;
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDescriction() {
        return descriction;
    }

    public void setDescriction(String descriction) {
        this.descriction = descriction;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

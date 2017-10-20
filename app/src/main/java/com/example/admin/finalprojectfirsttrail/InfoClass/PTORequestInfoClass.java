package com.example.admin.finalprojectfirsttrail.InfoClass;

import java.util.Date;

/**
 * Created by Admin on 10/20/2017.
 */

public class PTORequestInfoClass {
    Date dateFrom;
    Date dateTo;
    Date dateRequest;
    String description;
    String status;

    public PTORequestInfoClass() {
    }

    public PTORequestInfoClass(Date dateFrom, Date dateTo, Date dateRequest, String description, String status) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.dateRequest = dateRequest;
        this.description = description;
        this.status = status;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Date getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(Date dateRequest) {
        this.dateRequest = dateRequest;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

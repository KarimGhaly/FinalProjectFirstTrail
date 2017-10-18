package com.example.admin.finalprojectfirsttrail.InfoClass;

/**
 * Created by Admin on 10/18/2017.
 */

public class ExpenseInfoClass {

    private float amount;
    private String description;
    private String photoUrl;

    public ExpenseInfoClass() {
    }

    public ExpenseInfoClass(float amount, String description, String photoUrl) {
        this.amount = amount;
        this.description = description;
        this.photoUrl = photoUrl;
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
}

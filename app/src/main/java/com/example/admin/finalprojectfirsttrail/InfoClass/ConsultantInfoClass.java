package com.example.admin.finalprojectfirsttrail.InfoClass;

import java.util.Date;

/**
 * Created by Admin on 10/13/2017.
 */

public class ConsultantInfoClass {
    int id;
    String firstName;
    String lastName;
    String address;
    String phoneNo;
    String emergencyName;
    String emergencyPhone;
    Date joinDate;
    String technology;
    String refrence;
    String email;
    String skype;

    public ConsultantInfoClass() {
    }

    public ConsultantInfoClass(int id, String firstName, String lastName, String address, String phoneNo, String emergencyName, String emergencyPhone, Date joinDate, String technology, String refrence, String email, String skype) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNo = phoneNo;
        this.emergencyName = emergencyName;
        this.emergencyPhone = emergencyPhone;
        this.joinDate = joinDate;
        this.technology = technology;
        this.refrence = refrence;
        this.email = email;
        this.skype = skype;
    }

    public int getID() {
        return id;
    }

    public void setID(int ID) {
        this.id = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmergencyName() {
        return emergencyName;
    }

    public void setEmergencyName(String emergencyName) {
        this.emergencyName = emergencyName;
    }

    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getRefrence() {
        return refrence;
    }

    public void setRefrence(String refrence) {
        this.refrence = refrence;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }
}

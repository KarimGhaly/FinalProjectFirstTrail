package com.example.admin.finalprojectfirsttrail.FragmentClass;

import com.example.admin.finalprojectfirsttrail.InfoClass.ConsultantInfoClass;
import com.example.admin.finalprojectfirsttrail.InfoClass.ContactInfoClass;
import com.example.admin.finalprojectfirsttrail.InfoClass.HousingInfoClass;
import com.example.admin.finalprojectfirsttrail.InfoClass.TeamInfoClass;

import java.util.List;

/**
 * Created by Admin on 10/14/2017.
 */

public class AccountFragClass {
    public ConsultantInfoClass getConsultantInfoClass() {
        return consultantInfoClass;
    }

    public void setConsultantInfoClass(ConsultantInfoClass consultantInfoClass) {
        this.consultantInfoClass = consultantInfoClass;
    }

    public HousingInfoClass getHousingInfoClass() {
        return housingInfoClass;
    }

    public void setHousingInfoClass(HousingInfoClass housingInfoClass) {
        this.housingInfoClass = housingInfoClass;
    }

    public TeamInfoClass getTeamInfoClass() {
        return teamInfoClass;
    }

    public void setTeamInfoClass(TeamInfoClass teamInfoClass) {
        this.teamInfoClass = teamInfoClass;
    }



    public AccountFragClass() {

    }

    ConsultantInfoClass consultantInfoClass;
    HousingInfoClass housingInfoClass;
   public TeamInfoClass teamInfoClass = new TeamInfoClass();
    ContactInfoClass Instuctor_Contact;
    ContactInfoClass TrainingManager_Contact;
    ContactInfoClass MarketingManager_Contact;

    public ContactInfoClass getInstuctor_Contact() {
        return Instuctor_Contact;
    }

    public void setInstuctor_Contact(ContactInfoClass instuctor_Contact) {
        Instuctor_Contact = instuctor_Contact;
    }

    public ContactInfoClass getTrainingManager_Contact() {
        return TrainingManager_Contact;
    }

    public void setTrainingManager_Contact(ContactInfoClass trainingManager_Contact) {
        TrainingManager_Contact = trainingManager_Contact;
    }

    public ContactInfoClass getMarketingManager_Contact() {
        return MarketingManager_Contact;
    }

    public void setMarketingManager_Contact(ContactInfoClass marketingManager_Contact) {
        MarketingManager_Contact = marketingManager_Contact;
    }
}

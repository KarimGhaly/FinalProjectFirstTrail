package com.example.admin.finalprojectfirsttrail.InfoClass;

/**
 * Created by Admin on 10/14/2017.
 */

public class TeamBindingClass {
    String teamRefrence;
    String consultantRefrence;

    public TeamBindingClass() {
    }

    public String getTeamRefrence() {
        return teamRefrence;
    }

    public void setTeamRefrence(String teamRefrence) {
        this.teamRefrence = teamRefrence;
    }

    public String getConsultantRefrence() {
        return consultantRefrence;
    }

    public void setConsultantRefrence(String consultantRefrence) {
        this.consultantRefrence = consultantRefrence;
    }

    public TeamBindingClass(String teamRefrence, String consultantRefrence) {

        this.teamRefrence = teamRefrence;
        this.consultantRefrence = consultantRefrence;
    }
}

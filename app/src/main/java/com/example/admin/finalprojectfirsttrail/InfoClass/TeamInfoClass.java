package com.example.admin.finalprojectfirsttrail.InfoClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 10/14/2017.
 */

public class TeamInfoClass {
    String Team_Name;
    List<String> Team_Members = new ArrayList<>();

    public TeamInfoClass() {
    }

    public String getTeam_Name() {
        return Team_Name;
    }

    public void setTeam_Name(String team_Name) {
        Team_Name = team_Name;
    }

    public List<String> getTeam_Members() {
        return Team_Members;
    }

    public void addTeam_Members(String Consultant_Name)
    {
        Team_Members.add(Consultant_Name);
    }
}

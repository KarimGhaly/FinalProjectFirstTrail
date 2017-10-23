package com.example.admin.finalprojectfirsttrail.FragmentClass;

import com.example.admin.finalprojectfirsttrail.InfoClass.InterviewInfoClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 10/23/2017.
 */

public class InterviewFragClass {
    List<InterviewInfoClass> interviewList = new ArrayList<>();

    public InterviewFragClass() {
    }

    public InterviewFragClass(List<InterviewInfoClass> interviewList) {
        this.interviewList = interviewList;
    }

    public List<InterviewInfoClass> getInterviewList() {
        return interviewList;
    }

    public void setInterviewList(List<InterviewInfoClass> interviewList) {
        this.interviewList = interviewList;
    }
    public void addtoInterviewList(InterviewInfoClass interview)
    {
        interviewList.add(interview);
    }
    public void clearList(){
        interviewList.clear();
    }
}

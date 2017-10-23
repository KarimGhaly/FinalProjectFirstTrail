package com.example.admin.finalprojectfirsttrail.FragmentClass;

import com.example.admin.finalprojectfirsttrail.InfoClass.GradedAssignmentInfoClass;
import com.example.admin.finalprojectfirsttrail.InfoClass.TodayAssigmentInfoClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 10/23/2017.
 */

public class TrainingFragClass {
    float overallGrade;
    TodayAssigmentInfoClass todayAssigmentInfoClass;
    List<GradedAssignmentInfoClass> gradeAssignmentList = new ArrayList<>();

    public TrainingFragClass() {
    }

    public TrainingFragClass(float overallGrade, TodayAssigmentInfoClass todayAssigmentInfoClass) {
        this.overallGrade = overallGrade;
        this.todayAssigmentInfoClass = todayAssigmentInfoClass;
    }

    public float getOverallGrade() {
        return overallGrade;
    }

    public void setOverallGrade(float overallGrade) {
        this.overallGrade = overallGrade;
    }

    public TodayAssigmentInfoClass getTodayAssigmentInfoClass() {
        return todayAssigmentInfoClass;
    }

    public void setTodayAssigmentInfoClass(TodayAssigmentInfoClass todayAssigmentInfoClass) {
        this.todayAssigmentInfoClass = todayAssigmentInfoClass;
    }

    public List<GradedAssignmentInfoClass> getGradeAssignmentList() {
        return gradeAssignmentList;
    }

    public void setGradeAssignmentList(List<GradedAssignmentInfoClass> gradeAssignmentList) {
        this.gradeAssignmentList = gradeAssignmentList;
    }
    public void addGradeList(GradedAssignmentInfoClass grade)
    {
        gradeAssignmentList.add(grade);
    }

    public void clearList(){
        gradeAssignmentList.clear();
    }
}

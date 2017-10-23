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
   List<TodayAssigmentInfoClass> todayAssigmentsList = new ArrayList<>();
    List<GradedAssignmentInfoClass> gradeAssignmentList = new ArrayList<>();

    public TrainingFragClass() {
    }

    public TrainingFragClass(float overallGrade, List<TodayAssigmentInfoClass> todayAssigmentsList, List<GradedAssignmentInfoClass> gradeAssignmentList) {
        this.overallGrade = overallGrade;
        this.todayAssigmentsList = todayAssigmentsList;
        this.gradeAssignmentList = gradeAssignmentList;
    }

    public float getOverallGrade() {
        return overallGrade;
    }

    public void setOverallGrade(float overallGrade) {
        this.overallGrade = overallGrade;
    }

    public List<TodayAssigmentInfoClass> getTodayAssigmentsList() {
        return todayAssigmentsList;
    }

    public void setTodayAssigmentsList(List<TodayAssigmentInfoClass> todayAssigmentsList) {
        this.todayAssigmentsList = todayAssigmentsList;
    }

    public List<GradedAssignmentInfoClass> getGradeAssignmentList() {
        return gradeAssignmentList;
    }

    public void setGradeAssignmentList(List<GradedAssignmentInfoClass> gradeAssignmentList) {
        this.gradeAssignmentList = gradeAssignmentList;
    }

    public void addAssigntmetList(TodayAssigmentInfoClass todayAssigmentInfoClass)
    {
        todayAssigmentsList.add(todayAssigmentInfoClass);
    }

    public void clearAssignmetList(){
        todayAssigmentsList.clear();
    }
    public void addGradeList(GradedAssignmentInfoClass grade)
    {
        gradeAssignmentList.add(grade);
    }

    public void clearList(){
        gradeAssignmentList.clear();
    }
}

package com.example.admin.finalprojectfirsttrail.FragmentClass;

import com.example.admin.finalprojectfirsttrail.InfoClass.InsuranceInfoClass;
import com.example.admin.finalprojectfirsttrail.InfoClass.PTOInfoClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 10/20/2017.
 */

public class BenfitiesFragClass {
    PTOInfoClass ptoInfoClass;
    List<InsuranceInfoClass> insuranceList;

    public BenfitiesFragClass() {
    }

    public BenfitiesFragClass(PTOInfoClass ptoInfoClass, List<InsuranceInfoClass> insuranceList) {
        this.ptoInfoClass = ptoInfoClass;
        this.insuranceList = insuranceList;
    }

    public PTOInfoClass getPtoInfoClass() {
        return ptoInfoClass;
    }

    public void setPtoInfoClass(PTOInfoClass ptoInfoClass) {
        this.ptoInfoClass = ptoInfoClass;
    }

    public List<InsuranceInfoClass> getInsuranceList() {
        return insuranceList;
    }

    public void setInsuranceList(List<InsuranceInfoClass> insuranceList) {
        this.insuranceList = insuranceList;
    }

    public void addtoInsuranceClass(InsuranceInfoClass insuranceInfoClass)
    {
        if(insuranceList == null)
        {
            insuranceList = new ArrayList<>();
        }
        insuranceList.add(insuranceInfoClass);
    }
}

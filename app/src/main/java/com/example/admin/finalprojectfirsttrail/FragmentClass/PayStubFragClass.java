package com.example.admin.finalprojectfirsttrail.FragmentClass;

import com.example.admin.finalprojectfirsttrail.InfoClass.PaySlipInfoClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 10/17/2017.
 */

public class PayStubFragClass {
    List<PaySlipInfoClass> paySlips = new ArrayList<>();

    public PayStubFragClass() {
    }

    public List<PaySlipInfoClass> getPaySlips() {
        return paySlips;
    }

    public void addPaySlip(PaySlipInfoClass paySlipInfoClass){
        paySlips.add(paySlipInfoClass);
    }
    public void clear(){
        paySlips.clear();
    }
}

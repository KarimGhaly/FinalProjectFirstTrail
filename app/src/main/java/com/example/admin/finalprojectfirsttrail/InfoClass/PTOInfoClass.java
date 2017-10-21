package com.example.admin.finalprojectfirsttrail.InfoClass;

/**
 * Created by Admin on 10/20/2017.
 */

public class PTOInfoClass {
    int totalPTO;
    int usedPTO;
    int remainingPTO;

    public PTOInfoClass() {
    }

    public PTOInfoClass(int totalPTO, int usedPTO, int remainingPTO) {
        this.totalPTO = totalPTO;
        this.usedPTO = usedPTO;
        this.remainingPTO = remainingPTO;
    }

    public int getTotalPTO() {
        return totalPTO;
    }

    public void setTotalPTO(int totalPTO) {
        this.totalPTO = totalPTO;
    }

    public int getUsedPTO() {
        return usedPTO;
    }

    public void setUsedPTO(int usedPTO) {
        this.usedPTO = usedPTO;
    }

    public int getRemainingPTO() {
        return remainingPTO;
    }

    public void setRemainingPTO(int remainingPTO) {
        this.remainingPTO = remainingPTO;
    }
}

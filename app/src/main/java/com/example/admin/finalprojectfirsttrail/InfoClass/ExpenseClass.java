package com.example.admin.finalprojectfirsttrail.InfoClass;

/**
 * Created by ios on 10/18/17.
 */

public class ExpenseClass {

        float amount;
        String description;

        public ExpenseClass(float amount, String description) {
            this.amount = amount;
            this.description = description;
        }

        public float getAmount() {
            return amount;
        }

        public void setAmount(float amount) {
            this.amount = amount;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
}

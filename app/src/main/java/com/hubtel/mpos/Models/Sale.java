package com.hubtel.mpos.Models;

import java.io.Serializable;

/**
 * Created by apple on 11/07/16.
 */
public class Sale implements Serializable {

    private static final long serialVersionUID = 7526472295622776147L;


    private String amount;
    private String subject;
    private String customerIdentify;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCustomerIdentify() {
        return customerIdentify;
    }

    public void setCustomerIdentify(String customerIdentify) {
        this.customerIdentify = customerIdentify;
    }
}

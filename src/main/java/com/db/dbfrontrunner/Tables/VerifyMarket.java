package com.db.dbfrontrunner.Tables;

public class VerifyMarket {


    String empid;
    String securityid;
    int broker_price;
    int hours;
    int minutes;

    public  VerifyMarket()
    {}

    public VerifyMarket(String empid, String securityid, int broker_price, int hours, int minutes) {
        this.empid = empid;
        this.securityid = securityid;
        this.broker_price = broker_price;
        this.hours = hours;
        this.minutes = minutes;
    }



    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getSecurityid() {
        return securityid;
    }

    public void setSecurityid(String securityid) {
        this.securityid = securityid;
    }

    public int getBroker_price() {
        return broker_price;
    }

    public void setBroker_price(int broker_price) {
        this.broker_price = broker_price;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }





}

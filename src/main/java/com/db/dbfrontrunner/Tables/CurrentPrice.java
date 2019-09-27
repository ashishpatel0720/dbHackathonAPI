package com.db.dbfrontrunner.Tables;

public class CurrentPrice {




    String securityid;
    int hours;
    int minutes;

    public CurrentPrice()
    {}


    public CurrentPrice(String securityid, int hours, int minutes) {
        this.securityid = securityid;
        this.hours = hours;
        this.minutes = minutes;
    }

    public String getSecurityid() {
        return securityid;
    }

    public void setSecurityid(String securityid) {
        this.securityid = securityid;
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

package com.db.dbfrontrunner.Tables;

public class UserId {

    public String brokerid;
    public UserId(){
        
    }

    public UserId(String brokerid) {
        this.brokerid = brokerid;
    }

    public String getBrokerid() {
        return brokerid;
    }

    @Override
    public String toString() {
        return "userid{" +
                "brokerid='" + brokerid + '\'' +
                '}';
    }

    public void setBrokerid(String brokerid) {
        this.brokerid = brokerid;
    }
}



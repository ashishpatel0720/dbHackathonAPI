package com.db.dbhackathonapi.Tables;

public class UserSecurity {
    public String brokerid;
    public String security;

    public UserSecurity(){

    }

    public UserSecurity(String brokerid, String security) {
        this.brokerid = brokerid;
        this.security = security;
    }

    public String getBrokerid() {
        return brokerid;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public void setBrokerid(String brokerid) {
        this.brokerid = brokerid;
    }
}

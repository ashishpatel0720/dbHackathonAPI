package com.db.dbhackathonapi.Tables;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BrokertradingLimits {

    @Id
    String empid;
    String nameofemployee;
    String desk;
    String designation;
    String sectorlimit;
    String overalllimit;
    String sector1;
    String sector2;
    String password;

    public BrokertradingLimits(){

    }

    public BrokertradingLimits(String empid, String nameofemployee, String desk, String designation, String sectorlimit, String overalllimit, String sector1, String sector2, String password) {
        this.empid = empid;
        this.nameofemployee = nameofemployee;
        this.desk = desk;
        this.designation = designation;
        this.sectorlimit = sectorlimit;
        this.overalllimit = overalllimit;
        this.sector1 = sector1;
        this.sector2 = sector2;
        this.password = password;
    }

    @Override
    public String toString() {
        return "broker_trading_limits{" +
                "empid='" + empid + '\'' +
                ", nameofemployee='" + nameofemployee + '\'' +
                ", desk='" + desk + '\'' +
                ", designation='" + designation + '\'' +
                ", sectorlimit='" + sectorlimit + '\'' +
                ", overalllimit='" + overalllimit + '\'' +
                ", sector1='" + sector1 + '\'' +
                ", sector2='" + sector2 + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getNameofemployee() {
        return nameofemployee;
    }

    public void setNameofemployee(String nameofemployee) {
        this.nameofemployee = nameofemployee;
    }

    public String getDesk() {
        return desk;
    }

    public void setDesk(String desk) {
        this.desk = desk;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getSectorlimit() {
        return sectorlimit;
    }

    public void setSectorlimit(String sectorlimit) {
        this.sectorlimit = sectorlimit;
    }

    public String getOveralllimit() {
        return overalllimit;
    }

    public void setOveralllimit(String overalllimit) {
        this.overalllimit = overalllimit;
    }

    public String getSector1() {
        return sector1;
    }

    public void setSector1(String sector1) {
        this.sector1 = sector1;
    }

    public String getSector2() {
        return sector2;
    }

    public void setSector2(String sector2) {
        this.sector2 = sector2;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

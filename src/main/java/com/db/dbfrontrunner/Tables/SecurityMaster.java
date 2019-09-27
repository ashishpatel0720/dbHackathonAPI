package com.db.dbfrontrunner.Tables;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SecurityMaster {

    String companyname;
    @Id
    String sector;
    String symbol;
    String isinno;
    int marketlot;
    String pricevariantlimit;

    public SecurityMaster(){

    }

    public SecurityMaster(String companyname, String sector, String symbol, String isinno, int marketlot, String pricevariantlimit) {
        this.companyname = companyname;
        this.sector = sector;
        this.symbol = symbol;
        this.isinno = isinno;
        this.marketlot = marketlot;
        this.pricevariantlimit = pricevariantlimit;
    }

    @Override
    public String toString() {
        return "SecurityMaster{" +
                "companyname='" + companyname + '\'' +
                ", sector='" + sector + '\'' +
                ", symbol='" + symbol + '\'' +
                ", isinno='" + isinno + '\'' +
                ", marketlot=" + marketlot +
                ", pricevariantlimit='" + pricevariantlimit + '\'' +
                '}';
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getIsinno() {
        return isinno;
    }

    public void setIsinno(String isinno) {
        this.isinno = isinno;
    }

    public int getMarketlot() {
        return marketlot;
    }

    public void setMarketlot(int marketlot) {
        this.marketlot = marketlot;
    }

    public String getPricevariantlimit() {
        return pricevariantlimit;
    }

    public void setPricevariantlimit(String pricevariantlimit) {
        this.pricevariantlimit = pricevariantlimit;
    }
}

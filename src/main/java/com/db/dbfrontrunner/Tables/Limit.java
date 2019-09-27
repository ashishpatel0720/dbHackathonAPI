package com.db.dbfrontrunner.Tables;

public class Limit {
    public String sector;
    public Double sectorlimit;

    public Limit(String s){

    }

    public Limit(String sector, Double sectorlimit) {
        this.sector = sector;
        this.sectorlimit = sectorlimit;
    }

    @Override
    public String toString() {
        return "Limit{" +
                "sector='" + sector + '\'' +
                ", sectorlimit=" + sectorlimit +
                '}';
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public Double getSectorlimit() {
        return sectorlimit;
    }

    public void setSectorlimit(Double sectorlimit) {
        this.sectorlimit = sectorlimit;
    }
}

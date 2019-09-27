package com.db.dbfrontrunner.Tables;

public class Sectors {
   String sector1;
   String sector2;

    public Sectors(){

    }

    public Sectors(String sector1, String sector2) {
        this.sector1 = sector1;
        this.sector2 = sector2;
    }

    @Override
    public String toString() {
        return "Sectors{" +
                "sector1='" + sector1 + '\'' +
                ", sector2='" + sector2 + '\'' +
                '}';
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
}

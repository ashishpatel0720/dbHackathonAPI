package com.db.dbhackathonapi.Tables;

public class UserSector {
    public String Sector;

    public UserSector(){

    }

    public UserSector(String sector) {
        Sector = sector;
    }

    @Override
    public String toString() {
        return "UserSector{" +
                "Sector='" + Sector + '\'' +
                '}';
    }

    public String getSector() {
        return Sector;
    }

    public void setSector(String sector) {
        Sector = sector;
    }
}

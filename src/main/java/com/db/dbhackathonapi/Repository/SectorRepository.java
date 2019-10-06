package com.db.dbhackathonapi.Repository;

import com.db.dbhackathonapi.Tables.Sectors;

import java.util.List;

public interface SectorRepository {


    List<Sectors> findByEmpid(String empid);

}

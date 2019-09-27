package com.db.dbfrontrunner.Repository;

import com.db.dbfrontrunner.Tables.BrokertradingLimits;
import com.db.dbfrontrunner.Tables.Sectors;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SectorRepository {


    List<Sectors> findByEmpid(String empid);

}

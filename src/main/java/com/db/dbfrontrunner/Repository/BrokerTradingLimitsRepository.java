package com.db.dbfrontrunner.Repository;

import com.db.dbfrontrunner.Tables.BrokertradingLimits;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BrokerTradingLimitsRepository extends CrudRepository<BrokertradingLimits, String> {

     @Query(value = "select REPLACE(sectorlimit, ',', '') from broker_trading_limits where empid = ?" , nativeQuery = true)
     String findByEmpid(String id);
}

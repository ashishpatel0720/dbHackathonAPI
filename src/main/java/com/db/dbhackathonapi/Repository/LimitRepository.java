package com.db.dbhackathonapi.Repository;

import com.db.dbhackathonapi.Tables.BrokertradingLimits;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LimitRepository extends CrudRepository<BrokertradingLimits , String> {

    @Query(value = "select REPLACE(sectorlimit, ',', '') from broker_trading_limits where empid = ?" , nativeQuery = true)
    public String getSectorLimit(String brokerid);
}

package com.db.dbhackathonapi.Repository;

import com.db.dbhackathonapi.Tables.SecurityMaster;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;



public interface SymbolRepository extends CrudRepository<SecurityMaster , String> {

    @Query(value = "select companyname from security_master where symbol = ? ", nativeQuery = true)
    public String getSecurity(String symbol);
}

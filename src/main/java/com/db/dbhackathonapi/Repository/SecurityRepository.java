package com.db.dbhackathonapi.Repository;

import com.db.dbhackathonapi.Tables.SecurityMaster;
import org.springframework.data.repository.CrudRepository;

public interface SecurityRepository extends CrudRepository<SecurityMaster , String>{

}

package com.db.dbfrontrunner.Repository;

import com.db.dbfrontrunner.Tables.SecurityMaster;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SecurityRepository extends CrudRepository<SecurityMaster , String>{

}

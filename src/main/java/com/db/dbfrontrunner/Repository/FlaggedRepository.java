package com.db.dbfrontrunner.Repository;

import com.db.dbfrontrunner.Tables.Orders;
import com.db.dbfrontrunner.Tables.flagged;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FlaggedRepository extends CrudRepository<flagged, String> {
}

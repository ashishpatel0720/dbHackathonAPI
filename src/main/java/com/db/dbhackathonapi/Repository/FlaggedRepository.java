package com.db.dbhackathonapi.Repository;

import com.db.dbhackathonapi.Tables.flagged;
import org.springframework.data.repository.CrudRepository;

public interface FlaggedRepository extends CrudRepository<flagged, String> {
}

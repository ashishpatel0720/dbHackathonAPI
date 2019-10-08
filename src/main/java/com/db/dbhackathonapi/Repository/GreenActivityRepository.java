package com.db.dbhackathonapi.Repository;

import com.db.dbhackathonapi.Tables.GreenActivity;
import com.db.dbhackathonapi.Tables.TravelActivity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface GreenActivityRepository extends CrudRepository<GreenActivity, Integer> {

    List<GreenActivity> findAllByUserEmail(@Param("userEmail") String userEmail);

    @Transactional
    @Modifying
    @Query(value="update green_activity t set t.type=:type, t.ghg_footprint=:ghg_footprint where t.id=:id",nativeQuery = true)
    void modifyUserInfoById(@Param("type") String type,   @Param("ghg_footprint") int ghg_footprint, @Param("id") int id);


}
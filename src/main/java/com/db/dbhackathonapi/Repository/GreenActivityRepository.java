package com.db.dbhackathonapi.Repository;

import com.db.dbhackathonapi.Tables.GreenActivity;
import com.db.dbhackathonapi.Tables.TravelActivity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface GreenActivityRepository extends CrudRepository<GreenActivity, Integer> {

    List<GreenActivity> findAllByUserEmail(@Param("userEmail") String userEmail);

    @Transactional
    @Modifying
    @Query(value="update green_activity t set t.userEmail=:userEmail, t.type =:type, t.suggestedPlantScore=:suggestedPlantScore where t.id=:id",nativeQuery = true)
    void modifyUserInfoById(@Param("userEmail") String userEmail, @Param("type") String type,   @Param("suggestedPlantScore") String suggestedPlantScore, @Param("id") int id);


}
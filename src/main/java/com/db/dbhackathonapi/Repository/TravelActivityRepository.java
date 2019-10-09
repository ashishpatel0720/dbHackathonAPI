package com.db.dbhackathonapi.Repository;

import com.db.dbhackathonapi.Tables.TravelActivity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface TravelActivityRepository extends CrudRepository<TravelActivity, Integer> {

    List<TravelActivity> findAllByUserEmail(@Param("userEmail") String userEmail);

    @Transactional
    @Modifying
    @Query(value="update travel_activity t set t.medium=:medium, t.distance =:distance, t.contributors =:contributors , t.ghg_footprint=:ghg_footprint where t.id=:id",nativeQuery = true)
    void modifyUserInfoById(@Param("medium") String medium, @Param("contributors") int contributors,@Param("distance") int distance, @Param("ghg_footprint") String ghg_footprint, @Param("id") int id);

//    @Query(value="select id,user_email,medium,distance,contributors,ghg_footprint from travel_activity t where t.id=:activityId",nativeQuery = true)
//    void findByActivityId(@Param("activityId") String activityId);

}
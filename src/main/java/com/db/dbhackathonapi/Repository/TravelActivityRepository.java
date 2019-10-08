package com.db.dbhackathonapi.Repository;

import com.db.dbhackathonapi.Tables.TravelActivity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface TravelActivityRepository extends CrudRepository<TravelActivity, String> {

//    @Query("select  from travel_activities where user_email=:userEmail")
    List<TravelActivity> findAllByUserEmail(@Param("userEmail") String userEmail);

    @Modifying
    @Query("update TravelActivity t set t.medium=:medium, t.contributors =:contributors , t.ghgFootprint=:ghgFootprint where t.email=:email")
    void modifyUserInfoById(@Param("medium") String medium, @Param("contributors") int contributors, @Param("ghgFootprint") int ghgFootprint, @Param("email") String email);

}
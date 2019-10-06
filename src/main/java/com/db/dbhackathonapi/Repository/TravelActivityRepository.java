package com.db.dbhackathonapi.Repository;

import com.db.dbhackathonapi.Tables.TravelActivity;
import com.db.dbhackathonapi.Tables.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface TravelActivityRepository extends CrudRepository<TravelActivity, String> {

//    @Query("select  from travel_activities where user_email=:userEmail")
    List<TravelActivity> findAllByUserEmail(@Param("userEmail") String userEmail);
}
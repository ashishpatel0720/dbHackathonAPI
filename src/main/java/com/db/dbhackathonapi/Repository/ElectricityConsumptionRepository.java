package com.db.dbhackathonapi.Repository;

import com.db.dbhackathonapi.Tables.ElectricityConsumption;
import com.db.dbhackathonapi.Tables.TravelActivity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ElectricityConsumptionRepository extends CrudRepository<ElectricityConsumption, Integer> {

    List<ElectricityConsumption> findAllByUserEmail(@Param("userEmail") String userEmail);

    @Transactional
    @Modifying
    @Query(value="update electricity_consumption t set t.appliance_type=:appliance_type, t.duration_minutes =:duration_minutes, t.contributors =:contributors , t.ghg_footprint=:ghg_footprint where t.id=:id",nativeQuery = true)
    void modifyUserInfoById(@Param("appliance_type") String appliance_type, @Param("duration_minutes") int duration_minutes, @Param("contributors") int contributors, @Param("ghg_footprint") int ghg_footprint, @Param("id") int id);

}
package com.db.dbfrontrunner.Repository;

import com.db.dbfrontrunner.Tables.Orders;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserOrdersRepository extends CrudRepository<Orders , String> {
    List<Orders> findByBroker(String id);
}

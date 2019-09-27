package com.db.dbfrontrunner.Repository;

import com.db.dbfrontrunner.Tables.Orders;

import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Orders, String> {
}

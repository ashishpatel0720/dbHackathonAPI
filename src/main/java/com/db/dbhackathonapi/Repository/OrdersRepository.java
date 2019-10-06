package com.db.dbhackathonapi.Repository;

import com.db.dbhackathonapi.Tables.Orders;

import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Orders, String> {
}

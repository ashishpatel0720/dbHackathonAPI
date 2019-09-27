package com.db.dbfrontrunner.Repository;

import com.db.dbfrontrunner.Tables.Orders;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExecuteRepository extends CrudRepository<Orders, String> {

    @Query(value = "select sum(value) from orders where broker = ? and security = ?" , nativeQuery = true)
    Double getSum(String id, String sec);

    List<Orders> findBybrokerAndSecurity(String id, String sec);
}

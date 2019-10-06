package com.db.dbhackathonapi.Controller;


import com.db.dbhackathonapi.Tables.Orders;
import com.db.dbhackathonapi.Repository.UserOrdersRepository;
import com.db.dbhackathonapi.Tables.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserOrdersController {

    @Autowired
    UserOrdersRepository user_orders;

    @CrossOrigin //Todo:
    @PostMapping("/orders")
    public List<Orders> user_orders(@RequestBody UserId data){
        System.out.println(data.brokerid);
         List<Orders> orders = user_orders.findByBroker(data.brokerid);
        return orders;

    }



}

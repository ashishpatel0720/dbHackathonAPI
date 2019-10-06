package com.db.dbhackathonapi.Controller;


import com.db.dbhackathonapi.Repository.FlaggedRepository;
import com.db.dbhackathonapi.Tables.Orders;
import com.db.dbhackathonapi.Repository.OrdersRepository;
import com.db.dbhackathonapi.Tables.flagged;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrdersController {

    @Autowired
    OrdersRepository ordersrepository;


    @Autowired
    FlaggedRepository flaggedRepository;

    @CrossOrigin //Todo:
    @RequestMapping(value = "/orders" , method = RequestMethod.GET)
    public List<Orders> Order_List (){

        List<Orders> Orders = (List<Orders>) ordersrepository.findAll();
        System.out.println(Orders);
        return Orders;

    }

    @CrossOrigin
    @RequestMapping(value = "/flagged", method = RequestMethod.GET)
    public List<flagged> flagged_List(){
        List<flagged> flaggedorders = (List<flagged>)flaggedRepository.findAll();
        return flaggedorders;

    }



}

package com.db.dbfrontrunner.Controller;


import com.db.dbfrontrunner.Repository.BrokerTradingLimitsRepository;
import com.db.dbfrontrunner.Repository.OrdersBuyRepository;
import com.db.dbfrontrunner.Tables.UserSecurity;
import com.db.dbfrontrunner.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/users")
public class OrdersBuySellController {

    @Autowired
    OrdersBuyRepository ordersBuy;
    @Autowired
    BrokerTradingLimitsRepository broker_trading_limit;


    @CrossOrigin //Todo:
    @PostMapping("/orders/buy")
    public Response buy(@RequestBody UserSecurity user){

        String seclimit = broker_trading_limit.findByEmpid(user.brokerid);
        Double result = Double.parseDouble(seclimit);
        System.out.println(seclimit);
        double amount = ordersBuy.getSum(user.brokerid , user.security);
        Double remaining_amount= result - amount;
        return new Response(1,"Logged In.","You have successfully Signed In",remaining_amount);
    }


    @CrossOrigin //Todo:
    @PostMapping("/orders/sell")
    public Response sell(@RequestBody UserSecurity user){

        String seclimit = broker_trading_limit.findByEmpid(user.brokerid);
        Double result = Double.parseDouble(seclimit);
        System.out.println(seclimit);
        double amount = ordersBuy.getSum(user.brokerid , user.security);
        Double remaining_amount= result - amount;
        return new Response(1,"Logged In.","You have successfully Signed In",remaining_amount);
    }




}

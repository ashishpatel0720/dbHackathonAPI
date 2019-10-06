package com.db.dbhackathonapi.Controller;


import com.db.dbhackathonapi.Implementation.VerifyMarketImpl;
import com.db.dbhackathonapi.Repository.BrokerTradingLimitsRepository;
import com.db.dbhackathonapi.Repository.ExecuteRepository;
import com.db.dbhackathonapi.Repository.FlaggedRepository;
import com.db.dbhackathonapi.Repository.SymbolRepository;
import com.db.dbhackathonapi.Tables.Orders;
import com.db.dbhackathonapi.Tables.UserData;
import com.db.dbhackathonapi.Tables.flagged;
import com.db.dbhackathonapi.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/users")
public class ExecuteController {

    @Autowired
    ExecuteRepository execute;
    @Autowired
    BrokerTradingLimitsRepository broker_trading_limit;
    @Autowired
    SymbolRepository symbrep;
    @Autowired
    VerifyMarketImpl verify;
    @Autowired
    FlaggedRepository flaggedRepository;

	 @CrossOrigin //Todo:
    @PostMapping("/orders/execute")
    public Response executeOrders(@RequestBody UserData UserData){
        String seclimit = broker_trading_limit.findByEmpid(UserData.brokerid);
        Double result = Double.parseDouble(seclimit);
        String security = symbrep.getSecurity(UserData.symbol);
        double value = execute.getSum(UserData.brokerid , security);


        Double remaining_amount= result - value;

       float response = verify.findBySymbolandHoursandMinutes(UserData.getSymbol() , UserData.hours , UserData.minutes);



        if((UserData.getPrice() > (response * 1.02)) || (UserData.getPrice() < (response * 0.98))){

            flagged flag = new flagged(UserData.clientname , security , "19-Jul",null, UserData.quantity,"LIMIT", UserData.price/ UserData.quantity , UserData.direction , UserData.price, UserData.brokerid , UserData.isinno);
            flaggedRepository.save(flag);
            return new Response(2,"not executed","High variance in order",value);

        }
        else{

            if((remaining_amount - (UserData.getPrice() * UserData.getQuantity()) >= 0)){



                Orders new_order=new Orders(UserData.clientname , security , "19-Jul",null, UserData.quantity,"LIMIT", UserData.price/ UserData.quantity , UserData.direction , UserData.price, UserData.brokerid , UserData.isinno);

                execute.save(new_order);

                return new Response(1,"executed","You have successfully Order execution",remaining_amount- UserData.price);
            }

            else{
                return new Response(0,"not executed","You have exceded the limit",remaining_amount);
            }
        }
        }


}

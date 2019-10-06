package com.db.dbhackathonapi.Controller;

import com.db.dbhackathonapi.Implementation.VerifyMarketImpl;
import com.db.dbhackathonapi.Tables.CurrentPrice;
import com.db.dbhackathonapi.response.Response;
import com.db.dbhackathonapi.Tables.VerifyMarket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController    // This means that this class is a Controller
@RequestMapping(path="/verify") // This means URL's start wit
public class VerifyMarketController {
    @Autowired
    VerifyMarketImpl verifyMarketImpl;

    @CrossOrigin //Todo:
    @PostMapping("/variance")
    public Response verify_price(@RequestBody VerifyMarket market)
    {


        String symbol = market.getSecurityid();
        float broker_price = market.getBroker_price();
        int hours = market.getHours();
        int minutes = market.getMinutes();
        String empid = market.getEmpid();
        float current_price;

        try {

            current_price  = verifyMarketImpl.findBySymbolandHoursandMinutes(symbol, hours, minutes);

        }

        catch (Exception e)
        {
            Response response=new Response(3,"Compliance Verification","Query Error",null);
            return response;
        }

        if (verifyvariance(broker_price,current_price))
        {
            Response response=new Response(1,"Compliance Verification","Compliance Verified Set Price is within limits",null);
            return response;

        }
        String poss_range = new String(0.98*current_price+":"+1.02*current_price);
        Response response=new Response(2,"Compliance Verification","Price is non-compliance it should be within 2% limit of market price",poss_range);



        return response;


    }


    @CrossOrigin
    @PostMapping("/currentprice")
    public Response current_price(@RequestBody CurrentPrice price)
    {
        String symbol = price.getSecurityid();
        int hours = price.getHours();
        int minutes = price.getMinutes();
        float current_price;
        try {

            current_price  = verifyMarketImpl.findBySymbolandHoursandMinutes(symbol, hours, minutes);

        }

        catch (Exception e)
        {
            Response response=new Response(2,"Current Price ","Query Error",null);
            return response;
        }


        Response response = new Response(1,"Current Price",Float.toString(current_price),null);

        return response;



    }

    boolean verifyvariance(float broker_price,float current_price)
    {
        if (broker_price < 1.02*current_price && broker_price > 0.98*current_price)
        {
            return true;

        }

        return false;
    }


}

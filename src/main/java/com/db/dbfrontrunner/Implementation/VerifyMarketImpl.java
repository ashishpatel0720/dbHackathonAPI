package com.db.dbfrontrunner.Implementation;

import com.db.dbfrontrunner.Repository.VerifyMarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class VerifyMarketImpl implements VerifyMarketRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public float findBySymbolandHoursandMinutes(String symbol, int hours, int minutes) {

        String sql="select avg(stockcol) from stock where symbol=? and hours=? and minutes>? and minutes<=?";
        float current_price=jdbcTemplate.queryForObject(sql,new Object[]{symbol,hours,minutes-3,minutes},float.class);
        return current_price;
    }


}

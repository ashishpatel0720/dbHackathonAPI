package com.db.dbhackathonapi.Implementation;

import com.db.dbhackathonapi.Repository.SectorRepository;
import com.db.dbhackathonapi.Tables.Sectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SectorsImpl implements SectorRepository {


    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public List<Sectors> findByEmpid(String empid) {
        String sql="select b.sector1 , b.sector2  from broker_trading_limits b where empid = ?";

        List<Sectors> sector= (List<Sectors>) jdbcTemplate.query(sql,new Object[]{empid},new BeanPropertyRowMapper(Sectors.class));

        return sector;
    }
}

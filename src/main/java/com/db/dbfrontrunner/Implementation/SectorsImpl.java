package com.db.dbfrontrunner.Implementation;

import com.db.dbfrontrunner.Repository.SectorRepository;
import com.db.dbfrontrunner.Repository.SecurityRepository;
import com.db.dbfrontrunner.Tables.Sectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

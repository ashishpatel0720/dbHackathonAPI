package com.db.dbfrontrunner.Controller;

import com.db.dbfrontrunner.Implementation.SectorsImpl;
import com.db.dbfrontrunner.Repository.LimitRepository;
import com.db.dbfrontrunner.Repository.SectorRepository;
import com.db.dbfrontrunner.Repository.SecurityRepository;
import com.db.dbfrontrunner.Tables.Limit;
import com.db.dbfrontrunner.Tables.Sectors;
import com.db.dbfrontrunner.Tables.SecurityMaster;
import com.db.dbfrontrunner.Tables.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class LimitController {

    @Autowired
    SectorsImpl sectorsimpl;
    @Autowired
    SectorsImpl sectors;
    @Autowired
    LimitRepository limitrep;
    @Autowired
    SectorRepository secrep;

    @CrossOrigin
    @PostMapping("users/limits")
    public  List<Optional<Limit>> getSectors(@RequestBody UserId user){

        List<Optional<Limit>> data=new ArrayList<>();
        List<Sectors> sector = sectorsimpl.findByEmpid(user.brokerid);

        String sectorlimit = limitrep.getSectorLimit(user.brokerid);
        Double seclim = Double.parseDouble(sectorlimit);

        List<Sectors> value = sectors.findByEmpid(user.brokerid);
        System.out.println(value.get(0).getSector1());



        data.add(Optional.of(new Limit(value.get(0).getSector1().toString(), seclim)));
        data.add(Optional.of(new Limit(value.get(0).getSector2().toString(), seclim)));
        return data;

    }
}

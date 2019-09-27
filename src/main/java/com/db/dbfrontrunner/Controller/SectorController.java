package com.db.dbfrontrunner.Controller;

import com.db.dbfrontrunner.Implementation.SectorsImpl;
import com.db.dbfrontrunner.Repository.SecurityRepository;
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
public class SectorController {


    @Autowired
    SectorsImpl sectorsimpl;
    @Autowired
    SecurityRepository secrep;

    @CrossOrigin //Todo:
    @PostMapping("/users/securities")
    public List<Optional<SecurityMaster>> getSectors(@RequestBody UserId user){

        List<Optional<SecurityMaster>> data=new ArrayList<>();
        List<Sectors> sector = sectorsimpl.findByEmpid(user.brokerid);
         // System.out.println(sector.get(0).getSector1());
        //System.out.println(secrep.findById(sector.get(0).getSector1()));

         data.add(0,secrep.findById(sector.get(0).getSector1())) ;
         data.add(1,secrep.findById(sector.get(0).getSector2())) ;

         return data;
    }

}

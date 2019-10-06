package com.db.dbhackathonapi.Controller;


import com.db.dbhackathonapi.Repository.SecurityRepository;
import com.db.dbhackathonapi.Tables.SecurityMaster;
import com.db.dbhackathonapi.Tables.UserSector;
import com.db.dbhackathonapi.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class SecurityController {

    @Autowired
    SecurityRepository secrep;

    @CrossOrigin //Todo:
    @PostMapping("/users/sector/security")
    public Response getSecurity(@RequestBody UserSector sector){
        Optional<SecurityMaster> data =  secrep.findById(sector.Sector);
        return new Response(1,"Securities","You Securities",data);
    }

}

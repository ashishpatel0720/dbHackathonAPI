package com.db.dbhackathonapi.Controller;


import com.db.dbhackathonapi.Repository.AdminRepository;
import com.db.dbhackathonapi.Repository.TravelActivityRepository;
import com.db.dbhackathonapi.Tables.Admin;
import com.db.dbhackathonapi.Tables.TravelActivity;
import com.db.dbhackathonapi.Tables.User;
import com.db.dbhackathonapi.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.db.dbhackathonapi.StatusCodeEnum.*;


/*
either you can use
 */

@RestController    // This means that this class is a Controller
@RequestMapping(path="/activities") // This means URL's start with /demo (after Application path)
public class ActivityController {

	@Autowired
	private TravelActivityRepository travelActivityRepository;

	@CrossOrigin //Todo:
 	@GetMapping(value = "/travel/{userEmail}")
	public Response fetchTravelData( @PathVariable String userEmail){

		List<TravelActivity>activities=travelActivityRepository.findAllByUserEmail(userEmail);

		return new Response(OK.getCode(),"Travel data","Travel Data has "+activities.size()+" rows",activities);
		}
}
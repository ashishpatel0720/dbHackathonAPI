package com.db.dbhackathonapi.Controller;


import com.db.dbhackathonapi.Repository.AdminRepository;
import com.db.dbhackathonapi.Repository.TravelActivityRepository;
import com.db.dbhackathonapi.Tables.Admin;
import com.db.dbhackathonapi.Tables.TravelActivity;
import com.db.dbhackathonapi.Tables.User;
import com.db.dbhackathonapi.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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
	public Response getTravelData( @PathVariable String userEmail){

		List<TravelActivity>activities=travelActivityRepository.findAllByUserEmail(userEmail);

		return new Response(OK,"Travel data","Travel Data has "+activities.size()+" rows",activities);
	}

	@CrossOrigin //Todo:
	@GetMapping(value = "/types")
	public Response getActivityTypes( ){

		List<String>activities= Arrays.asList("Travel");

		return new Response(OK,"Activity Types","Types of activities could be configured here, total "+activities.size()+" rows",activities);
	}

	@CrossOrigin //Todo:
	@GetMapping(value = "/types/travel")
	public Response getTravelActivityTypes( ){

		List<String>activities= Arrays.asList("Bicycle", "Motor Bike","Car","Cab","Bus");

		return new Response(OK,"Activity Types","Types of Travel activities could be configured here, total "+activities.size()+" rows",activities);
	}

}
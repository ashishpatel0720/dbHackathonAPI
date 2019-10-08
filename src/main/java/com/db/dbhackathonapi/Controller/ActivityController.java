package com.db.dbhackathonapi.Controller;


import com.db.dbhackathonapi.Constants;
import com.db.dbhackathonapi.Repository.ElectricityConsumptionRepository;
import com.db.dbhackathonapi.Repository.GreenActivityRepository;
import com.db.dbhackathonapi.Repository.TravelActivityRepository;
import com.db.dbhackathonapi.Tables.TravelActivity;
import com.db.dbhackathonapi.interfaces.Activity;
import com.db.dbhackathonapi.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

	@Autowired
	private GreenActivityRepository greenActivityRepository;

	@Autowired
	private ElectricityConsumptionRepository electricityConsumptionRepository;

	@CrossOrigin
	@GetMapping(value = "/{userEmail}")
	public Response getAllActivityData( @PathVariable String userEmail){
		try{
			List<Activity>activities=new ArrayList<>();
			activities.addAll(travelActivityRepository.findAllByUserEmail(userEmail));
			activities.addAll(greenActivityRepository.findAllByUserEmail(userEmail));
			activities.addAll(electricityConsumptionRepository.findAllByUserEmail(userEmail));
			return new Response(OK,"All Activity data","All Activity Data has "+activities.size()+" rows",activities);
		}catch (Exception e){
			return new Response(ERROR, e.getMessage(), Arrays.toString(e.getStackTrace()), null);
		}
	}

	@CrossOrigin
	@GetMapping(value = "/types")
	public Response getAllActivityTypes( ){
		try{
			List<String>activities=new ArrayList<>();
			activities.addAll(Constants.greenActivityList);
			activities.addAll(Constants.travelActivityList);
			activities.addAll(Constants.electricApplianceList);
			return new Response(OK,"All Activity Types","Types of All activities are total "+activities.size()+" rows",activities);
		}catch (Exception e){
			return new Response(ERROR, e.getMessage(), Arrays.toString(e.getStackTrace()), null);
		}
	}
}
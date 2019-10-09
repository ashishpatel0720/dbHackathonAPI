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

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.db.dbhackathonapi.StatusCodeEnum.*;
import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;


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
			activities.addAll(
					travelActivityRepository.findAllByUserEmail(
							userEmail).stream()
							.map(travelActivity -> {
								travelActivity.setType("travel-activity");
								return travelActivity;
							}).collect(Collectors.toList())
			);

			activities.addAll(
					greenActivityRepository.findAllByUserEmail(
							userEmail).stream()
							.map(travelActivity -> {
								travelActivity.setType("green-activity");
								return travelActivity;
							}).collect(Collectors.toList())
			);
			activities.addAll(
					electricityConsumptionRepository.findAllByUserEmail(
							userEmail).stream()
							.map(travelActivity -> {
								travelActivity.setType("electricity-consumption");
								return travelActivity;
							}).collect(Collectors.toList())
			);
			return new Response(OK,"All Activity data","All Activity Data has "+activities.size()+" rows",activities);
		}catch (Exception e){
			return new Response(ERROR, e.getMessage(), Arrays.toString(e.getStackTrace()), null);
		}
	}

	@CrossOrigin
	@GetMapping(value = "/score/{userEmail}") //monthly score
	public Response getAverageScore(@PathVariable String userEmail){
		try{
			//find activities this month
			// calculate score
			// return back the response
			return new Response(OK,"All Activity Score","All Activity Score ",100);
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
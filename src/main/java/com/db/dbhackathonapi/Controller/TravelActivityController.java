package com.db.dbhackathonapi.Controller;


import com.db.dbhackathonapi.Constants;
import com.db.dbhackathonapi.Repository.TravelActivityRepository;
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
@RequestMapping(path="/activities/travel-activity") // This means URL's start with /demo (after Application path)
public class TravelActivityController {

	@Autowired
	private TravelActivityRepository travelActivityRepository;

	@CrossOrigin
	@GetMapping(value = "/{userEmail}")
	public Response getTravelData( @PathVariable String userEmail){
		try{
			List<TravelActivity>activities=travelActivityRepository.findAllByUserEmail(userEmail);
			return new Response(OK,"Travel data","Travel Data has "+activities.size()+" rows",activities);
		}catch (Exception e){
			return new Response(ERROR, e.getMessage(), Arrays.toString(e.getStackTrace()), null);
		}
	}

	@CrossOrigin
	@GetMapping(value = "/types")
	public Response getTravelActivityTypes( ){
		try{
			List<String>activities= Constants.travelActivityList;
			return new Response(OK,"Travel Activity Types","Types of Travel activities could be configured here, total "+activities.size()+" rows",activities);
		}catch (Exception e){
			return new Response(ERROR, e.getMessage(), Arrays.toString(e.getStackTrace()), null);
		}
	}

	@CrossOrigin
	@PostMapping("/add")
	public Response addActivity(@RequestBody TravelActivity travelActivity) {

		try {
			TravelActivity activity = travelActivityRepository.save(travelActivity);
			return new Response(OK, "Added .", "Travel Activity Added", activity);
		} catch (Exception e) {
			return new Response(ERROR, "Error", Arrays.toString(e.getStackTrace()), null);
		}
	}

	@CrossOrigin
	@PostMapping("/modify")
	public Response modifyActivity(@RequestBody TravelActivity travelActivity) {

		System.out.println(travelActivity);
		Optional<TravelActivity> u=travelActivityRepository.findById(travelActivity.getId());
		if(!u.isPresent()) {
			return new Response(WARNING,"Not Exists","Row Not Exists, Id:"+travelActivity.getId(),null);
		}

		try {
			travelActivityRepository.modifyUserInfoById(
					travelActivity.getMedium(),
					travelActivity.getContributors(),
					travelActivity.getDistance(),
					travelActivity.getGhgFootprint(),
					travelActivity.getId()
			);

			return new Response(OK, "Modified .", "Travel Activity Modified", travelActivity);
		} catch (Exception e) {
			return new Response(ERROR, e.getMessage(), Arrays.toString(e.getStackTrace()), travelActivity);
		}
	}

	@CrossOrigin
	@PostMapping("/delete")
	public Response deleteActivity(@RequestBody TravelActivity travelActivity) {
		Optional<TravelActivity> u=travelActivityRepository.findById(travelActivity.getId());
		if(!u.isPresent()) {
			return new Response(WARNING,"Not Exists","Row Not Exists, Id:"+travelActivity.getId(),null);
		}
		try {
			travelActivityRepository.deleteById(travelActivity.getId());
			return new Response(OK, "Deleted", "Travel Activity Deleted", travelActivity);
		} catch (Exception e) {
			return new Response(ERROR, e.getMessage(), Arrays.toString(e.getStackTrace()), travelActivity);
		}
	}
}
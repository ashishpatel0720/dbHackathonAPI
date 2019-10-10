package com.db.dbhackathonapi.Controller;


import com.db.dbhackathonapi.Constants;
import com.db.dbhackathonapi.Repository.TravelActivityRepository;
import com.db.dbhackathonapi.Tables.TravelActivity;
import com.db.dbhackathonapi.Tables.User;
import com.db.dbhackathonapi.Utils;
import com.db.dbhackathonapi.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

import static com.db.dbhackathonapi.StatusCodeEnum.*;


/*
either you can use
 */

@RestController    // This means that this class is a Controller
@RequestMapping(path="/activities/travel-activity") // This means URL's start with /demo (after Application path)
public class TravelActivityController {

	@Autowired
	private TravelActivityRepository travelActivityRepository;
    @Autowired
    private Utils utils;

	@CrossOrigin
	@GetMapping(value = "/{userEmail}")
	public Response getTravelData( @PathVariable String userEmail){
		try{
			List<TravelActivity> activities = travelActivityRepository.findAllByUserEmail(userEmail);
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
			travelActivity.setGhgFootprint(calculateGHG(travelActivity));
			travelActivity.setTimestamp(new Timestamp(new Date().getTime()));
			TravelActivity activity = travelActivityRepository.save(travelActivity);
			return new Response(OK, "Added .", "Travel Activity Added", activity);
		} catch (Exception e) {
			return new Response(ERROR, e.getMessage(), Arrays.toString(e.getStackTrace()), null);
		}
	}

	private String calculateGHG(TravelActivity travelActivity) {

    /*petrol - 2.322 kgCO2/ltre
  car - petrol
    distance  - 5000 km/yr
    avg 15 km/lt =applianceFactor
            ltres = 5000/15 = 333.33 ltrs

    car disel
    avg 20 km/ltre


    petrol - 30 ltres
    emission - 300 * 2.322 = 696.6 kgCO2
    in tonnes = 696.6 /908 = 0.767 ----> ans

0.767 + 0.2 +0.7+ 0.9+10=red


            treesrequired = tonnes / 5
    mintu = 2.45 tonnes
    no of trees for mintu = 2.45/5 = 0.49 trees per year = green
*/
/*
        private String medium;
        private int distance;
        private int contributors;
*/

        Map<String,String> applianceFactorsMap = utils.getMapFactors("metaData");
        Map<String,String> travelMediumMap = utils.getMapFactors("travelMedium");
        String travelFuelType = travelActivity.getFuelType()!=null ? travelActivity.getFuelType().toUpperCase(): "OTHER";
        String travelMedium = travelActivity.getMedium()!=null ? travelActivity.getMedium().toUpperCase(): "OTHER";

       float ghgValue = ((Float.parseFloat(travelMediumMap.get(travelMedium))* Float.parseFloat(travelActivity.getDistance()))/travelActivity.getContributors()
                        * Float.parseFloat(applianceFactorsMap.get(travelFuelType)))/ 908;

        return String.valueOf(ghgValue);
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
			travelActivity.setGhgFootprint(calculateGHG(travelActivity));
			travelActivityRepository.modifyUserInfoById(
					travelActivity.getMedium(),
					travelActivity.getFuelType(),
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

private Optional<TravelActivity> getValidUser(TravelActivity travelActivity)
{
	Optional<TravelActivity> u=travelActivityRepository.findById(travelActivity.getId());
		return u;
}


	@CrossOrigin
	@PostMapping("/getTravelAvgScore")
	public Response getTravelAvgScore(@RequestBody TravelActivity travelActivity) {
		Optional<TravelActivity> u=getValidUser(travelActivity);
		if(!u.isPresent()) {
			return new Response(WARNING,"Not Exists","Row Not Exists, Id:"+travelActivity.getId(),null);
		}
		try {
			return new Response(OK, "Deleted", "Travel score value", u);
		} catch (Exception e) {
			return new Response(ERROR, e.getMessage(), Arrays.toString(e.getStackTrace()), u);
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
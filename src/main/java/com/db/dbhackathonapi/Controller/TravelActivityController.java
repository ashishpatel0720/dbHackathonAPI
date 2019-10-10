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
import java.text.DateFormatSymbols;

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
			Collections.sort(activities,new Comparator<TravelActivity>() {
				@Override
				public int compare(TravelActivity o1, TravelActivity o2) {
					long t1 = o1.getTimestamp() != null ? o1.getTimestamp().getTime() : 0L;
					long t2 = o2.getTimestamp() != null ? o2.getTimestamp().getTime() : 0L;
					if (t2 > t1)
						return 1;
					else if (t1 > t2)
						return -1;
					else
						return 0;
				}});
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
            travelActivity.setTimestamp(new Timestamp(new Date().getTime()));
			travelActivity.setGhgFootprint(calculateGHG(travelActivity));
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


	@CrossOrigin
	@GetMapping(value = "/sixMonthAvgScore/{userEmail}")
	public Response getSixMonthAvgScore( @PathVariable String userEmail) {

		try {
			List<TravelActivity> activities = travelActivityRepository.findAllByUserEmail(userEmail);
			Map<String ,String> dataMap =  new HashMap<>();
			Map<String,String> monthMap = new HashMap<>();
			int j=1;
			String[] shortMonths = new DateFormatSymbols().getShortMonths();
			for (String shortMonth : shortMonths) {
				monthMap.put(String.valueOf(j),shortMonth);
				j++;
			}
			int month = new Timestamp(System.currentTimeMillis()).getMonth();
			for(int i=month-6; i<month;i++)
			dataMap.put(String.valueOf(monthMap.get(String.valueOf(i))),"0");

			for(TravelActivity travelActivity :activities)
			{
				if(travelActivity.getTimestamp()!=null)
				{
					//listMonthMap.put(String.valueOf(travelActivity.getTimestamp().getMonth()),String.valueOf(1));
					if(travelActivity.getTimestamp().getMonth() > (new Timestamp(System.currentTimeMillis()).getMonth() - 6))
					{
						if(dataMap.containsKey(String.valueOf(travelActivity.getTimestamp().getMonth())))
						dataMap.put(monthMap.get(String.valueOf(travelActivity.getTimestamp().getMonth())),
								String.valueOf((Float.parseFloat(dataMap.get(String.valueOf(travelActivity.getTimestamp().getMonth())))
										+(travelActivity.getGhgFootprint()!=null?Float.parseFloat(travelActivity.getGhgFootprint()):0.0f))));
						else
							dataMap.put(monthMap.get(String.valueOf(travelActivity.getTimestamp().getMonth())),String.valueOf(travelActivity.getGhgFootprint()));
					}
				}

			}

			return new Response(OK, "Calculated .", "Travel Activity Calculated", dataMap);
		}
		catch (Exception e) {
			return new Response(ERROR, e.getMessage(), Arrays.toString(e.getStackTrace()), null);
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
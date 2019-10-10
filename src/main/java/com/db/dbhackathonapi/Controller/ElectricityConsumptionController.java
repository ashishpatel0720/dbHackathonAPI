package com.db.dbhackathonapi.Controller;


import com.db.dbhackathonapi.CompareByTimeStamp;
import com.db.dbhackathonapi.Constants;
import com.db.dbhackathonapi.Repository.ElectricityConsumptionRepository;
import com.db.dbhackathonapi.Repository.TravelActivityRepository;
import com.db.dbhackathonapi.Tables.ElectricityConsumption;
import com.db.dbhackathonapi.Tables.TravelActivity;
import com.db.dbhackathonapi.Utils;
import com.db.dbhackathonapi.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.util.*;
import java.util.Date;

import static com.db.dbhackathonapi.StatusCodeEnum.*;


/*
either you can use
 */

@RestController    // This means that this class is a Controller
@RequestMapping(path="/activities/electricity-consumption") // This means URL's start with /demo (after Application path)
public class ElectricityConsumptionController {

	@Autowired
	private ElectricityConsumptionRepository electricityConsumptionRepository;
    @Autowired
    private Utils utils;

    @CrossOrigin
    @GetMapping(value = "/{userEmail}")
    public Response getData(@PathVariable String userEmail) {
        System.out.println("hi hello ");
        try {
            List<ElectricityConsumption> activities = electricityConsumptionRepository.findAllByUserEmail(userEmail);
            Collections.sort(activities,new Comparator<ElectricityConsumption>() {
                @Override
                public int compare(ElectricityConsumption o1, ElectricityConsumption o2) {
                    long t1 = o1.getTimestamp() != null ? o1.getTimestamp().getTime() : 0L;
                    long t2 = o2.getTimestamp() != null ? o2.getTimestamp().getTime() : 0L;
                    if (t2 > t1)
                        return 1;
                    else if (t1 > t2)
                        return -1;
                    else
                        return 0;
                }});
            return new Response(OK, "Electricity data", "Electricity Data has " + activities.size() + " rows", activities);

        } catch (Exception e) {
            return new Response(ERROR, e.getMessage(), Arrays.toString(e.getStackTrace()), null);

        }
    }

    @CrossOrigin
    @GetMapping(value = "/types")
    public Response getTravelActivityTypes() {
        try {
            List<String> activities = Constants.electricApplianceList;

			return new Response(OK,"Electricity Consumption Types","Types of Electricity Consumption could be configured here, total "+activities.size()+" rows",activities);
		}catch (Exception e){
			return new Response(ERROR, e.getMessage(), Arrays.toString(e.getStackTrace()), null);
		}
	}

    @CrossOrigin
    @PostMapping("/add")
    public Response addActivity(@RequestBody ElectricityConsumption electricityConsumption) {


		try {
            electricityConsumption.setGhgFootprint(calculateGHG(electricityConsumption));
			electricityConsumption.setTimestamp(new Timestamp(new Date().getTime()));
            ElectricityConsumption activity = electricityConsumptionRepository.save(electricityConsumption);
            return new Response(OK, "Added .", "Electricity Consumption Added", activity);
        } catch (Exception e) {
            return new Response(ERROR, e.getMessage(), Arrays.toString(e.getStackTrace()), null);
        }
    }

    @CrossOrigin
    @PostMapping("/getAvgElecScore")
    public Response getAvgElecScore(@RequestBody ElectricityConsumption electricityConsumption) {


        Optional<ElectricityConsumption> u = electricityConsumptionRepository.findById(electricityConsumption.getId());

        if (!u.isPresent())
            return new Response(WARNING, "Not Exists", "Row Not Exists, Id:" + electricityConsumption.getId(), null);
        try {
            return new Response(OK, "Modified .", "Electricity Consumption Modified", electricityConsumption);
        } catch (Exception e) {
            return new Response(ERROR, e.getMessage(), Arrays.toString(e.getStackTrace()), electricityConsumption);
        }

    }

    private String calculateGHG(ElectricityConsumption electricityConsumption) {

        Map<String,String > applianceFactors = utils.getMapFactors("applianceFactors");
        String applianceType = electricityConsumption.getApplianceType() !=null ?electricityConsumption.getApplianceType().toUpperCase():"OTHER";
        float ghgValue = ((Float.parseFloat(applianceFactors.get(applianceType)) *
                (Float.parseFloat(electricityConsumption.getDurationMinutes())/60))/electricityConsumption.getContributors())* 0.5452f;

        return String.valueOf(ghgValue);
    }

    @CrossOrigin
    @PostMapping("/modify")
    public Response modifyActivity(@RequestBody ElectricityConsumption electricityConsumption) {

        System.out.println(electricityConsumption);

        Optional<ElectricityConsumption> u = electricityConsumptionRepository.findById(electricityConsumption.getId());

        if (!u.isPresent())
            return new Response(WARNING, "Not Exists", "Row Not Exists, Id:" + electricityConsumption.getId(), null);

        try {
            electricityConsumption.setGhgFootprint(calculateGHG(electricityConsumption));
            electricityConsumptionRepository.modifyUserInfoById(
                    electricityConsumption.getApplianceType(),
                    electricityConsumption.getDurationMinutes(),
                    electricityConsumption.getContributors(),
                    electricityConsumption.getGhgFootprint(),
                    electricityConsumption.getId()
            );

            return new Response(OK, "Modified .", "Electricity Consumption Modified", electricityConsumption);
        } catch (Exception e) {
            return new Response(ERROR, e.getMessage(), Arrays.toString(e.getStackTrace()), electricityConsumption);
        }
    }

    @CrossOrigin
    @PostMapping("/delete")
    public Response deleteActivity(@RequestBody ElectricityConsumption electricityConsumption) {
        System.out.println(electricityConsumption);

        Optional<ElectricityConsumption> u = electricityConsumptionRepository.findById(electricityConsumption.getId());

        if (!u.isPresent())
            return new Response(WARNING, "Not Exists", "Row Not Exists, Id:" + electricityConsumption.getId(), null);

        try {
            electricityConsumptionRepository.deleteById(electricityConsumption.getId());
            return new Response(OK, "Deleted", "Electricity Consumption Deleted", electricityConsumption);
        } catch (Exception e) {
            return new Response(ERROR, e.getMessage(), Arrays.toString(e.getStackTrace()), electricityConsumption);
        }
    }

    @CrossOrigin
    @GetMapping(value = "/sixMonthAvgScore/{userEmail}")
    public Response getSixMonthAvgScore( @PathVariable String userEmail) {

        try {
            List<ElectricityConsumption> electricityConsumptions = electricityConsumptionRepository.findAllByUserEmail(userEmail);
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

            for(ElectricityConsumption electricityConsumption :electricityConsumptions)
            {
                if(electricityConsumption.getTimestamp()!=null)
                {
                    //listMonthMap.put(String.valueOf(travelActivity.getTimestamp().getMonth()),String.valueOf(1));
                    if(electricityConsumption.getTimestamp().getMonth() > (new Timestamp(System.currentTimeMillis()).getMonth() - 6))
                    {
                        if(dataMap.containsKey(String.valueOf(electricityConsumption.getTimestamp().getMonth())))
                            dataMap.put(monthMap.get(String.valueOf(electricityConsumption.getTimestamp().getMonth())),
                                    String.valueOf((Float.parseFloat(dataMap.get(String.valueOf(electricityConsumption.getTimestamp().getMonth())))
                                            +(electricityConsumption.getGhgFootprint()!=null?Float.parseFloat(electricityConsumption.getGhgFootprint()):0.0f))));
                        else
                            dataMap.put(monthMap.get(String.valueOf(electricityConsumption.getTimestamp().getMonth())),String.valueOf(electricityConsumption.getGhgFootprint()));
                    }
                }
            }

            return new Response(OK, "Calculated .", "Travel Activity Calculated", dataMap);
        }
        catch (Exception e) {
            return new Response(ERROR, e.getMessage(), Arrays.toString(e.getStackTrace()), null);
        }
    }

}
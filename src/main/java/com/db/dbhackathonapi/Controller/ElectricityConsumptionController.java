package com.db.dbhackathonapi.Controller;


import com.db.dbhackathonapi.Constants;
import com.db.dbhackathonapi.Repository.ElectricityConsumptionRepository;
import com.db.dbhackathonapi.Repository.TravelActivityRepository;
import com.db.dbhackathonapi.Tables.ElectricityConsumption;
import com.db.dbhackathonapi.Tables.TravelActivity;
import com.db.dbhackathonapi.Utils;
import com.db.dbhackathonapi.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.db.dbhackathonapi.StatusCodeEnum.*;


/*
either you can use
 */

@RestController    // This means that this class is a Controller
@RequestMapping(path = "/activities/electricity-consumption")
// This means URL's start with /demo (after Application path)
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

            return new Response(OK, "Electricity Consumption Types", "Types of Electricity Consumption could be configured here, total " + activities.size() + " rows", activities);
        } catch (Exception e) {
            return new Response(ERROR, e.getMessage(), Arrays.toString(e.getStackTrace()), null);
        }
    }

    @CrossOrigin
    @PostMapping("/add")
    public Response addActivity(@RequestBody ElectricityConsumption electricityConsumption) {


        try {
            electricityConsumption.setGhgFootprint(calculateGHG(electricityConsumption));
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
}
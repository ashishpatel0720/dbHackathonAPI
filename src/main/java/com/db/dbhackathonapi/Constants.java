package com.db.dbhackathonapi;


import com.db.dbhackathonapi.interfaces.Activity;

import java.util.*;

import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;

public  class Constants {
    public static final List<String> greenActivityList = Arrays.asList("Planted a Tree", "Used Public Transport", "Used Paper Bags", "Shared a Cab");
    public static final List<String> travelActivityList = Arrays.asList("Bicycle", "Motor Bike", "Car", "Flight", "Cab", "Bus", "Other");
    public static final List<String> electricApplianceList = Arrays.asList("Fridge", "Ac", "Washing Machine", "Geyser", "Other");

    HashMap<String, String> metaData = new HashMap<String, String>();


    //TODO: add comparator to all activity
    public static final Comparator<Activity> ACTIVITY_COMPARATOR = comparing(Activity::getTimestamp);
}



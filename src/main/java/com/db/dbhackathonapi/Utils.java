package com.db.dbhackathonapi;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class Utils {

    private static HashMap<String, String> metaData = new HashMap<String, String>();
    private static HashMap<String, String> applianceFactors = new HashMap<String, String>();
    private static HashMap<String, String> travelMedium = new HashMap<String, String>();

    public static Map getMapFactors(String factorName) {
        if (factorName.equals("metaData")) {
            metaData.put("ELECTRICITY", "0.5452");
            metaData.put("NATURAL GAS", "0.1852");
            metaData.put("WATER CONSUMPTION", "0.3");
            metaData.put("PETROL", "2.322");
            metaData.put("DIESEL", "2.672");
            metaData.put("KEROSENE", "2.5421");
            metaData.put("LPG", "1.492");
            metaData.put("OTHER", "0.5452");
            return metaData;
        } else if (factorName.equalsIgnoreCase("applianceFactors")) {
            applianceFactors.put("FRIDGE", "1");
            applianceFactors.put("AC", "1");
            applianceFactors.put("WASHING MACHINE", "1");
            applianceFactors.put("GEYSER", "1");
            applianceFactors.put("OTHER", "1");
            return applianceFactors;
        }
        else if (factorName.equalsIgnoreCase("travelMedium")) {

            travelMedium.put("BICYCLE", "1");
            travelMedium.put("BIKE", "1");
            travelMedium.put("CAR", "1");
            travelMedium.put("FLIGHT", "1");
            travelMedium.put("CAB", "1");
            travelMedium.put("BUS", "1");
            travelMedium.put("OTHER", "1");
            return travelMedium;
        }
        return null;
    }
}


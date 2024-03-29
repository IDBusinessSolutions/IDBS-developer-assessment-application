package com.idbs.simpleassessment.poc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.idbs.simpleassessment.poc.livefeed.LiveFeedMicroService_POC;

/**
 * Data from a feed always provides the following info BUT the EXACT format will varies between locations:
 * 
 * ALL DATA STREAMS start with OfficeId and then a series of data for equipment in that office as follows
 * 
 * DateTime - the date and time of the measurement
 * 
 * EquipmentId - String (a general unique ID, e.g. number or GUID)
 * 
 * EngeryConsumption - float (decimal in kilo-watts per hour)
 * 
 * TimeOn - long (numeric integer, seconds that the equipment has been switched on for)
 * 
 * @author RNaylor
 *
 */

public class EnergyDataManager
{
    public static String LONDON_WATERLOO_FEED_ID = "LND-WTL";

    public static final String WOKING_FEED_ID = "WOK-UK";

    private List<String> loadDataFromFeed(String dataFeed) throws IOException
    {
        /*
         * The data feed will provide a String that starts with the location identifier - this in turn defines how the
         * data is formatted. Different locations have different formats of the data.
         */

        List<String> data = new ArrayList<>();

        if (dataFeed.startsWith(WOKING_FEED_ID))
        {
            // load raw data from locatin's feed. NOTE: format will be specific to location BUT contains same data
            LiveFeedMicroService_POC liveFeed = LiveFeedMicroService_POC.getFeedFor(WOKING_FEED_ID);
            String rawData = liveFeed.readData();

            String[] rawDataArray = rawData.split(":::");

            for (String s : rawDataArray)
            {
                Date date = new Date(s.substring(0, 25).trim());

                float energyConsumption = Float.parseFloat(s.substring(28, 42).trim());
                int timeSwitchedOn = Integer.parseInt(s.substring(50, 61).trim());
                String equipmentId = s.substring(63).trim();

                String dataString = equipmentId + "," + energyConsumption + "," + timeSwitchedOn + "," + date;
                data.add(dataString);
            }

            return data;

        }

        if (dataFeed.startsWith(LONDON_WATERLOO_FEED_ID))
        {
            // load raw data from locatin's feed. NOTE: format will be specific to location BUT contains same data
            LiveFeedMicroService_POC liveFeed = LiveFeedMicroService_POC.getFeedFor(LONDON_WATERLOO_FEED_ID);
            String rawData = liveFeed.readData();

            String[] rawDataArray = rawData.split("DATA_END");

            for (String wd : rawDataArray)
            {
                String[] wdArray = wd.split(",");
                String dateAndTime = wdArray[0].replace(" ", "");
                Date date = new Date(Long.parseLong(dateAndTime));

                String equipmentId = wdArray[1].trim();
                float energyConsumption = Float.parseFloat(wdArray[2].trim());
                int timeSwitchedOn = Integer.parseInt(wdArray[3].trim());

                String dataString = equipmentId + "," + energyConsumption + "," + timeSwitchedOn + "," + date;
                data.add(dataString);
            }

            return data;
        }

        throw new RuntimeException("unknown location - no datasource defined - cannot proceed");
    }

    public double calculateLocationEnergyConsumption(String locationId) throws IOException
    {
        List<String> dataStringList = new ArrayList<String>();

        dataStringList = loadDataFromFeed(locationId);

        // energy usage
        double energyUsed = 0.0d;

        for (String equipmentData : dataStringList)
        {
            String[] dataItems = equipmentData.split(",");
            double energyConsumed = Float.valueOf(dataItems[1]) * (Integer.valueOf(dataItems[2]) / 3600d);
            energyUsed += energyConsumed;
        }

        return energyUsed;
    }

}

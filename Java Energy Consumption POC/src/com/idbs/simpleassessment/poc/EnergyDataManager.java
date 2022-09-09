package com.idbs.simpleassessment.poc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

    public static final String PORTLAND_MAINE_FEED_ID = "PRT-MN";

    public static final String ALAMEDA_CALIFORNIA_FEED_ID = "ALM-CA";


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

        if (dataFeed.startsWith(PORTLAND_MAINE_FEED_ID))
        {
            // load raw data from locatin's feed. NOTE: format will be specific to location BUT contains same data
            LiveFeedMicroService_POC liveFeed = LiveFeedMicroService_POC.getFeedFor(PORTLAND_MAINE_FEED_ID);
            String rawData = liveFeed.readData();

            String[] rawDataArray = rawData.split("<>");

            for (String wd : rawDataArray)
            {
                String equipmentId = wd.substring(wd.indexOf("equipmentId:") + "equipmentId:".length(), wd.indexOf("readingDate")).trim();

                String d1 = wd.substring(wd.indexOf("readingDate:") + "readingDate:".length(), wd.indexOf("readingTime"));
                String d2 = wd.substring(wd.indexOf("readingTime:") + "readingTime:".length(), wd.indexOf("energyConsumption"));
                Date date = new Date(d1.trim() + " " + d2.trim());

                String energyConsumptionStr = wd.substring(wd.indexOf("energyConsumption:") + "energyConsumption:".length(), wd.indexOf("timeOn"));
                float energyConsumption = Float.parseFloat(energyConsumptionStr.trim());

                String timeSwitchedOnStr = wd.substring(wd.indexOf("timeOn:") + "timeOn:".length());
                int timeSwitchedOn = Integer.parseInt(timeSwitchedOnStr.trim());

                String dataString = equipmentId + "," + energyConsumption + "," + timeSwitchedOn + "," + date;
                data.add(dataString);
            }

            return data;
        }

        if (dataFeed.startsWith(ALAMEDA_CALIFORNIA_FEED_ID))
        {
            // load raw data from locatin's feed. NOTE: format will be specific to location BUT contains same data
            LiveFeedMicroService_POC liveFeed = LiveFeedMicroService_POC.getFeedFor(ALAMEDA_CALIFORNIA_FEED_ID);
            String rawData = liveFeed.readData();

            rawData = rawData.replaceAll("\t", "");
            rawData = rawData.substring(rawData.indexOf("[") + 1, rawData.indexOf("]"));

            for (int i = 0; i < 3; i++)
            {
                String[] alamedaData = readAlmedaJsonFormat(rawData);
                rawData = rawData.substring(100);
                Date date = new Date(alamedaData[0].trim());
                String equipmentId = alamedaData[1].trim();
                float energyConsumption = Float.parseFloat(alamedaData[2].trim());
                int timeSwitchedOn = Integer.parseInt(alamedaData[3].trim());

                String dataString = equipmentId + "," + energyConsumption + "," + timeSwitchedOn + "," + date;
                data.add(dataString);
            }

            return data;
        }

        throw new RuntimeException("unknown location - no datasource defined - cannot proceed");
    }

    private String[] readAlmedaJsonFormat(String rawData)
    {
        String[] retVal =
        { "", "", "", "" };
        int curPos = 0;

        curPos = rawData.indexOf("dateTime", curPos);
        curPos = rawData.indexOf(":", curPos);
        curPos = rawData.indexOf("\"", curPos);
        String str = rawData.substring(rawData.indexOf("\"", curPos++) + 1, rawData.indexOf("\"", curPos++)).trim();
        retVal[0] = str;

        curPos = rawData.indexOf("equipmentId", curPos);
        curPos = rawData.indexOf(":", curPos);
        curPos = rawData.indexOf("\"", curPos);
        str = rawData.substring(rawData.indexOf("\"", curPos++) + 1, rawData.indexOf("\"", curPos++)).trim();
        retVal[1] = str;

        curPos = rawData.indexOf("energyConsumption", curPos);
        curPos = rawData.indexOf(":", curPos);
        curPos = rawData.indexOf("\"", curPos);
        str = rawData.substring(rawData.indexOf("\"", curPos++) + 1, rawData.indexOf("\"", curPos++)).trim();
        retVal[2] = str;

        curPos = rawData.indexOf("timeOn", curPos);
        curPos = rawData.indexOf(":", curPos);
        curPos = rawData.indexOf("\"", curPos);
        str = rawData.substring(rawData.indexOf("\"", curPos++) + 1, rawData.indexOf("\"", curPos++)).trim();
        retVal[3] = str;

        return retVal;
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

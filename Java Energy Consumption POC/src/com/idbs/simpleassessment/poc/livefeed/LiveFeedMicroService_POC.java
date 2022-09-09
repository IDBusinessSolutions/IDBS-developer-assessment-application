package com.idbs.simpleassessment.poc.livefeed;

import static com.idbs.simpleassessment.poc.EnergyDataManager.ALAMEDA_CALIFORNIA_FEED_ID;
import static com.idbs.simpleassessment.poc.EnergyDataManager.WOKING_FEED_ID;
import static com.idbs.simpleassessment.poc.EnergyDataManager.LONDON_WATERLOO_FEED_ID;
import static com.idbs.simpleassessment.poc.EnergyDataManager.PORTLAND_MAINE_FEED_ID;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * DO NOT REFACTOR THIS CLASS
 * 
 * simple impl for the POC only - will be replace with reading data from a live feed in production, probably a
 * micro-service.
 * 
 * DO NOT REFACTOR THIS CLASS
 * 
 * IT IS HERE TO MOCK A LIVE FEED OF DATA
 *
 **/
public class LiveFeedMicroService_POC
{
    private final String feedId;

    public LiveFeedMicroService_POC(String feedId)
    {
        this.feedId = feedId;
    }

    //
    // DO NOT REFACTOR THIS METHOD
    // IT IS HERE TO MOCK A LIVE FEED OF DATA
    //
    public String readData() throws IOException
    {
        String retVal = null;
        File file = null;

        file = determineSourceDataFile();

        retVal = readFeedData(file);

        return retVal;
    }

    private String readFeedData(File file) throws IOException, FileNotFoundException
    {
        String retVal;
        // now read the data from file...
        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String line = null;
            StringBuilder stringBuilder = new StringBuilder();

            while ((line = reader.readLine()) != null)
            {
                if (!line.startsWith("#"))
                {
                    stringBuilder.append(line);
                }
            }

            retVal = stringBuilder.toString();
        }
        
        return retVal;
    }

    private File determineSourceDataFile() throws IOException
    {
        File retVal = null;

        if (feedId.equals(LONDON_WATERLOO_FEED_ID))
        {
            retVal = new File(LONDON_WATERLOO_FEED_ID + ".dat");
        }
        else if (feedId.equals(WOKING_FEED_ID))
        {
            retVal = new File(WOKING_FEED_ID + ".dat");
        }
        else if (feedId.equals(PORTLAND_MAINE_FEED_ID))
        {
            retVal = new File(PORTLAND_MAINE_FEED_ID + ".dat");
        }
        else if (feedId.equals(ALAMEDA_CALIFORNIA_FEED_ID))
        {
            retVal = new File(ALAMEDA_CALIFORNIA_FEED_ID + ".dat");
        }
        else
        {
            throw new IOException("unknown data feed identifier - canot continue: " + feedId);
        }

        return retVal;
    }

    public static LiveFeedMicroService_POC getFeedFor(String feedId)
    {
        LiveFeedMicroService_POC retVal = new LiveFeedMicroService_POC(feedId);

        return retVal;
    }
}

package com.idbs.simpleassessment.poc.livefeed;

import static com.idbs.simpleassessment.poc.TransformEnergyDataToStandardDataStore_POC.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DO NOT REFACTOR THIS CLASS
 * 
 * simple impl for the POC only - will be replace with reading data from a live feed in production, probably a
 * micro-service.
 * 
 * DO NOT REFACTOR
 *
 */
public class LiveFeedMicroService_POC
{
    //
    // DO NOT REFACTOR THIS METHOD
    // IT IS HERE TO MOCK A LIVE FEED OF DATA
    //
    static public String readDataFromLiveFeed(String feedId) throws IOException
    {
        String retVal = null;
        File file = null;

        if (feedId.equals(LONDON_WATERLOO_FEED_ID))
        {
            file = new File(LONDON_WATERLOO_FEED_ID + ".dat");
        }
        else if (feedId.equals(GUILDFORD_FEED_ID))
        {
            file = new File(GUILDFORD_FEED_ID + ".dat");
        }
        else if (feedId.equals(PORTLAND_MAINE_FEED_ID))
        {
            file = new File(PORTLAND_MAINE_FEED_ID + ".dat");
        }
        else if (feedId.equals(ALAMEDA_CALIFORNIA_FEED_ID))
        {
            file = new File(ALAMEDA_CALIFORNIA_FEED_ID + ".dat");
        }
        else
        {
            throw new IOException("unknown data feed identifier - canot continue: " + feedId);
        }

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
                    if (!feedId.equals(ALAMEDA_CALIFORNIA_FEED_ID))
                    {
                        stringBuilder.append(" // ");
                    }
                }
            }

            stringBuilder.setLength(stringBuilder.length() - 4);

            retVal = stringBuilder.toString();
        }

        return retVal;
    }
}

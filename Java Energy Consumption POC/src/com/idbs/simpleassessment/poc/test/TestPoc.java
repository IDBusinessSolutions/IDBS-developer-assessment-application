/*
 * Copyright (C) 1993-2020 ID Business Solutions Limited
 * All rights reserved
 */
package com.idbs.simpleassessment.poc.test;

import java.text.DecimalFormat;

import com.idbs.simpleassessment.poc.TransformEnergyDataToStandardDataStore_POC;

import static com.idbs.simpleassessment.poc.TransformEnergyDataToStandardDataStore_POC.*;

/**
 * 
 */

public class TestPoc
{
    public boolean testThatCalculatedValuesForEachOfficeAreCorrect()
    {
        TransformEnergyDataToStandardDataStore_POC poc = new TransformEnergyDataToStandardDataStore_POC();

        try
        {
            poc.loadEngeryConsumptionData();

            double test01EnergyUsed = poc.calculateLocationEnergyConsumption(LONDON_WATERLOO_FEED_ID);
            String test01Str = new DecimalFormat("#0.00").format(test01EnergyUsed / 1000);
            System.out.println("Energy Used at Waterloo Office is: " + test01Str + " Kw     (expected: 9.57 Kw)");

            double test02EnergyUsed = poc.calculateLocationEnergyConsumption(GUILDFORD_FEED_ID);
            String test02Str = new DecimalFormat("#0.00").format(test02EnergyUsed / 1000);
            System.out.println("Energy Used at Guildford Office is: " + test02Str + " Kw     (expected: 209.31 Kw)");

            double test03EnergyUsed = poc.calculateLocationEnergyConsumption(PORTLAND_MAINE_FEED_ID);
            String test03Str = new DecimalFormat("#0.00").format(test03EnergyUsed / 1000);
            System.out.println(
                "Energy Used at Portland Maine Office is: " + test03Str + " Kw     (expected: 387.13 Kw)");

            double test04EnergyUsed = poc.calculateLocationEnergyConsumption(ALAMEDA_CALIFORNIA_FEED_ID);
            String test04Str = new DecimalFormat("#0.00").format(test04EnergyUsed / 1000);
            System.out.println(
                "Energy Used at Alemeda California Office is: " + test04Str + " Kw     (expected: 4651.63 Kw)");

            return (test01Str.equals("9.57") && test02Str.equals("209.31") && test03Str.equals("387.13")
                && test04Str.equals("4651.63"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

}

/*
 * Copyright (C) 1993-2020 ID Business Solutions Limited
 * All rights reserved
 */
package com.idbs.simpleassessment.poc.test;

import java.text.DecimalFormat;

import com.idbs.simpleassessment.poc.EnergyDataManager;

import static com.idbs.simpleassessment.poc.EnergyDataManager.*;

/**
 * 
 */

public class TestPoc
{
    public boolean testThatCalculatedValuesForEachOfficeAreCorrect()
    {
        EnergyDataManager poc = new EnergyDataManager();

        try
        {
            double test01EnergyUsed = poc.calculateLocationEnergyConsumption(LONDON_WATERLOO_FEED_ID);
            String test01Str = new DecimalFormat("#0.00").format(test01EnergyUsed / 1000);
            System.out.println("Calculated energy used at Waterloo Office is: " + test01Str + " Kw     (expected: 9.57 Kw)");

            double test02EnergyUsed = poc.calculateLocationEnergyConsumption(WOKING_FEED_ID);
            String test02Str = new DecimalFormat("#0.00").format(test02EnergyUsed / 1000);
            System.out.println("Calculated energy used at Woking Office is: " + test02Str + " Kw     (expected: 209.31 Kw)");

            return (test01Str.equals("9.57") && test02Str.equals("209.31"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

}

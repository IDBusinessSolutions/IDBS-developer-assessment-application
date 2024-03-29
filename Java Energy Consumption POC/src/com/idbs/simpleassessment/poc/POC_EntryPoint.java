/*
 * Copyright (C) 1993-2020 ID Business Solutions Limited
 * All rights reserved
 */
package com.idbs.simpleassessment.poc;

import com.idbs.simpleassessment.poc.test.TestPoc;

/**
 * 
 */

public class POC_EntryPoint
{
    public static void main(String[] args)
    {
        try
        {
            TestPoc test = new TestPoc();
            boolean passTest = test.testThatCalculatedValuesForEachOfficeAreCorrect();

            if (passTest)
            {
                System.out.println("\n\nTEST PASS: all values as expected");
            }
            else
            {
                System.out.println("\n");
                System.out.println("FAILED TEST  ====== FAILED TEST  ====== FAILED TEST  ====== FAILED TEST  ====== FAILED TEST  ====== ");
                System.out.println("FAILED TEST  ======                                                             FAILED TEST  ====== ");
                System.out.println("FAILED TEST  ======              some calculated values were not correct        FAILED TEST  ====== ");
                System.out.println("FAILED TEST  ======                                                             FAILED TEST  ====== ");
                System.out.println("FAILED TEST  ====== FAILED TEST  ====== FAILED TEST  ====== FAILED TEST  ====== FAILED TEST  ====== ");            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}

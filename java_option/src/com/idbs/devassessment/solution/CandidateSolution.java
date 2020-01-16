/*
 * Copyright (C) 1993-2020 ID Business Solutions Limited
 * All rights reserved
 */
package com.idbs.devassessment.solution;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import com.idbs.devassessment.core.QuestionType;

/**
 * Example solution for the example question - finding the average of an array of integers
 */

public class CandidateSolution extends CandidateSolutionBase
{
    @Override
    public QuestionType getQuestionType()
    {
        /**
         * 
         * CHANGE this return type to YOUR selected choice of question that you will code an answer for.
         * 
         */
        
        return QuestionType.IDBS_EXAMPLE;
    }

    @Override
    public String getAnswer()
    {
        /*
         * This is the default solution and provides some example code on how to extract data from Json in java.
         * 
         *  As an initial start we suggest you comment ALL the code below and return a null value from the method.
         *  Run this in the assess application and you see many examples of the Json that question produces.
         *   
         */
        
        // first get Json as a String for the question using the inherited method...
        String json = getJsonForQuestion();

        // now use the json api to read the json to give a JsonObject representing the Json...
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject jsonObject = reader.readObject();
        reader.close();

        // now start extracting the data you need from the json....
        
        // get the start value from the Json
        int startValue = jsonObject.getInt("startValue");

        // read the values array from the json
        JsonArray jsonArray = jsonObject.getJsonArray("values");

        // now sum the array
        int arraySum = 0;

        for (int i = 0; i < jsonArray.size(); i++)
        {
            arraySum += jsonArray.getInt(i);
        }

        // calculate the answer..
        int answer = startValue - arraySum;

        return Integer.toString(answer);
    }

}

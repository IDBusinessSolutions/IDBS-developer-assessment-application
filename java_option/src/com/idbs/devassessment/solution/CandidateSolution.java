/*
 * Copyright (C) 1993-2019 ID Business Solutions Limited
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
        return QuestionType.Example;
    }

    @Override
    public String getAnswer()
    {
        // first get Json as a String for the question using the inherited method...
        String json = getJsonForQuestion();

        // now use the json api to read the json to give a JsonObject representing the Json...
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject jsonObject = reader.readObject();
        reader.close();

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
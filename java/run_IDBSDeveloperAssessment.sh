#!/bin/bash

# This is the execution command line to use to run the IDBS Developer Assessment application using the jar files supplied from github.
# It uses the default IDBS Example solution as the basis of the question and solution for the application
# It assumes that the sh file is located in the same directory/folder as the IDBSDeveloperAssessment.jar

java -cp "./referenceLibs/IDBSDeveloperAssessment.jar;./referenceLibs/javax.json-1.0.4.jar;./referenceLibs/javax.json-api-1.1.jar" com.idbs.devassessment.core.IDBSDeveloperAssessmentMain

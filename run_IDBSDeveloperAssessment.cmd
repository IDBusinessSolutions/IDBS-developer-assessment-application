REM This is the execution command line to use to run the IDBS Developer Assessment application using the jar files supplied from github.
REM It uses the default IDBS Example solution as the basis of the question and solution for the application
REM It assumes that the cmd file is located in the same directory/folder as the IDBSDeveloperAssessment.jar

java -cp ".\ReferenceLibs\IDBSDeveloperAssessment.jar;.\ReferenceLibs\javax.json-1.0.4.jar;.\ReferenceLibs\javax.json-api-1.1.jar" com.idbs.devassessment.core.IDBSDeveloperAssessmentMain

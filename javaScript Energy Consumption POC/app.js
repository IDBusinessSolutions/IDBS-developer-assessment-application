const fs = require('fs');
const util = require('util');
const julian = require('julian');

const GUILDFORD_FEED_ID = 'GFD-UK';
const LONDON_WATERLOO_FEED_ID = 'LND-WTL';
const PORTLAND_MAINE_FEED_ID = "PRT-MN";
const ALAMEDA_CALIFORNIA_FEED_ID = "ALM-CA";

var energyData = new Map();

function main(){
	loadAllEnergyData();
	
	setTimeout(reportEnergyConsumptionData, 100);

}


function loadAllEnergyData(){
	loadDataFromFeed(GUILDFORD_FEED_ID);
	loadDataFromFeed(LONDON_WATERLOO_FEED_ID);
	loadDataFromFeed(PORTLAND_MAINE_FEED_ID);
	loadDataFromFeed(ALAMEDA_CALIFORNIA_FEED_ID);
}

function loadDataFromFeed(feedId){
	
	
	fs.readFile('.\\' + feedId + '.dat', (err, data) => {
	
		if(feedId === GUILDFORD_FEED_ID){
			var rawDataArray = data.toString().split('\r\n');
				
            for (var i = 0; i < rawDataArray.length; i++)
            {
				var rawData = rawDataArray[i];
				if(!rawData.startsWith('#')){
					var date = new Date(rawData.substring(0, 25).trim());

					var energyConsumption = rawData.substring(28, 42).trim();
					var timeSwitchedOn = rawData.substring(50, 61).trim();
					var equipmentId = rawData.substring(63).trim();

					saveToDataStore(GUILDFORD_FEED_ID, date, equipmentId, energyConsumption, timeSwitchedOn);
				}
            }
		}
		
		if(feedId === LONDON_WATERLOO_FEED_ID){
			var rawDataArray = data.toString().split('\r\n');
				
            for (var i = 0; i < rawDataArray.length; i++)
            {
				var rawData = rawDataArray[i];
				if(!rawData.startsWith('#')){
					var strArr = rawData.split(',');
					var date = new julian.toDate(strArr[0].trim());
					var equipmentId = strArr[1].trim();
					var energyConsumption = strArr[2].trim();
					var timeSwitchedOn = strArr[3].trim();
					
                	saveToDataStore(LONDON_WATERLOO_FEED_ID, date, equipmentId, energyConsumption, timeSwitchedOn);
				}
            }
		}

		if(feedId === PORTLAND_MAINE_FEED_ID){
			var rawDataArray = data.toString().split('\r\n');
				
            for (var i = 0; i < rawDataArray.length; i++)
            {
				var rawData = rawDataArray[i];
				if(!rawData.startsWith('#')){
					var strArr = rawData.split('+');
					var date = new Date(strArr[2] + " " + strArr[3]);
					var equipmentId = strArr[1].trim();
					var energyConsumption = strArr[4].trim();
					var timeSwitchedOn = strArr[5].trim();
					
                	saveToDataStore(PORTLAND_MAINE_FEED_ID, date, equipmentId, energyConsumption, timeSwitchedOn);
				}
            }
		}


		if(feedId === ALAMEDA_CALIFORNIA_FEED_ID){
			var json = JSON.parse(data);
				
            for (var i = 0; i < json.data.record.length; i++)
            {
				var date = new Date(json.data.record[i].dateTime);
				var equipmentId = json.data.record[i].equipmentId;
				var energyConsumption = json.data.record[i].energyConsumption;
				var timeSwitchedOn = json.data.record[i].timeOn;

				saveToDataStore(ALAMEDA_CALIFORNIA_FEED_ID, date, equipmentId, energyConsumption, timeSwitchedOn);
            }

		}

	});

}

	
function saveToDataStore(feedId, date, equipmentId, energyConsumption, timeSwitchedOn){
	var dataIn = {date, equipmentId, energyConsumption, timeSwitchedOn};
	dataIn = '{' + date + ', ' +  equipmentId + ', ' + energyConsumption + ', ' +  timeSwitchedOn + '}';
	var currentData = energyData.get(feedId);

	if(typeof currentData === 'undefined'){
		currentData = dataIn;
	}
	else{
		currentData = currentData + ',' + dataIn;
	}
	
	energyData.set(feedId, currentData);
	
}

function reportEnergyConsumptionData(){
	console.log('\n');
	var testResult = true;
	
	var data = energyData.get(GUILDFORD_FEED_ID);
	var energyConsumption = calculateEngergyConsumption(data);
	testResult = testResult && (energyConsumption == 209.31);
	console.log('Energy Used at ' + GUILDFORD_FEED_ID + ' Office is: ' + energyConsumption );

	data = energyData.get(LONDON_WATERLOO_FEED_ID);
	energyConsumption = calculateEngergyConsumption(data);
	testResult = testResult && (energyConsumption == 9.57);
	console.log('Energy Used at ' + LONDON_WATERLOO_FEED_ID + ' Office is: ' + energyConsumption );


	data = energyData.get(PORTLAND_MAINE_FEED_ID);
	energyConsumption = calculateEngergyConsumption(data);
	testResult = testResult && (energyConsumption == 371.54);
	console.log('Energy Used at ' + PORTLAND_MAINE_FEED_ID + ' Office is: ' + energyConsumption );


	data = energyData.get(ALAMEDA_CALIFORNIA_FEED_ID );
	energyConsumption = calculateEngergyConsumption(data);
	testResult = testResult && (energyConsumption == 4651.63);
	console.log('Energy Used at ' + ALAMEDA_CALIFORNIA_FEED_ID + ' Office is: ' + energyConsumption );


	console.log('\n >>>>>>>> PASS: ' + testResult + '  <<<<<<<<<<<\n\n');

}

function calculateEngergyConsumption(officeData){
	var officeDataArr = officeData.split('},');
	var energyConsumption = 0.0;
	
	for(var i = 0; i < officeDataArr.length; i++){
		officeDataArr[i] = officeDataArr[i].replace("{", "").replace("}", "").trim();
		readingDataArr = officeDataArr[i].split(',');
		energyConsumption += readingDataArr[2] * (readingDataArr[3] / 3600);
	}

	energyConsumption = Math.round(energyConsumption/10) / 100;
	return energyConsumption;

}

main();

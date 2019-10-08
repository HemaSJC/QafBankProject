package com.rtp.listener;

import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Iterator;

import org.json.simple.parser.JSONParser;

import com.qmetry.qaf.automation.core.ConfigurationManager;


public class RTPListener implements ITestListener, ISuiteListener {
	
	public static String runID;
	public static String resPath;
	public static String drivername;
	
	public static ThreadLocal<String> testPath = new ThreadLocal<String>();
	public static ThreadLocal<String> testPathnew = new ThreadLocal<String>(); //new code
	public static ThreadLocal<String> testPathjson = new ThreadLocal<String>(); 
	public static ThreadLocal<String> overviewpath = new ThreadLocal<String>(); 
	public static ThreadLocal<String> resultspath = new ThreadLocal<String>(); 
	public static ThreadLocal<String> resultspathforcheckpoints = new ThreadLocal<String>(); 
	public static ThreadLocal<String> testName = new ThreadLocal<String>();
	
	@Override
	public void onStart(ISuite suite) 
	{
		JSONObject obj =null;
		BufferedWriter writer = null;
		try
		{
			
			drivername=suite.getParameter("driver.name");
			runID = suite.getParameter("RTP_resultId");
			String dir = System.getProperty("user.dir");
	        resPath = (dir + "\\test-results\\" + runID);
	        File fDir = new File(resPath);
	        fDir.mkdir();
	        
	        // Status json file creation
	        
	        testPath.set(dir + "\\test-results\\"+"status.json");
	        File newFile = new File(testPath.get());

	         obj = new JSONObject();
			


	        
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		

		
		obj.put("RTP_Status", "Started");

		JsonParser parser = new JsonParser();

		try 
		{
			//JsonObject array1 = (JsonObject) parser.parse(new FileReader(testPath.get()));
			 writer = new BufferedWriter(new FileWriter(testPath.get()));
			writer.write(obj.toString());
			


			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	@Override
	public void onFinish(ISuite suite) 
	{
		testPath.set(System.getProperty("user.dir") + "\\test-results\\"+"status.json");
        File newFile = new File(testPath.get());
        BufferedWriter writer = null;
        
				
				
		JSONObject jobject = new JSONObject();
		
		jobject.put("RTP_Status", "Completed");

		JsonParser parser = new JsonParser();

		try 
		{
			
			 writer = new BufferedWriter(new FileWriter(testPath.get()));
			writer.write(jobject.toString());
		
				
		}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
		finally
		{
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void onStart(ITestContext context) {
		
		testName.set(context.getName());
		testPath.set(resPath + "//" + context.getName() + ".json");
		testPathnew.set(System.getProperty("user.dir") + "\\test-results\\"+"meta-info.json"); //newcode
		
		File newFile = new File(testPath.get());

		JSONArray jsonArray = new JSONArray();
		
		try 
		{
			newFile.createNewFile();
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(testPath.get()));
			writer.write(jsonArray.toString());
			writer.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void onFinish(ITestContext context) {
	
			
	}
	
	@Override
	public void onTestStart(ITestResult result) {

	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
				
		JsonObject jsonChild = new JsonObject();
		JsonObject jsonParent = new JsonObject();
		
		String tName = result.getTestName();
		String result_array_name =result.getMethod().getGroups()[0];
		String dCode = testName.get();
	
		long startTime = result.getStartMillis();
		long endTime = result.getEndMillis();
//		ReportiumClient client = (ReportiumClient) ConfigurationManager.getBundle().getObject("perfecto.report.client");
//		String reportURL = client.getReportUrl();
				
		String s[]=tName.split("]_");
		
		String s0 = s[0];
		String RTP_tcID =s0.substring(5);
		
		String s1 = s[1];
		String Automation_tcID = s1.substring(1);		
			 
		jsonChild.addProperty("Status", "Passed");
		jsonChild.addProperty("StartTime", startTime);
		jsonChild.addProperty("EndTime", endTime);
		
		jsonParent.addProperty("TestID", result_array_name);
		jsonParent.addProperty("DeviceCode", dCode);
		jsonParent.addProperty("Platform", dCode);
		jsonParent.addProperty("RTP_tcID", RTP_tcID);
		jsonParent.addProperty("Automation_tcID", Automation_tcID);
		jsonParent.add("Details", jsonChild);
						
		JsonParser parser = new JsonParser();
		
		try 
		{
			JsonArray array = (JsonArray) parser.parse(new FileReader(testPath.get()));
			array.add(jsonParent);
			
			testPathnew.set(System.getProperty("user.dir") + "/test-results/" +"meta-info.json"); //newcode

			Object object = parser
                    .parse(new FileReader(testPathnew.get()));
            
            //convert Object to JSONObject
			JsonObject jsonObject = (JsonObject)object;
                        
            //Reading the array
			JsonArray reports = (JsonArray)jsonObject.get("reports");
			
			//fetching the json foldername
            for (int i = 0; i < reports.size();) {
            	JsonObject item = (JsonObject)reports.get(0);
            	String name = String.valueOf(item.get("dir"));
            	
            	testPathjson.set(System.getProperty("user.dir") + "\\" + name.replaceAll("\"", "") +"\\meta-info.json");
            	
            	Object object1 = parser
                        .parse(new FileReader(testPathjson.get()));
            	
            	//convert Object to JSONObject
    			JsonObject jsonObject1 = (JsonObject)object1;
    			
    			//Reading the array
    			JsonArray tests = (JsonArray)jsonObject1.get("tests");
    			
    			
    			
    			//Loop to fetch the browser folder name
    			Iterator<JsonElement> iterator = tests.iterator();
	            while (iterator.hasNext()) {
	                //System.out.println(iterator.next());
	            	JsonElement json1 = iterator.next();
	            	
	            	overviewpath.set(System.getProperty("user.dir") + "\\" + name.replaceAll("\"", "") + "\\" + json1.toString().replaceAll("\"", "") +"\\overview.json");
	            	
	            	Object object2 = parser
	                        .parse(new FileReader(overviewpath.get()));
	            	
	            	//convert Object to JSONObject
	    			JsonObject jsonObject2 = (JsonObject)object2;
	    			
	    			//Reading the array
	    			JsonArray tests1 = (JsonArray)jsonObject2.get("classes");
	    			
	    			//Loop to fetch the browser folder name
	    			Iterator<JsonElement> iterator1 = tests1.iterator();
	    			
	    			while (iterator1.hasNext()) {
	    				JsonElement json2 = iterator1.next();
		    			resultspath.set(System.getProperty("user.dir") + "\\" + name.replaceAll("\"", "") + "\\" + json1.toString().replaceAll("\"", "") + "\\" + json2.toString().replaceAll("\"", "") +"\\meta-info.json");
		    			
		    			Object object3 = parser
		                        .parse(new FileReader(resultspath.get()));
		            	
		            	//convert Object to JSONObject
		    			JsonObject jsonObject3 = (JsonObject)object3;
		    			
		    			//Reading the array
		    			JsonArray tests2 = (JsonArray)jsonObject3.get("methods");
		    			
		    			for (int j = 0; j < tests2.size();) {
		                	JsonObject item1 = (JsonObject)tests2.get(0);
		                	String name1 = String.valueOf(item1.get("metaData"));
		                	String arr[] = name1.split(",");
		                	String arrname = arr[4].substring(7);
		                	//System.out.println("Name :" + arrname);    
		                	
			    			resultspathforcheckpoints.set(System.getProperty("user.dir") + "\\" + name.replaceAll("\"", "") + "\\" + json1.toString().replaceAll("\"", "") + "\\" + json2.toString().replaceAll("\"", "") +"\\" + arrname.replaceAll("\"", "") + ".json");
			    			
			    			Object object4 = parser
			                        .parse(new FileReader(resultspathforcheckpoints.get()));
			            	
			            	//convert Object to JSONObject
			    			JsonObject jsonObject4 = (JsonObject)object4;
			    			//JsonObject outerObject = JsonObject(jsonObject4.toString());
			    			
			    			//Reading the array
			    			//JsonObject innerObject = (JsonObject) outerObject.getAsJsonObject("seleniumLog");
			    			
			    			JsonArray tests3 = (JsonArray)jsonObject4.get("checkPoints");	
			    			jsonParent.add("Result Details", tests3); //newcode	
			    			break;
		    			}
	    			}
	    			
	            }    			
            	//System.out.println("Branch :" + name);
            	break;
            }
                        
			            
			FileWriter file = null;
			
			try 
			{
				file = new FileWriter(testPath.get());
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String str = gson.toJson(array);
			
			try 
			{
				file.write(str);
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
			try 
			{
				file.flush();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
         
           }

     
        private JsonObject JsonObject(String string) {
		// TODO Auto-generated method stub
		return null;
	}


		//******************************************


	//}

	@Override
	public void onTestFailure(ITestResult result) {
		
		JsonObject jsonChild = new JsonObject();
		JsonObject jsonParent = new JsonObject();
		
		String tName = result.getTestName();
		String result_array_name =result.getMethod().getGroups()[0];
		String dCode = testName.get();
	
		long startTime = result.getStartMillis();
		long endTime = result.getEndMillis();
//		ReportiumClient client = (ReportiumClient) ConfigurationManager.getBundle().getObject("perfecto.report.client");
//		String reportURL = client.getReportUrl();
		
		String s[]=tName.split("]_");
		
		String s0 = s[0];
		String RTP_tcID =s0.substring(5);
		
		String s1 = s[1];
		String Automation_tcID = s1.substring(1);
					
			 
		jsonChild.addProperty("Status", "Failed");
		jsonChild.addProperty("StartTime", startTime);
		jsonChild.addProperty("EndTime", endTime);
		
		jsonParent.addProperty("TestID", result_array_name);
		jsonParent.addProperty("DeviceCode", dCode);
		jsonParent.addProperty("Platform", dCode);
		jsonParent.addProperty("RTP_tcID", RTP_tcID);
		jsonParent.addProperty("Automation_tcID", Automation_tcID);
		jsonParent.add("Details", jsonChild);
		
		JsonParser parser = new JsonParser();

		try 
		{
			JsonArray array = (JsonArray) parser.parse(new FileReader(testPath.get()));
			array.add(jsonParent);

			FileWriter file = null;
			
			try 
			{
				file = new FileWriter(testPath.get());
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String str = gson.toJson(array);
			
			try 
			{
				file.write(str);
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
			try 
			{
				file.flush();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		JsonObject jsonChild = new JsonObject();
		JsonObject jsonParent = new JsonObject();
		
		String tName = result.getTestName();
		String result_array_name =result.getMethod().getGroups()[0];
		String dCode = testName.get();
	
		long startTime = result.getStartMillis();
		long endTime = result.getEndMillis();
//		ReportiumClient client = (ReportiumClient) ConfigurationManager.getBundle().getObject("perfecto.report.client");
//		String reportURL = client.getReportUrl();
		
		String s[]=tName.split("]_");
		
		String s0 = s[0];
		String RTP_tcID =s0.substring(5);
		
		String s1 = s[1];
		String Automation_tcID = s1.substring(1);	
			
			 
		jsonChild.addProperty("Status", "Skipped");
		jsonChild.addProperty("StartTime", startTime);
		jsonChild.addProperty("EndTime", endTime);
		
		jsonParent.addProperty("TestID", result_array_name);
		jsonParent.addProperty("DeviceCode", dCode);
		jsonParent.addProperty("Platform", dCode);
		jsonParent.addProperty("RTP_tcID", RTP_tcID);
		jsonParent.addProperty("Automation_tcID", Automation_tcID);
		jsonParent.add("Details", jsonChild);
		
		JsonParser parser = new JsonParser();

		try 
		{
			JsonArray array = (JsonArray) parser.parse(new FileReader(testPath.get()));
			array.add(jsonParent);

			FileWriter file = null;
			
			try 
			{
				file = new FileWriter(testPath.get());
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String str = gson.toJson(array);
			
			try 
			{
				file.write(str);
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
			try 
			{
				file.flush();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	
}
package com.automation.framework.webService;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.automation.framework.core.DriverScript;
import com.automation.framework.pojs.TestDriver;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Response;

public class RESTKeywords {

	public String getJsonFileToString(String filePath){
		JSONParser jsonParser = new JSONParser();
		Object object= null;
		try{	
			object = jsonParser.parse(new FileReader(filePath));	
			return object.toString();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (ParseException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	public void getResponseForAPostRequest(TestDriver testDriver,Hashtable<String,String> params) throws ParseException{
		DriverScript.logMessage(testDriver,"info", "getResponseForAPostRequest keyword is invoked");
		String Requestdata = (String) params.get("FirstTestServiceRequest");
		String URL = (String) params.get("URI");
		DriverScript.logMessage(testDriver,"info"," URI is "+ URL);
		Response response = null;
		try{
		response =  RestAssured.given().contentType("application/json").body(Requestdata).when().post("http://www.google.com");
		System.out.println(response.asString());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		if(response != null && response.body().toString().length() > 0){
			DriverScript.logMessage(testDriver,"info"," Response from the Webservice"+response.asString());
			DriverScript.logMessage(testDriver,"info"," Response from the Webservice"+response.print());
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject)jsonParser.parse(response.asString());
			JSONObject message = (JSONObject)jsonObject.get("Data");
			String Token = (String) message.get("Token");
		
			System.out.println("Token is" +Token);
			
			DriverScript.logMessage(testDriver,"info"," Token value is "+Token);
			testDriver.getMapValues().put("Token",Token);
		}
	}

	public void getResponseForAPostRequestWithHeaderParam(TestDriver testDriver,Hashtable<String,String> params) throws ParseException{
		DriverScript.logMessage(testDriver,"info", "getResponseForAPostRequestWithHeaderParam keyword is invoked");
		String Requestdata = params.get("SecondTestServiceRequest");
		String URL =  params.get("URI");
		String session = params.get("HeaderValue");
		String Token =  testDriver.getMapValues().get("Token");
		Token = params.get("Token");
		Header header =new Header(session, Token);
		Response response =  RestAssured.given().contentType("application/json").header(header).body(Requestdata)
				.when().post(URL);	 
		if(response != null && response.body().toString().length() > 0){
			DriverScript.logMessage(testDriver,"info"," Response from the Webservice"+response.prettyPrint());
			//Parser to fetch attribute value in response body
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject)parser.parse(response.asString());
			JSONArray msg = (JSONArray) jsonObject.get("Data");
			//Validating account number of all users in the response body
			for(int i=0;i<msg.size();i++)
			{
				JSONObject AccountNumber = (JSONObject)msg.get(i);
				String UserAccountNumber = (String) AccountNumber.get("AccountNumber");
				if(params.get("AccountNumber").equals(UserAccountNumber))
				{
					DriverScript.logMessage(testDriver,"info"," Account Number is matching with "+ UserAccountNumber );
				}
			}  
			Long statusCode=(Long) jsonObject.get("StatusCode");
			testDriver.getMapValues().put("StatusCode",statusCode.toString());
		}
	}
	
	public void getResponse(TestDriver testDriver,Hashtable<String,String> params) throws ParseException{
		DriverScript.logMessage(testDriver,"info", "getResponse keyword is invoked");
		String URI = (String) params.get("getURL1");
		DriverScript.logMessage(testDriver,"info"," URI is "+ URI);
		Response response = null;
		try{
		response =  RestAssured.given().contentType("application/json").when().get(URI);
		System.out.println(response.asString());
		System.out.println(response.getStatusCode());
		if (response.toString() != null && response.toString() != "") {
			testDriver.setResponse(response);
		
	}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
		
		public void postResponse(TestDriver testDriver,Hashtable<String,String> params) throws ParseException{
			DriverScript.logMessage(testDriver,"info", "getResponse keyword is invoked");
			String URI = (String) params.get("postURL1");
			String Requestdata = (String) params.get("RequestBody");
			DriverScript.logMessage(testDriver,"info"," URI is "+ URI);
			Response response = null;
			try{
			response =  RestAssured.given().contentType("application/json").body(Requestdata).when().post("http://www.google.com");
			System.out.println(response.asString());
			System.out.println(response.getStatusCode());
			if (response.toString() != null && response.toString() != "") {
				testDriver.getMapValuesResponse().put("postResponse", response);
			
		}
			}catch(Exception e){
				e.printStackTrace();
			}
			
		
	}
}

package com.automation.framework.utilities;

import java.util.Hashtable;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;

import com.automation.framework.actionInterpreter.RWDMobileWeb;
import com.automation.framework.actionInterpreter.RWDMobileWeb.Action;
import com.automation.framework.core.DriverScript;
import com.automation.framework.exceptions.MobileAdaptorException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.exceptions.WebAdaptorException;
import com.automation.framework.pojs.TestDriver;


public class Mail {
	 static boolean repeatTask = false;
	 
      public static String fetchUrlFromMail(TestDriver testDriver,Hashtable<String, String> testData) throws WebAdaptorException, MobileAdaptorException, ObjectNameNotFoundException{
            Properties props = new Properties();
            String url = null;
           
              props.setProperty("mail.store.protocol", "imaps");
              try {
                  Session session = Session.getInstance(props, null);
                  Store store = session.getStore();
                  store.connect("imap.gmail.com", testDriver.getGlobalParamMap().get("testUsername"),testDriver.getGlobalParamMap().get("testpassword"));
              
                  Folder inbox = store.getFolder("INBOX");
                  inbox.open(Folder.READ_ONLY);
                  int counter=0;
                  int repeatFlag=0;
                  String orderNo= testData.get("OrderNumber");
                  boolean flag = false;
                  while(counter<3 && repeatFlag<2){
                	  
	                  Message msg1 = inbox.getMessage(inbox.getMessageCount()- counter);
	                  Address[] in = msg1.getFrom();
	                  for (Address address : in) {
	                      System.out.println("FROM:" + address.toString());
	                  }
	                  Object content = msg1.getContent();  
	                  if (content instanceof String)  
	                  {  
	                	  
	                      String body = (String)content;
	                            
	                      if(body.contains(orderNo)){
	                  
		                      String findUrlStr = "name=\"HTML Version\"  href=";
		                      int urlIndex = body.indexOf(findUrlStr) + findUrlStr.length() +1;
		                      for (int i = urlIndex; i < body.length(); i++) {
		                            if(body.charAt(i)=='\"'){
		                                  url = body.substring(urlIndex,i);
		                                  break;
		                            }
		                      }
		                      flag = true;      
	                      }
                	  
	                  }  
	                  else if (content instanceof Multipart)  
	                  {  
	                      Multipart mp = (Multipart)content; 
	                      BodyPart bp = mp.getBodyPart(0);
	                      String bodyContent = (String) bp.getContent();
	                      if(bodyContent.contains(orderNo)){
		                      String findUrlStr = "View in your browser";
		                     
		                      int urlIndex = bodyContent.indexOf(findUrlStr) + findUrlStr.length() +1;
		                      for (int i = urlIndex; i < bodyContent.length(); i++) {
		                            if(bodyContent.charAt(i)=='>'){
		                                  url = bodyContent.substring(urlIndex,i);
		                                  break;
		                            }
		                                  
		                            }
		                      flag = true;  
	                      }
	                     
	                  }
	                  counter++;
                	  if(counter==3){
                		  RWDMobileWeb.invokeKeyword(testDriver, Action.wait,"wait.high");
                		  RWDMobileWeb.invokeKeyword(testDriver, Action.wait,"wait.medium");
                		  counter=0;
                		  repeatFlag++;
                	  }
                	  if(flag){
                		  break;
                	  }
                  }
                  
           if(repeatFlag==1){
        	   DriverScript.logMessage(testDriver,"testStepFail","order confirmation mail for order number "+ orderNo + "is not recieved in inbox");
        	   throw new Exception(new Throwable("order confirmation mail for order number "+ orderNo + "is not recieved in inbox"));
           }

      } catch (Exception mex) {
    	  if(!repeatTask){
    		  repeatTask= true;
    		  RWDMobileWeb.invokeKeyword(testDriver, Action.wait,"wait.low");
    		  fetchUrlFromMail(testDriver, testData);
    	  }
    	  else{
    		  
    		  mex.printStackTrace();
    	  }
    		  
       
    }
              return url;
      }

}

package com.mystore.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

//This readconfig file is used to read the data(key values) from the config properties file

//First create an object of the Properties class -> Built in class in java jo key=value format ki
	//file padhne ke liye bani hai
	//Iska object constructor k inside islye hota h taaki ReadConfig file ka object bante he yeh constructor automatically run
	//hoga jisse turant properties file load ho jaaye — manually alag se koi method call na karna pade.


//Input File Stream -> Properties class akele file nahi padh sakti. Usse koi "pipe" chahiye jo file ka data uske paas pahunchaaye.
		//FileInputStream wahi pipe hai — disk pe rakhi file ka data byte-by-byte padhke Properties ke paas deta hai.


//getProperty("baseurl") kya karta hai?
//Properties object mein saara data loaded hai — getProperty() uss data mein se key ke naam se value nikalti hai:


//basically -> properties.load() ne saara data memory mein load kiya


public class ReadConfig {

	
	Properties properties;
	
	
	//Config.properties ka path 
	String path = "C:\\Users\\himan\\OneDrive\\Desktop\\Hybrid Automation Framework\\MyStore\\Configuration\\config.properties";
	
	
	public ReadConfig(){
		
		try {
		properties = new Properties();	
		FileInputStream fis = new FileInputStream(path);
		properties.load(fis);
		//Ab properties object ke andar sara data aa gya
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
			}
	
	
	public String getBaseUrl() {
		
		String value = properties.getProperty("baseUrl");
		
		if(value!=null) {
			return value;
			}else{
				
				throw new RuntimeException("URL not found");
			}
	}
	
	public String getbrowser() {
		
		String value = properties.getProperty("browser");
		if(value!=null) {
			return value;
		}else {
			throw new RuntimeException("Browser not found");
		}
			
	}	
	
}





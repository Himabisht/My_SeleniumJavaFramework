package com.mystore.testcases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.mystore.utilities.ReadConfig;


//Note -> Jo bhi executable statements h vo sabhi methods k inside he hongi


public class BaseClass {

	
	//Create a url and browser variable using ReadConfig.java file.
	
	
	ReadConfig readconfig = new ReadConfig();
	
	String url = readconfig.getBaseUrl();
	String browser = readconfig.getbrowser();
	
	
	
	//Create a webDriver object 
	public static WebDriver driver;
	
	//Create a reference for Logger
	public static Logger logger;
	
	//Create a setup browser method
	
	@BeforeClass
	public void setup(){
		
	switch(browser.toLowerCase())
	{
	case "chrome":
		driver = new ChromeDriver();
		break;
		
	case "edge":
		driver = new EdgeDriver();
	    break;
	    
	case "firefox":
		driver = new FirefoxDriver();
		
	default:
		driver = null;
		break;
	}	
	
	
	
	//Create an implicitly wait method
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
		
	
	//Create a method for log4j2
	logger = LogManager.getLogger("MyStore");
	
	//Open browser
	driver.get(url);
	logger.info("URL opened successfully");
	
	}
	
	//Create a tear down method
	
	@AfterClass
	public void tearDown() {
		driver.close();
		
	}
	
	//Create a method to capture screenshot
	
	public void captureScreenshot(WebDriver driver, String testName) throws IOException {
		
		//step1: Convert webdriver object to screenshot interface
		TakesScreenshot screenshot = ((TakesScreenshot) driver);
		
		//step2: call getscreenshot method to create image file
		//getScreenshotAs - Yeh actual kaam karta hai — browser ka screenshot leta hai.
		//outputType.FILE - Screenshot ko temporary File ke roop mein do
		//File src - Temporary file ban gayi — par yeh system ke temp folder mein hai. Isko permanent jagah
		//copy karna padega — warna program khatam hone pe delete ho jaayegi.
		File src = screenshot.getScreenshotAs(OutputType.FILE);
		
		
		
		File dest = new File(System.getProperty("user.dir")+"//Screenshots//"+ testName+".png");
		
		//Step3: copy image file to destination
		//FileUtils — Apache Commons IO ki class hai — file operations easy banati hai.
		//copyFile(src, dest) — Temp folder se screenshot uthao aur Screenshots folder mein permanent save karo.
		FileUtils.copyFile(src, dest);
	
	
	//Yha issue yeh h ki mujhe manually har jagah screenshot method ko call karna padh rha h. Isko code ko maine directly
	//Listener class mein add kar diya h.
	//}
}
}

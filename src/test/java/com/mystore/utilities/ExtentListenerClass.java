package com.mystore.utilities;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.mystore.testcases.BaseClass;

//ITestListener -> Interface present in TestNG 
//Jab test pass/fail hoga toh TestNG automatically is class ke methods call karega.

public class ExtentListenerClass implements ITestListener {

	
	//Class -> HTML file banane wala
	// jo actual HTML file banati hai — jise tum browser mein khologe. Abhi sirf declare kiya, koi kaam nahi hua.
	ExtentSparkReporter htmlReporter;
	
	//Class -> Report ka master controller 
	// poori report ka boss hai. Yeh manage karta hai — kitne tests hain, pass/fail kaun sa hai, system info kya hai. 
	//Akela kaam nahi karta — isko ek reporter (jaise SparkReporter) se attach karna padta hai.
	ExtentReports reports;
	
	//Class -> Ek test ki entry
	//Har ek test method ki individual entry report mein. Jab loginTest chale toh ek ExtentTest object banega, 
	//jab checkoutTest chale toh doosra. Yeh woh object hai jisme tum likhte ho — PASS hua, FAIL hua, screenshot yeh hai.
	ExtentTest test;
	
	//
	public void configureReport(){
	
	//ExtentListenerReportDemo.html -> Report Name	
	htmlReporter = new ExtentSparkReporter("ExtentListenerReportDemo.html");	
	
	
	
	// poori report ka master ready hai. Par abhi yeh akela hai — isko pata nahi ki HTML file kahan banani hai.
	reports = new ExtentReports();
	
	//Yahan reports aur htmlReporter ko jooda — ab master ko pata hai ki HTML file banani hai aur kahan banani hai.
	reports.attachReporter(htmlReporter);
	
	
	//Add environment details/info to reports
	reports.setSystemInfo("Machine", "testPC");
	reports.setSystemInfo("username", "Himanshu");
	reports.setSystemInfo("OS", "Windows");
	reports.setSystemInfo("Browser", "Chrome");
	//Yha abhi browser chrome h jo harded coded h hum isko dynamic bhi kar sakte h
	//ReadConfig() obj = new ReadConfig()
	//ReadConfig().getBrowser(); -> browser value -> chrome/edge
	
	
	
	//Configuration to change look and feel of the report
	htmlReporter.config().setDocumentTitle("Extent Report Demo");   //Tab name
	htmlReporter.config().setReportName("Test Report");   //Heading 
	htmlReporter.config().setTheme(Theme.DARK);
	
	}
	
	
	//Suite shuru hote hi report setup ho jaaye -> configReport()
	//onStart method is called when any Test starts
	public void onStart(ITestContext Result) {
		
		configureReport();
		System.out.println("On Start Method invoked......");
		System.out.println("====================================================");
		System.out.println("TEST SUITE STARTED: " + Result.getName());
		System.out.println("Total Tests to Run: " + Result.getAllTestMethods().length);
		System.out.println("Start Time: " + new java.util.Date());
		System.out.println("====================================================");
		
	}
	
	
	//onFinish method is called after all tests are executed
	public void onFinish(ITestContext Result) {
		System.out.println("On Finish method invkoked......");
		System.out.println("====================================================");
	    System.out.println("TEST SUITE FINISHED: " + Result.getName());
	    System.out.println("Passed  : " + Result.getPassedTests().size());
	    System.out.println("Failed  : " + Result.getFailedTests().size());
	    System.out.println("Skipped : " + Result.getSkippedTests().size());
	    System.out.println("End Time: " + new java.util.Date());
	    System.out.println("====================================================");
		
		//Yeh MUST hai — bina iske HTML file save nahi hogi!
		reports.flush();
		//reports.flush() sabse important line hai — yeh saara data disk pe likhta hai. Bina flush() ke HTML file ya toh empty hogi ya banegi hi nahi.
		
	}
	
	//When a test case is failed, then this method will be called
	//Result.getName() -> Return the name of the method/testcase name
	public void onTestFailure(ITestResult Result) {
	System.out.println("Name of the method failed: "+ Result.getName());
	
	
	// ✅ Yeh line add karo — Reason bhi console mein dikhega
    System.out.println("Reason for failure: " + Result.getThrowable().getMessage());

	
	
	//reports.createTest() — HTML report mein ek naya row/entry banata hai us test ke liye. Result.getName() us row ka heading hoga.
	test = reports.createTest(Result.getName());
	test.log(Status.FAIL, MarkupHelper.createLabel("Name of the failed test case is:" + Result.getName(),ExtentColor.RED));	
	
	
	//Failure ka reason bhi add karo report mein, warna sirf naam se pata nahi chalega kya fail hua
	test.log(Status.FAIL, "Reason: " + Result.getThrowable().getMessage());
	
	
	
	//Now call the screenshot method here to add the screenshot code
	//First create an object of the base class and then call the method
	BaseClass bc = new BaseClass();
	try {
		bc.captureScreenshot(BaseClass.driver, Result.getName());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	//Now we will write a code to add a screenshot in our report
	
	
	//System.getProperty("user.dir")-> Project ka root folder automatically detect karta hai and Return karta hai → "C:\Users\Himanshu\eclipse-workspace\MyStore"
	// Screenshots folder ka naam add kiya
	// Result → "C:\Users\Himanshu\eclipse-workspace\MyStore\Screenshots\"
	//+ Result.getName() + ".png" -> Final result → "C:\Users\Himanshu\eclipse-workspace\MyStore\Screenshots\loginTest.png"
	String screenshotpath = System.getProperty("user.dir")+ "\\Screenshots\\"+ Result.getName()+".png";
	
	
	//File -> class file in java ->  yeh disk pe rakhi file ko represent karti hai.
	//Abhi sirf ek object bana — file actually read nahi hui, open nahi hui. Bas Java ko bataya ki "is path pe ek file hai."
	File screenshotfile = new File(screenshotpath);
	
	
	//exists() — File class ka method hai jo true ya false return karta hai —
    //File disk pe hai?  → true  → Andar jao, report mein add karo
	//File nahi hai?     → false → if block skip, kuch nahi hoga
	//Agar file exist na kare aur directly attach karne ki koshish karo toh report mein broken image aayegi ya exception aa sakta hai. Isliye pehle check karo.
	if(screenshotfile.exists()) {
		
		//test.addScreenCaptureFromPath(screenshotpath) -> Report mein screenshot image embed karta hai
		// screenshotpath → woh path jo upar banaya tha
		// Return karta hai → MediaEntityBuilder object
		//test.fail -> // Report mein FAIL entry banata hai and Saath mein screenshot bhi attach ho jaati hai
		//test.fail("Captured screenshot is below:"+ test.addScreenCaptureFromPath(screenshotpath));
		
		
		test.log(Status.FAIL,
			    "Screenshot below: ",
			    MediaEntityBuilder.createScreenCaptureFromPath(screenshotpath).build()
			);
		
		//Note -> test.addScreenCaptureFromPath(screenshotpath) -> yeh method internally ek ExtentTest object return karta hai, not actual image text
		// "Captured screenshot is below:" + object -> karte ho, Java us object ka default toString() print karta hai:
		// Java uska toString() call karega
		// "com.aventstack.extentreports.ExtentTest@4d7aaca2" ← Yahi print hua ❌
	}
	}
	
	//When a test case is skipped, then this method will be called
	public void onTestSkipped(ITestResult Result){
	System.out.println("Name of the method skipped:"+ Result.getName());	
	test = reports.createTest(Result.getName());
	test.log(Status.SKIP, MarkupHelper.createLabel("Name of the skipped test case is:"+ Result.getName(),ExtentColor.YELLOW));	
	}
	
	//When a test case is passed, then this method will be executed
	public void onTestSuccess(ITestResult Result) {
	System.out.println("Name of the method passed:"+ Result.getName());
	test = reports.createTest(Result.getName());
	
	
	//test.log(Status.PASS, ...) — Us entry mein PASS status likho. Status.PASS se report mein green indicator aata hai.
	//MarkupHelper.createLabel(..., ExtentColor.GREEN) — Sirf text likhne ki jagah ek colored label/badge banata hai report mein —
	//ExtentColor.GREEN — badge ka color green hoga kyunki test pass hua.
	test.log(Status.PASS, MarkupHelper.createLabel("Name of the passed tet case is:"+ Result.getName(),ExtentColor.GREEN));
	
	
	BaseClass bc = new BaseClass();
	try {
		bc.captureScreenshot(BaseClass.driver, Result.getName());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
     String screenshotpath = System.getProperty("user.dir")+ "\\Screenshots\\"+ Result.getName()+".png";
	
    
	File screenshotfile = new File(screenshotpath);

	if(screenshotfile.exists()) {
		
		test.log(Status.PASS,
			    "Screenshot below: ",
			    MediaEntityBuilder.createScreenCaptureFromPath(screenshotpath).build()
			);
	
	}
	
	
	}
	
	//When a test case is started then this method will be executed
	public void onTestStart(ITestResult Result) {
	System.out.println("Name of the test method started: "+ Result.getName());		
	}
	
}

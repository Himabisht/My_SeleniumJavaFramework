package com.mystore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class indexPage {

	//Create an object of WebDriver
	WebDriver ldriver;
	
	//Create a constructor
	public indexPage(WebDriver rdriver) {
		
	//Use this keyword if you have used both the ldriver and rdriver as driver
	ldriver = rdriver;
	PageFactory.initElements(rdriver, this);
	}
	
	//Identify Elements
	@FindBy(xpath="(//b)[1]")
	WebElement link;
	
	
	//Identify action on web Elements
	public void linkclick() {
		link.click();
	}
	
	
}

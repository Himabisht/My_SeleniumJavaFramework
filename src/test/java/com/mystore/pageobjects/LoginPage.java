package com.mystore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage{
	
	//Create an object of WebDriver
	WebDriver ldriver;
	
	
	//create a constructor
	public LoginPage(WebDriver rdriver){
	ldriver = rdriver;
	PageFactory.initElements(rdriver, this);
	}
	
	//Identify Elements
	
	//Email
	@FindBy(xpath="//input[@type='email']")
	WebElement email;
	
	
	//Password
	@FindBy(xpath="//input[@type='password']")
	WebElement pwd;
	
	//Submit button
	@FindBy(xpath="//button[@id='submitLoginBtn']")
	WebElement submit;
	
	
	//Identify action on web elements
	
	//Enter Email
	public void enterEmail(String emaill) {
	email.sendKeys(emaill);	
	}
	
	
	//Enter Password
	public void enterPwd(String password){
	pwd.sendKeys(password);	 
	}
	
	//Click submit button
	public void submitbtn() {
	submit.click();	
	}

}

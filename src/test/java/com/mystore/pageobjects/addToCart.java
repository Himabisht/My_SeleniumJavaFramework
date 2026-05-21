package com.mystore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class addToCart {
	
	
	
	//WebDriver object
	WebDriver ldriver;
	
	
	
	//Constructor
	public addToCart(WebDriver rdriver) {
	ldriver = rdriver;
	PageFactory.initElements(rdriver, this);
	}
	
	
	//Identify WebElements
	
	//Button
	@FindBy(xpath="(//button[text()='ADD TO CART'])[1]")
	WebElement addcrtbutton;
	
	//Text
	@FindBy(xpath="//span[@class='cart-item-title']")
	WebElement txt;

	
	
	//Perform Operations
	public void clickaddcrt(){
	addcrtbutton.click();	
	}
	
	//Get text
	public String mascara() {
	String text = txt.getText();	
	return text;	
	}
	
}


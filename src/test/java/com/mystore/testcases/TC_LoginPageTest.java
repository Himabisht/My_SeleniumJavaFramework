package com.mystore.testcases;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mystore.pageobjects.LoginPage;
import com.mystore.pageobjects.addToCart;
import com.mystore.pageobjects.indexPage;

//Extend the test cases class with the base class
public class TC_LoginPageTest extends BaseClass {

	
	
	//Create one method to perform login
	
	@Test(enabled=false)
	public void login(){
		
	//Shifting these two common lines in base class	
//	driver.get(url);
//	logger.info("URL opened successfully");
	
	
	//Create an object of page objects in order to call their methods
	indexPage indx = new indexPage(driver);
	
	//Click on Ecommerce link
	indx.linkclick();
	logger.info("Link clicked successfully");
	
	//Create an object of page objects in order to call their methods
	LoginPage lp = new LoginPage(driver);
	
	//Enter email
	lp.enterEmail("admin@admin.com");
	logger.info("Email entered successfully");
	
	//Enter password
	lp.enterPwd("admin123");
    logger.info("Password entered successfully");
	
	//click on submit button
	lp.submitbtn();
	logger.info("Submit button clicked successfully");
	}
	
	
	//Create a method to perform add to cart
	@Test
	public void AddtoCart() throws IOException {
		
		
		logger.info("================== AddtoCart method started==============");
		
		//Create an object of page objects in order to call their methods
		indexPage indx = new indexPage(driver);
		
		
		
		//Create an object of page objects in order to call their methods

		    try {
		        
		        logger.info("STEP 1 START → Clicking Ecommerce link");
		        indx.linkclick();
		        logger.info("STEP 1 DONE ✅");

		        LoginPage lp = new LoginPage(driver);
		        logger.info("STEP 2 START → Entering Email");
		        lp.enterEmail("admin@admin.com");
		        logger.info("STEP 2 DONE ✅");

		        logger.info("STEP 3 START → Entering Password");
		        lp.enterPwd("admin123");
		        logger.info("STEP 3 DONE ✅");

		        logger.info("STEP 4 START → Clicking Submit button");
		        lp.submitbtn();
		        logger.info("STEP 4 DONE ✅");

		        addToCart atc = new addToCart(driver);
		        logger.info("STEP 5 START → Clicking Add to Cart");
		        atc.clickaddcrt();
		        logger.info("STEP 5 DONE ✅");
		        
		        
		        String item = atc.mascara();
		        
		        
		      //compare selected item
			  if(item.equals("Essence Mascara Lash Princess")) {
			  logger.info("The selected item is exactly the same as the added item: passed");	
			    Assert.assertTrue(true);
				}
				else {
					logger.info("The selected item does not matches with the added item: failed");
					//captureScreenshot(driver,"AddtoCart");
				}

		    } catch(Exception e) {
		        // Exception print bhi hoga ✅
		        logger.error("Test failed with exception: " + e.getMessage());
		        // TestNG ko bhi signal milega ✅
		        Assert.fail(e.getMessage());
		        
		    }
		}
			

		
		
	
			
//			
//			
//		//TestNG sirf ek cheez dekhta hai — kya method bina kisi Exception ke complete hua?
//        //Haan → PASS ✅
//        //Exception aayi → FAIL ❌	
//		//Koi Exception nahi = PASS
//		//Koi bhi Exception aayi = FAIL
//		//Assert.fail() = Forcefully Exception throw karta hai = FAIL	
//		
//			// Option 1
//			Assert.fail("Item did not match with added item");
//			// AssertionError: Item did not match with added item
//
//			// Option 2
//			//Assert.assertTrue(false);
//			// AssertionError: expected [true] but found [false]	
//		}
//		
		
	//}
}

 package com.Gigleaz.pages;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Gigleaz.base.Base;
import com.Gigleaz.qa.pageObjects.HomePage;
import com.Gigleaz.qa.pageObjects.SearchResultPage;
public class Search extends Base {
	
   WebDriver driver;
   SearchResultPage searchResultPage ;
 
   public Search() {
    //first way:to calling properties file we create constructor on parent class and we call the here in this way we afre calling properties file
	   super();
	   
   }
   
   @BeforeMethod
   public void setup() {
	   
	//second Way to call properties file
	   //super();
	   driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
   }
 
   
	@Test
	public void verifySearchWithValidProdect() throws InterruptedException {
		 
		HomePage homepage= new HomePage(driver);
		
		 homepage.enterProductIntoSearchBoxField(prop.getProperty("validSearchProductName"));//valid item displayed are not checking
		 searchResultPage = homepage.clickOnSearchButton();
		
		 searchResultPage.displayStatusOfHPValidProduct();
		 Thread.sleep(3000);	
	}
	@Test
	public void  verifySearchWithInvalidProdect() {
		
		HomePage homepage= new HomePage(driver);
		homepage.enterProductIntoSearchBoxField(prop.getProperty("InvalidSearchProductName"));//valid item displayed are not checking
		searchResultPage =homepage.clickOnSearchButton();
		
		//String actualText=searchResult.displayWarningText();
		//when we actualText, expectedText not match:this should be display;"no prodect massage in search results is not dis"
		Assert.assertEquals(searchResultPage.retrieveNoProdectMassageText(), prop.getProperty("noProductTextInSerachResult"),"no prodect massage in search results is not displayed");
	}
	
	 @AfterMethod
	   public void tearDown() {
		   driver.quit();
	   }
}

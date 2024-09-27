package com.Gigleaz.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Gigleaz.base.Base;
import com.Gigleaz.qa.Utils.Utilitys;
import com.Gigleaz.qa.pageObjects.AccountPage;
import com.Gigleaz.qa.pageObjects.HomePage;
import com.Gigleaz.qa.pageObjects.LoginPage;

public class LoginPages extends Base {
	WebDriver driver;
	LoginPage loginPage;

	public LoginPages() {
		super();
	}

	@BeforeMethod
	public void setUp() {

		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));

		HomePage homePage = new HomePage(driver);
		 //homePage.clickOnMyAccountDropDown();
		 //loginPage = homePage.clickOnloginOptoin();
		
//above 2 lines of code we are reducing in to one line
		 loginPage= homePage.navigateToLoginPage();
		 

	}

	@Test(priority = 1, dataProvider = "supplyTestData") // if you are not given any priority to the test case it will execute alphabetical order													 
	public void verifyLoginWithValidCredentials(String email, String password) throws InterruptedException {
		/*loginPage.enterEmailAddress(email);
		loginPage.enterPassword(password);
		AccountPage accountPage = loginPage.clickOnLoginButton();*/
		
// above 3 line code i made in one line in below
		AccountPage accountPage=loginPage.login(email, password);
		Assert.assertTrue(accountPage.checkingAccountPageIsDisplayed(),"account page is not displayed");
	}

	@DataProvider(name = "supplyTestData")
	public Object[][] supplyTestData() {
		Object[][] data = Utilitys.getTestDataFromExcel("LoginPage");// if we are passing excelFileName as a "LoginPage"
																		// we should pass as a same name "LoginPage"
		return data;
	}

	  @Test(priority = 2) 
	  public void verifyLoginWithInValidCredentials() {
		
		
		  
			/*loginPage.enterEmailAddress(Utilitys.generateEmailWithTimeStamp());
			loginPage.enterPassword(prop.getProperty("invalidPassword"));
			loginPage.clickOnLoginButton();	*/
		  
// above 3 line code i made in one line in below
		    loginPage.login(Utilitys.generateEmailWithTimeStamp(), prop.getProperty("invalidPassword"));
	        String ActualWarningMassage =loginPage.retrieveEmailPasswordNotMatchingWarningMassageText();
	        Assert.assertTrue(ActualWarningMassage.contains(prop.getProperty("expectedWarningMassage")));
	  
	  }
	 @Test
      public void verifyLoginWithInvalidEmailAndValidPassword() {
		
		 /*
    	  loginPage.enterEmailAddress(Utilitys.generateEmailWithTimeStamp());
    	  loginPage.enterPassword(prop.getProperty("validPassword"));
    	  loginPage.clickOnLoginButton();*/
		 
// above 3 line code i made in one line in below	  
    	  loginPage.login(Utilitys.generateEmailWithTimeStamp(), prop.getProperty("validPassword"));
    	  String ActualWarningMassage =loginPage.retrieveEmailPasswordNotMatchingWarningMassageText();
	      Assert.assertTrue(ActualWarningMassage.contains(prop.getProperty("expectedWarningMassage")),"warining massage was not displayed on page");
    	  
      }
	 @Test
	 public void verifyLoginWithValidEmailAndInvalidPassword() throws InterruptedException {
		
	/*	 
   	  loginPage.enterEmailAddress(prop.getProperty("validEmailId"));
   	  Thread.sleep(3000);
   	  loginPage.enterPassword(prop.getProperty("invalidPassword"));
   	  loginPage.clickOnLoginButton();*/

		// above 3 line code i made in one line in below
		 loginPage.login("validEmailId", prop.getProperty("invalidPassword"));
   	     String ActualWarningMassage =loginPage.retrieveEmailPasswordNotMatchingWarningMassageText();
	     Assert.assertTrue(ActualWarningMassage.contains(prop.getProperty("expectedWarningMassage")),"warining massage was not displayed on page");
   	  
     }
	 
	 @Test
	 public void verifyLoginWithoutProvidingCredentials() {
		   
		  loginPage.clickOnLoginButton();
		  
		  String ActualWarningMassage =loginPage.retrieveEmailPasswordNotMatchingWarningMassageText();
	      Assert.assertTrue(ActualWarningMassage.contains(prop.getProperty("expectedWarningMassage")),"warining massage was not displayed on page");
	 }
	 
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}

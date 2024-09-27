package com.Gigleaz.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Gigleaz.base.Base;
import com.Gigleaz.qa.pageObjects.HomePage;
import com.Gigleaz.qa.pageObjects.RegisterPage;

public class RegisterFunctionality extends Base {

	WebDriver driver;
	RegisterPage registerPage;

//constructor for loading properties file
	public RegisterFunctionality() {
		super();
	}

    @BeforeMethod
	public void setUp() throws InterruptedException {
		// we are calling method from base class to lunch browser and open url :below
		// line code line num=31
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		Thread.sleep(3000);
	}

	@Test
	public void verifyRegisteringAccountWithOutFillingAnyDetails()  {

	
		HomePage homePage= new HomePage(driver);
		homePage.clickOnMyAccountDropDown();
	    registerPage = homePage.clickOnRegisterOption();
		registerPage.clickOnContinueButton();
		String actual =registerPage.warningTextMassageElement();
		Assert.assertTrue(actual.contains(prop.getProperty("expectedData")), "worning massage is not displayed");
		

	}
	/*@Test
	public void verifyRegisteringAccountByProvidingAllFields() {
		
	}
	@Test
	public void verifyRegisteringAccountWithExistingEmailAddress() { 
		
	}*/

	@AfterMethod
	public void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}

}

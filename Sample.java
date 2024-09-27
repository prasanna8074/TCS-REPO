package com.Gigleaz.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Sample {

	 static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
	
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		driver.get("https://www.amazon.in/");
		
	
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("i phone 14 pro");
		
		driver.findElement(By.id("nav-search-submit-button")).click();
		Thread.sleep(3000);
		
		driver.get("https://www.amazon.in/");
		
		driver.get("https://www.amazon.in/");
		
		
		
		
		// prassa 
		
		
		driver.close();
		

	}

}

package com.revature.page;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmployeePage {

	private WebDriver driver;
	private WebDriverWait wdw; // Explicit waits
	
	@FindBy(xpath="//body[1]/div[2]/nav[1]/h1[1]")
	private WebElement welcomeHeading;
	
	public EmployeePage(WebDriver driver) {
		this.driver = driver;	
		this.wdw = new WebDriverWait(driver, Duration.ofSeconds(15)); // wait for a maximum of 15 seconds before throwing an exception
		
		// PageFactory initialization
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getWelcomeHeading() {
		return this.wdw.until(ExpectedConditions.visibilityOf(welcomeHeading));
	}
}
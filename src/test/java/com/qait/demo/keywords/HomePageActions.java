package com.qait.demo.keywords;

import org.openqa.selenium.WebDriver;

import com.qait.automation.getpageobjects.GetPage;

public class HomePageActions extends GetPage {
	
	WebDriver driver;

    public HomePageActions(WebDriver driver) {
        super(driver, "HomePage");
        this.driver = driver;
    }
    
	public void verifyUserIsOnHomePage(){
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("txt_lastLogin");
		isElementDisplayed("tab_active","HOME");
		logMessage("Assertion Passed :: User has successfully logged in");
	}
	
	public void clickMainTab(String tabName){
		element("lnk_mainTab",tabName).click();
		logMessage("User clicked on '"+tabName+"' tab");
	}
    
    
}

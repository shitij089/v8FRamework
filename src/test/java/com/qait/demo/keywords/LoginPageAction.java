package com.qait.demo.keywords;

import org.openqa.selenium.WebDriver;

import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.YamlReader;

public class LoginPageAction extends GetPage{

	
	public LoginPageAction(WebDriver driver) {
		super(driver, "loginPage");
	}

	public void clickOnLoginButton()
	{
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("btn_login");
		element("btn_login").click();
		logMessage("Clicked On login button");
	}
	

	public void submitLoginDetails()
	{
		isElementDisplayed("txt_email");
		element("txt_email").clear();
		element("txt_email").sendKeys(YamlReader.getData("username"));
		isElementDisplayed("txt_password");
		element("txt_password").clear();
		element("txt_password").sendKeys(YamlReader.getData("password"));
		isElementDisplayed("btn_submitLoginDetail");
		element("btn_submitLoginDetail").click();
		
		
	}
	
	
}

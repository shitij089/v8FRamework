package com.qait.demo.keywords;

import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.YamlReader;
import org.openqa.selenium.WebDriver;

public class HRISHomePageAction extends GetPage{


	public HRISHomePageAction(WebDriver driver) {
		super(driver, "hrisHomepage");
	}

	public String getProfileName()
	{
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("text_profile_name");
		logMessage("get profilr name as "+element("text_profile_name").getText());
		return element("text_profile_name").getText();
	}


}

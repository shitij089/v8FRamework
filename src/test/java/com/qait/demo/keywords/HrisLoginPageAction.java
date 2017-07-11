package com.qait.demo.keywords;

import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.YamlReader;
import org.openqa.selenium.WebDriver;

/**
 * Created by anilsingh on 07-07-2017.
 */
public class HrisLoginPageAction extends GetPage {
    public HrisLoginPageAction(WebDriver driver) {
        super(driver,"hrisLoginPage");
    }

    public void clickOnLoginButton(){

        wait.waitForPageToLoadCompletely();
        isElementDisplayed("login_container");
        element("login_container").click();
        logMessage("Clicked On login container");
    }

    public void submitLoginDetails()
    {
        isElementDisplayed("txt_login");
        element("txt_login").clear();
        element("txt_login").sendKeys(YamlReader.getData("username_hris"));
        isElementDisplayed("txt_password");
        element("txt_password").clear();
        element("txt_password").sendKeys(YamlReader.getData("password_hris"));
        isElementDisplayed("btn_sign_in");
        element("btn_sign_in").click();


    }


}

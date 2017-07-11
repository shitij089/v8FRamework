package com.qait.demo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qait.automation.TestSessionInitiator;

public class TestClass {

	TestSessionInitiator test;
	@BeforeClass
	public void initializeVaraible()
	{
		test= new TestSessionInitiator(this.getClass().getSimpleName());
	}
	
	@Test
	public void verifyUserCanSuccessfullyLogin()
	{
		test.launchApplication();
		test.loginPageAction.clickOnLoginButton();
		test.loginPageAction.submitLoginDetails();
		Assert.assertEquals(test.homePageAction.getProfileName(), "Tarang");
	}
	
	
	
	
	
	
}

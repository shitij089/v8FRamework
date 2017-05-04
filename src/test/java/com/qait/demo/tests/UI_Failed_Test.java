package com.qait.demo.tests;

import static com.qait.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qait.automation.TestSessionInitiator;

public class UI_Failed_Test {

	TestSessionInitiator test;
	String baseUrl;
	private String name;

	@BeforeClass
	public void Start_Test_Session() {
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		baseUrl =getYamlValue("baseUrl");
	}

	@BeforeMethod
	public void handleTestMethodName(Method method) {
		test.stepStartMessage(method.getName());
	}

	@Test
	public void Add_Computer_Test() {
		Assert.assertEquals(test.databaseActions.launchBaseURL(baseUrl), "Computers database",
				"Application is not launched successfully.");
		name = "TEST" + System.currentTimeMillis();
		Assert.assertTrue(test.databaseActions.addComputerfromFrontend(name));
		Reporter.log("[INFO]: Added a computer with name: "+name);
		Assert.assertEquals(name, test.databaseActions.validateComputerIsCreatedSuccessfully("epecting name"));
		Reporter.log("[TEST PASSED]: Computer with name: "+name+" added is displayed on searching");
	}

	
	
	@AfterMethod
	public void take_screenshot_on_failure(ITestResult result) {
		test.takescreenshot.takeScreenShotOnException(result);
	}

	@AfterClass
	public void close_Test_Session() {
		test.closeBrowserSession();
	}

}

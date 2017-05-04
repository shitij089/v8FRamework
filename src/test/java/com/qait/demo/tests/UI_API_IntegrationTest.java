package com.qait.demo.tests;

import static com.qait.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qait.automation.TestSessionInitiator;

public class UI_API_IntegrationTest {

	TestSessionInitiator test;
	String baseUrl;

	@BeforeClass
	public void Start_Test_Session() {
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		initVars();
	}

	@BeforeMethod
	public void handleTestMethodName(Method method) {
		test.stepStartMessage(method.getName());
	}

	private void initVars() {
		baseUrl = getYamlValue("baseUrl");

	}

	@Test
	public void Test01_Launch_Base_URL_Of_Application() {
		Assert.assertEquals(test.databaseActions.launchBaseURL(baseUrl), "Computers database",
				"Application is not launched successfully.");
	}

	private int count;

	@Test
	public void Test02_GET_COUNT_OF_COMPUTERS_FOUND_THROUGH_UI_BEFORE_ADD_COMPUTER_API_INVOKE() {
		count = test.databaseActions.getTotalCountOfComputerThroughUI();
	}

	private String name;

	@Test
	public void Test03_Add_a_Computer() {
		name = "TEST" + System.currentTimeMillis();
		Assert.assertTrue(test.databaseActions.addComputerThroughAPI(name));
	}

	@Test
	public void Test04_VALIDATE_COMPUTER_IS_ADDED_SUCCESSFULLY_THROUGH_API_() {
		Assert.assertEquals(name, test.databaseActions.validateComputerIsCreatedSuccessfully(name));
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

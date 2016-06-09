package com.qait.demo.tests;

import static com.qait.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.sql.ResultSet;

import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qait.automation.TestSessionInitiator;
import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.DataBaseConnecter;
import com.qait.automation.utils.YamlReader;

public class LoginTest {

	TestSessionInitiator test;
	String baseUrl;
	String tatocGameBaseUrl;
	DataBaseConnecter connector = new DataBaseConnecter();

	@BeforeClass
	public void Start_Test_Session() {
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		initVars();
		// test.launchApplication(baseUrl);
		test.launchApplication(tatocGameBaseUrl);
	}

	@BeforeMethod
	public void handleTestMethodName(Method method) {
		test.stepStartMessage(method.getName());
	}

	private void initVars() {
		// baseUrl = getYamlValue("baseUrl");
		tatocGameBaseUrl = getYamlValue("tatocGameBaseUrl");

	}

	// @Test
	// public void Step01_loginTest() {
	// // test.loginPage.enterLoginCredentials(getYamlValue("username"),
	// // getYamlValue("password"));
	// // test.homePage.verifyUserIsOnHomePage();
	// test.homePage.clickAdvanceCourse();
	//
	// // Connect to database
	// //YamlReader.setYamlFilePath();
	// connector.connectToDataBase(YamlReader.getYamlValue("database.host"),
	// YamlReader.getYamlValue("database.name"),
	// YamlReader.getYamlValue("database.user"),
	// YamlReader.getYamlValue("database.password"));
	// // driver.findElement(By.id("GlobalDateTimeText"));
	// String value = test.getDriver().findElement(By.id("symbol"))
	// .getAttribute("value");
	// String query = YamlReader.getYamlValue("database.query1").replace("?",
	// value);
	//
	// ResultSet resultset = connector
	// .getResultSetOnExecutingASelectQuery(query);
	//
	// String userName = null;
	// String pwd = null;
	// try {
	// while (resultset.next()) {
	// userName = resultset.getString(1);
	// pwd = resultset.getString(2);
	// // Do whatever you want to do with these 2 values
	// }
	// } catch (Exception ex) {
	// ex.getMessage();
	// }
	// test.loginPage.enterLoginCredentials(userName, pwd);
	//
	// }

	@Test
	public void testTatocGame() {

		test.homePage.clickGame();
		
	}

	@AfterMethod
	public void take_screenshot_on_failure(ITestResult result) {
		test.takescreenshot.takeScreenShotOnException(result);
	}

	@AfterClass
	public void close_Test_Session() {
		// test.closeBrowserSession();
	}

}

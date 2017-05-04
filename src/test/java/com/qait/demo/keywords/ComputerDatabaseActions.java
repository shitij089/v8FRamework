package com.qait.demo.keywords;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.config.RedirectConfig.redirectConfig;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.common.base.CharMatcher;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.qait.automation.getpageobjects.GetPage;

public class ComputerDatabaseActions extends GetPage {
	private WebDriver driver;

	public ComputerDatabaseActions(WebDriver driver) {
		super(driver, "ComputerDatabaseActions");
		this.driver = driver;
	}

	public String launchBaseURL(String baseUrl) {
		driver.get(baseUrl);
		return driver.getTitle();
	}

	public int getTotalCountOfComputerThroughUI() {
		return Integer
				.parseInt(CharMatcher.digit().retainFrom(driver.findElement(By.cssSelector("#main>h1")).getText()));
	}

	public boolean addComputerThroughAPI(String name) {
		RequestSpecification resSpec = given()
				.config(RestAssured.config().redirect(redirectConfig().followRedirects(false)));

		String finalURI = "http://computer-database.gatling.io/computers";

		HashMap<String, String> payload = new HashMap<>();
		payload.put("name", name);
		payload.put("introduced", "2017-05-03");
		payload.put("discontinued", "2017-07-03");
		payload.put("company", "2");

		Response response = resSpec.given().contentType(ContentType.JSON).with().body(payload).when().post(finalURI)
				.then().statusCode(303).extract().response();

		return response.getHeader("Set-Cookie").contains("success=Computer+" + name);
	}

	public String validateComputerIsCreatedSuccessfully(String computerName) {
		WebElement searchbox = driver.findElement(By.cssSelector("#searchbox"));
		searchbox.clear();
		searchbox.sendKeys(computerName);
		
		driver.findElement(By.cssSelector("#searchsubmit")).click();
		return driver.findElement(By.cssSelector("td>a")).getText().trim();
	}

	public boolean addComputerfromFront(String name) {
		element("add-computer").click();
		element("computer-name").sendKeys(name);
		element("computer-introduced-date").sendKeys("2017-05-04");
		element("computer-discontinued").sendKeys("2017-05-30");
		wait.hardWait(1);
		selectProvidedTextFromDropDown(element("company-dropdown"), "Apple Inc.");
		element("save").click();
		logMessage("[TEST PASSED]: Computer Added successfully");
		return true;
	}

}

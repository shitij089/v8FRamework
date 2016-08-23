package com.qait.qaInfotech.tests;

import static com.qait.automation.utils.YamlReader.getYamlValue;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qait.automation.TestSessionInitiator;
import com.qait.automation.utils.YamlReader;

public class TestClass {

	TestSessionInitiator test;
	String url, pageTitle;

	@BeforeClass
	public void Open_Browser_Window() {
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		url = getYamlValue("baseUrl");
	}

	@Test
	public void Step01_Verify_DashBoard_Page() {
		test.launchApplication(url);
		test.homePage.verifyMenuTab(getYamlValue("menuTab1"));
		test.homePage.verifyMenuTab(getYamlValue("menuTab2"));
		test.homePage.verifyIcon();
		test.homePage.verifySearchButton();
	}

	@Test
	public void Step02_Verify_All_Links_Are_Valid() {
		test.homePage.validateLinks();
	}

	@Test
	public void Step03_Verify_All_Images_Should_Be_Loaded() {
		test.homePage.validateImages();

	}

	@Test
	public void Step04_Verify_Valid_Searches() {
		test.homePage.validSearches(getYamlValue("dataForSearch"));
	}

	@Test
	public void Step05_VerifyContactFormSubmissionMessage() {
		test.launchApplication(url);
		test.homePage.verifyMenuTab(getYamlValue("menuTab2"));
		test.homePage.sendInformationInContactForm("Name",
				getYamlValue("enquiryDetails.name"));
		test.homePage.sendInformationInContactForm("Phone",
				getYamlValue("enquiryDetails.phone"));
		test.homePage.sendInformationInContactForm("E-mail",
				getYamlValue("enquiryDetails.email"));
		test.homePage.expandContactTypeDropDown();
		test.homePage
				.selectContactType(getYamlValue("enquiryDetails.enquiryType"));
		test.homePage
				.sendContactMessage(getYamlValue("enquiryDetails.message"));
		test.homePage.clickOnSubmitButtonOfContactForm();
		test.homePage.verifyContactFormResponseMessage();
	}

	@Test
	public void Step06_Verify_Request_For_Quote_Page() {
		test.launchApplication(url);
		test.homePage.expandQuoteImage();
		test.homePage.clickOnRequestForQuote();
		test.homePage.verifyRequestForQuoteUrl();
		pageTitle = test.homePage.getTitle();
		test.homePage.verifyTitleOfPage(pageTitle,
				getYamlValue("title.requestForQuotePage"));
	}

	@Test
	public void Step07_Verify_Upload_Your_Cv_Page() {
		test.launchApplication(getYamlValue("cvUrl"));
		pageTitle = test.homePage.getTitle();
		test.homePage
				.verifyTitleOfPage(pageTitle, getYamlValue("title.cvPage"));
	}

	@Test
	public void Step08_Verify_All_Videos() {
		test.launchApplication(getYamlValue("qa-tvUrl"));
		test.homePage.verifyVideos();

	}

	@Test
	public void Step09_Verify_Application_Accessibility_Page() {
		test.launchApplication(getYamlValue("applicationAccessibilityUrl"));
		pageTitle = test.homePage.getTitle();
		test.homePage.verifyTitleOfPage(pageTitle,
				getYamlValue("title.applicationAccessibility"));
	}

	@Test
	public void Step10_Verify_Case_Studies_Download_Form() {
		test.launchApplication(getYamlValue("caseStudyurl"));
		test.homePage.verifyCategoryDownloadForm();
	}

	@Test
	public void Step11_Verify_White_Papers_Download_Form()
	{
		test.launchApplication(getYamlValue("whitePaperUrl"));
		test.homePage.verifyCategoryDownloadForm();
		
	}
	
	@Test
	public void Step11_Verify_Blogs_Open_Successfully() {

		test.homePage.verifyHeadingBlogs();

	}

	@AfterMethod
	public void take_screenshot_on_failure(ITestResult result) {
		test.takescreenshot.takeScreenShotOnException(result);
	}

	@AfterClass
	public void Close_Browser_Session() {
		test.closeBrowserWindow();
	}

}

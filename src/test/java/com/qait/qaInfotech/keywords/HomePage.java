package com.qait.qaInfotech.keywords;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.print.attribute.standard.PageRanges;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.YamlReader;

public class HomePage extends GetPage {

	WebDriver driver;
	String temp, time[];
	int currentTime;

	public HomePage(WebDriver driver) {
		super(driver, "HomePage");
		this.driver = driver;
	}

	public void verifyMenuTab(String tabName) {
		Assert.assertTrue(isElementDisplayed("btn_MenuTab", tabName));
		logMessage("ASSERT PASSED : verify menu tab !!");
	}

	public void verifyIcon() {
		isElementDisplayed("img_homeIcon");
		Assert.assertTrue(element("img_homeIcon").getAttribute("src").contains(
				"qainfotech_logo"));
		logMessage("ASSERT PASSED :: verify home icon");
	}

	public void verifySearchButton() {
		Assert.assertTrue(isElementDisplayed("btn_search"));
		logMessage("ASSERT PASSED : verify search buton !!\n");
		Assert.assertTrue(isElementDisplayed("txt_search"));
		logMessage("ASSERT PASSED : verify search textfield\n");
	}

	public boolean getResponseCode(String url) {
		boolean isValid = false;
		try {
			URL u = new URL(url);
			HttpURLConnection h = (HttpURLConnection) u.openConnection();
			h.setRequestMethod("GET");
			h.connect();
			if (h.getResponseCode() == 200) {
				isValid = true;
			} else {
				logMessage("link : " + url + " is broken.\n");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return isValid;
	}

	public void validateLinks() {
		logMessage("Step: verifying links .....\n");
		int invalidLinks = 0;
		Document doc = null;
		try {
			doc = Jsoup.connect(YamlReader.getYamlValue("baseUrl")).get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Elements links = doc.select("a");

		for (Element link : links) {
			if (link.attr("abs:href").contains(
					YamlReader.getYamlValue("containUrl"))) {
				if (!getResponseCode(link.attr("abs:href"))) {
					invalidLinks++;
				}
			}
		}
		Assert.assertEquals(invalidLinks, 0);
		logMessage("ASSERT PASSED : Verify no links are broken !!\n");

	}

	public void validateImages() {
		logMessage("Step: verifying images .....\n");
		int unloadedImages = 0;
		Document doc = null;
		try {
			doc = Jsoup.connect(YamlReader.getYamlValue("baseUrl")).get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Elements links = doc.select("img");

		for (Element link : links) {
			if (!getResponseCode(link.attr("abs:src"))) {
				unloadedImages++;
			}

		}
		Assert.assertEquals(unloadedImages, 0);
		logMessage("ASSERT PASSED : Verify all images loaded completely !!\n");

	}

	public void sendDataInSearchField(String searchData) {
		isElementDisplayed("txt_search");
		element("txt_search").sendKeys(searchData);
		logMessage("Step: " + searchData + " is filled in search field\n");
	}

	public void clickOnSearchButton() {
		isElementDisplayed("btn_search");
		element("btn_search").click();
		logMessage("Step : Click on search button\n");
	}

	public void validSearches(String searchData) {
		int invalidSearches = 0;
		sendDataInSearchField(searchData);
		clickOnSearchButton();
		for (WebElement element : elements("list_allsearchedParagraph")) {
			if (!element.getText().toLowerCase()
					.equals(searchData.toLowerCase())) {
				invalidSearches++;
			}
		}

		Assert.assertEquals(invalidSearches, 0);
		logMessage("ASSERT PASSED : Verify all searches are valid\n");

	}

	public void sendInformationInContactForm(String informationType,
			String informationValue) {
		isElementDisplayed("txt_contactDetails", informationType);
		element("txt_contactDetails", informationType).sendKeys(
				informationValue);
		logMessage("Step : " + informationValue + " is send in "
				+ informationType + " field\n");
	}

	public void expandContactTypeDropDown() {
		isElementDisplayed("drpdwn_contactType");
		element("drpdwn_contactType").click();
		logMessage("Step: expand contact type list\n");
	}

	public void selectContactType(String contactType) {
		isElementDisplayed("btn_contactType", contactType);
		element("btn_contactType", contactType).click();
		logMessage("Step: select " + contactType + " as contact type\n");
	}

	public void sendContactMessage(String message) {
		isElementDisplayed("txt_contactMessage");
		element("txt_contactMessage").sendKeys(message);
		logMessage("Step: " + message
				+ " is send in contact message text field!!\n");
	}

	public void clickOnSubmitButtonOfContactForm() {
		isElementDisplayed("btn_sendContactMessage");

		((JavascriptExecutor) driver)
				.executeScript("document.getElementById('home-page-contact-form').click()");
		// element("btn_sendContactMessage").click();
		logMessage("Step: Click on submit button of contact form\n");
	}

	public void verifyContactFormResponseMessage() {
		isElementDisplayed("txt_alertMessage");
		Assert.assertEquals(element("txt_alertMessage").getText(),
				YamlReader.getYamlValue("enquiryDetails.responseMessage"));
		logMessage("ASSERT PASSED: Verify contact form submitted successfully response message!!\n");

	}

	public void expandQuoteImage() {
		isElementDisplayed("img_quote");
		element("img_quote").click();
		logMessage("Step: Expand quote image !!\n");
	}

	public void clickOnRequestForQuote() {
		isElementDisplayed("btn_quote");
		element("btn_quote").click();
		logMessage("Step: click on request for quote\n");
	}

	public void verifyRequestForQuoteUrl() {
		Assert.assertEquals(getResponseCode(getCurrentURL()), true);
		logMessage("ASSERT PASSED: Verified request for quote url !!\n");

	}

	public String getTitle() {
		isElementDisplayed("txt_title");
		logMessage("Step: Get page title as : "
				+ element("txt_title").getText());
		return element("txt_title").getText();
	}

	public void verifyTitleOfPage(String titleFromPage, String titleFromYaml) {
		Assert.assertEquals(titleFromPage, titleFromYaml);
		logMessage("ASSERT PASSED: Verified Page title!!\n");
	}

	public void verifyHeadingBlogs() {
		logMessage("Step: verifying heading blogs !!");
		boolean flag = true;
		for (WebElement element : elements("list_headingBlog")) {
			if (!getResponseCode(element.getAttribute("href"))) {
				flag = false;
			}
		}
		Assert.assertEquals(flag, true);
		logMessage("ASSERT PASSED : Verified all heading blogs open successfully !!");

	}

	public void clickOnPlayButton() {
		isElementDisplayed("btn_playVideo");
		element("btn_playVideo").click();
		logMessage("Step: Click On Play Button");
	}

	public int getCurrentTime() {
		temp = element("frame_videoContent").getAttribute("class");
		temp = temp.replaceAll("inactive", "active");
		temp = "document.getElementById('" + "my-video" + "').className=\""
				+ temp + "\";";
		executeJavascript(temp);
		// isElementDisplayed("txt_currentTime");
		temp = element("txt_currentTime").getText().split("\n")[1];
		time = new String[2];
		time = temp.split(":");
		return Integer.parseInt(time[1]) + 60 * Integer.parseInt(time[0]);
	}

	public void clickOnCancelButton() {
		isElementDisplayed("btn_cancel");
		element("btn_cancel").click();
		logMessage("Step: Click on cancel button !!\n");
	}

	public void verifyVideosInGivenPage() {
		int count = 0;
		for (WebElement element : elements("list_videoLink")) {
			count++;
			wait.waitForElementToBeClickable(element);
			element.click();
			switchToFrame(element("frame_video"));
			clickOnPlayButton();
			wait.waitForElementToBeInVisible(By
					.xpath("//div[@class='vjs-loading-spinner']"));
			currentTime = getCurrentTime();
			while (currentTime == 0) {
				wait.hardWait(2);
				currentTime = getCurrentTime();
			}
			Assert.assertNotEquals(currentTime, 0);
			logMessage("Step: video" + count + " is played Successfully!!");
			switchToDefaultContent();
			clickOnCancelButton();
			if (count % 3 == 0)
				scrollDown(element);
			wait.hardWait(4);
		}
	}

	public void verifyVideos() {
		int count = 1;
		while (count < elements("list_pagination").size()) {
			count++;
			verifyVideosInGivenPage();
			element("arrow_next").click();
			wait.hardWait(4);
		}
		verifyVideosInGivenPage();
		Assert.assertEquals(element("arrow_next").getAttribute("class"),
				"disabled");
		logMessage("ASSERT PASSED: verified all videos are playing successfully !!\n");
	}

	void clickOnDownloadButton() {
		isElementDisplayed("btn_download");
		wait.waitForElementToBeClickable(element("btn_download"));
		wait.hardWait(2);
		element("btn_download").click();
		logMessage("Step : click on download button!!\n");
	}

	void verifyDownloadFormTitle() {
		isElementDisplayed("txt_titleDownloadForm");
		Assert.assertEquals(element("txt_titleDownloadForm").getText(),
				YamlReader.getData("title.downloadForm"));
		logMessage("ASSERT PASSED: verified download form title!!\n");
	}

	void verifyCategoryFormAtGivenPage() {
		String currentUrl;
		int noOfBlogs = elements("list_readMoreLink").size();
		for (int i = 0; i < noOfBlogs; i++) {
			currentUrl = getCurrentURL();
			elements("list_readMoreLink").get(i).click();
			clickOnDownloadButton();
			verifyDownloadFormTitle();
			driver.get(currentUrl);
			System.out.println("verify blog " + i);
		}
	}

	public void verifyCategoryDownloadForm() {
		int noOfPages = elements("list_pageNumbers").size();
		for (int i = 0; i < noOfPages - 2; i++) {
			verifyCategoryFormAtGivenPage();
			wait.hardWait(2);
			element("btn_next").click();
			wait.hardWait(4);
		}
		verifyCategoryFormAtGivenPage();
		logMessage("completely verify !!\n");
	}

}

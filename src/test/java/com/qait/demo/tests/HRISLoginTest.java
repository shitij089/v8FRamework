package com.qait.demo.tests;

import com.qait.automation.TestSessionInitiator;
import org.junit.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by anilsingh on 07-07-2017.
 */
public class HRISLoginTest {

    private TestSessionInitiator testSessionInitiator;

    @BeforeTest
    public void initializeVariable(){
        testSessionInitiator = new TestSessionInitiator(this.getClass().getName());
    }

    @Test
    public void verifyUseLoginOnHris(){
        testSessionInitiator.launchApplication();
        testSessionInitiator.hrisLoginPageAction.clickOnLoginButton();
        testSessionInitiator.hrisLoginPageAction.submitLoginDetails();
        Assert.assertEquals(testSessionInitiator.homePageAction.getProfileName(), "Anil");
    }

}

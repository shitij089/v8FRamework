package com.qait.demo.tests;

import static com.qait.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.GenericArrayType;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qait.automation.TestSessionInitiator;
import com.qait.automation.utils.YamlReader;
public class demo {
	
	TestSessionInitiator test;
	@BeforeClass
	public void Start_Test_Session() {
		test = new TestSessionInitiator(this.getClass().getSimpleName());
	}
	
	@Test
	public void launch_Appliction(){
	 test.launchApplication(getYamlValue("loginUrl"));   
	 }
}

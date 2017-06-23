/*package com.demo.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;

import io.appium.java_client.NetworkConnectionSetting;
import io.appium.java_client.android.AndroidDriver;

public class BaseClass {
   protected AndroidDriver driver;
	
	@BeforeClass
	@Parameters({ "appPackage", "appActivity"})
	public void SetupAndroidDrivers(String _appPackage, String _appActivity) throws MalformedURLException, InterruptedException{

		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		//Setting Capabilities
		capabilities.setCapability("deviceName", "DeviceName");
		capabilities.setCapability("newCommandTimeout", 100000);
	    capabilities.setCapability("noReset", true);
	    capabilities.setCapability("noRest", true);
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");
		capabilities.setCapability(CapabilityType.VERSION, "23");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", _appPackage);
		capabilities.setCapability("appActivity", _appActivity);
		
		System.out.println("Driver BEFORE is:" + driver);
				
			 try {
		 		 //Starts a new Android Driver
				 driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		     } catch (IOException e) {

		     }


	 System.out.println("Driver AFTER is:" + driver);
	}

	
	@AfterClass
	 public void End() {
	  driver.quit();
	 }

}*/
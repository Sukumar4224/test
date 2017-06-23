package script;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseClass {
	static WebDriver driver;
	
	@BeforeMethod
	public void openBrowser(){
		System.setProperty("webdriver.gecko.driver","/Users/sukumar/Documents/workspace/selenium/exes/geckodriver");
		driver=new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("http://pos.dkrt.in/");
	}
	
	@AfterMethod 
		
	public void CloseBrowser(){
		driver.close();
	}
	
}


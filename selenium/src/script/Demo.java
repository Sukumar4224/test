package script;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Demo {

static WebDriver driver;
	
	@BeforeMethod
	public void openBrowser(){
		System.setProperty("webdriver.gecko.driver","/Users/sukumar/Documents/workspace/selenium/exes/geckodriver");
		driver=new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://www.voonik.com/");
	}
	
	@Test
	
	public void home() throws Exception{
		
		 Actions a = new Actions(driver);
		 a.moveToElement(driver.findElement(By.xpath("(.//*[@id='menu_item_0'])[1]"))).perform();
		 Thread.sleep(5000);
		 
		 List<WebElement> WebElements=driver.findElements(By.className("main-menu"));
		 WebElements.size();
		
		for(WebElement w : WebElements){
			System.out.println(w.getText());
			String d = w.getText();
			if(d.contains("ACCESSORIES")){System.out.println("***********************");}

		}
		
		 
		 a.moveToElement(driver.findElement(By.xpath("(.//*[@id='menu_item_3'])[1]"))).click().perform();
		
		Thread.sleep(5000);
	}
	
	@AfterMethod
	
	public void close(){
		driver.close();
	}
	
}


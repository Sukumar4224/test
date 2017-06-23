package script;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Start {
	static AndroidDriver<AndroidElement> appDriver;

	public static void main(String[] args) throws Exception {
		
		
		DesiredCapabilities caps = new DesiredCapabilities();
		System.out.println("Let's start");
		   // caps.SetCapability("app-package", "com.myapp.test");
		    //caps.SetCapability("browserName", "");
		    caps.setCapability("device", "Android");
		   // caps.SetCapability("app-activity", "com.myapp.SplashActivity");
		    caps.setCapability("takesScreenshot", true);
		    caps.setCapability("version", "6.0");
		    caps.setCapability("deviceName", "emulator-5554");
		    caps.setCapability("app", "/Users/sukumar/Desktop/app.apk");
		    appDriver = new AndroidDriver<AndroidElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
		    appDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		    
		    appDriver.findElement(By.id("com.lenskart.app:id/edit_phone")).sendKeys("7777777776");
		    appDriver.findElement(By.id("com.lenskart.app:id/button_continue")).click();
		    
		    Thread.sleep(2000);
		    
		    String APP_SCROLLVIEW = "android.widget.ScrollView";
		    String DESTINATION_ELEMENT_TEXT= "See All";
		    ((AndroidDriver) appDriver).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(APP_SCROLLVIEW)).scrollIntoView(new UiSelector().text(DESTINATION_ELEMENT_TEXT))");
//		    appDriver.switchTo().activeElement().findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.RelativeLayout/android.widget.TextView"));
//		    appDriver.swipe(100, 100, 500, 500, 500);
//			appDriver.findElement(By.id("com.lenskart.app:id/hto")).click();
//		    System.out.println(appDriver.manage().window().getSize());
		    appDriver.quit();
	}

}

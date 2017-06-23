package script;

import io.appium.java_client.android.AndroidDriver;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
 
public class StartApplication {
 
		private static AndroidDriver driver;
		public static void main(String[] args) throws MalformedURLException, InterruptedException {
 
			//File classpathRoot = new File(System.getProperty("user.dir"));
			//File appDir = new File(classpathRoot, "/Apps/Amazon/");
			
 
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
			capabilities.setCapability("deviceName", "emulator-5554");
			capabilities.setCapability("platformVersion", "6.0");
			capabilities.setCapability("platformName", "Android");
			//capabilities.setCapability("app", app.getAbsolutePath());
			capabilities.setCapability("appPackage", "com.lenskart.app");
			capabilities.setCapability("appActivity", "com.lenskart.app.ui.onboarding.SplashActivity");
			
			File path = new File("/Users/sukumar/Desktop/app.apk");
			capabilities.setCapability("app", path.getAbsolutePath());
 
			driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
			Thread.sleep(10000);
			driver.quit();
 
	}
 
}
package script;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class Android {
	static AppiumDriver appDriver;
	public static void main(String[] args) throws Exception {
	
		
		
			DesiredCapabilities caps = new DesiredCapabilities();
		   caps.setCapability("app-package", "com.lenskart.app");
		    //caps.SetCapability("browserName", "");
		    caps.setCapability("device", "Android");
		    caps.setCapability("app-activity", "com.lenskart.app.ui.onboarding.SplashActivity");
		    caps.setCapability("takesScreenshot", true);
		    caps.setCapability("version", "6.0");
		    caps.setCapability("deviceName", "emulator-5554");
		    caps.setCapability("app", "/Users/sukumar/Desktop/app.apk");
		    appDriver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), caps);
		    appDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		
		
	}

}

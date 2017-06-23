package script;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;

import io.appium.java_client.android.AndroidDriver;

public class BaseClassAndroid {
	
	static AndroidDriver appDriver;
	
	@BeforeMethod 
	
	public void Launchapp() throws MalformedURLException{
		DesiredCapabilities caps = new DesiredCapabilities();
	   // caps.SetCapability("app-package", "com.myapp.test");
	    //caps.SetCapability("browserName", "");
	    caps.setCapability("device", "Android");
	   // caps.SetCapability("app-activity", "com.myapp.SplashActivity");
	    caps.setCapability("takesScreenshot", true);
	    caps.setCapability("version", "6.0");
	    caps.setCapability("deviceName", "emulator-5554");
	    caps.setCapability("app", "/Users/sukumar/Desktop/app.apk");
	    appDriver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), caps);
	    appDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	

	 
}

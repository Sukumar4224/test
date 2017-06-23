package lib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class  Generic {

/*	public static void selectDrop(WebDriver driver,String s,String s2) throws Exception{
		List<WebElement> al=driver.findElements(By.id(s));
		for(WebElement l:al){
			System.out.println(l.getText());
			String value=l.getText();
			if(value.equalsIgnoreCase(s2)){
				Thread.sleep(2000);
				System.out.println("");
				l.click();
				
			}
		}
	}*/
	
	public void selectFromDrop(WebDriver driver,String path,String text) throws Exception{
	
	Select drpState = new Select(driver.findElement(By.id("path")));
	drpState.selectByVisibleText("s2");
	}
}

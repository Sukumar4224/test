package com.voonik.lib;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Generic {
	
	 
	public void  mouseOver(Actions a,WebElement element) throws Exception{
		
		Thread.sleep(5000);
		a.moveToElement(element).perform();
		
	}
	
	

}

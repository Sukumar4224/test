package com.voonik.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.voonik.lib.Generic;

public class HomePage {
	
	Generic g;
	
	public HomePage(WebDriver driver){
		PageFactory.initElements(driver, this);		
	}
	
	
	@FindBy(xpath="(//*[@id='menu_item_3'])[4]")
		private WebElement women_lingerie;
	
	
	
		public void women_lingerie_section(Actions a) throws Exception{
			
		g.mouseOver(a,women_lingerie);
		
		
	}

}

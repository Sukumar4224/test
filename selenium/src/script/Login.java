package script;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import con.pom.LoginPage;
import con.pom.SalesPage;
import lib.Generic;

public class Login extends BaseClass {
	@Test
	public void log() throws Exception{
	LoginPage l=new LoginPage(driver);
	
	l.userNameField("test@valyoo.in");
	l.passField("Lens@123");
	l.login();
	
	SalesPage sales= new SalesPage(driver);
	
	sales.salesman("12");
	Thread.sleep(5000);
	sales.optometrist("127");
	sales.cxNumber("7777777776");
	Thread.sleep(5000);
	
	sales.productSearch("70140");
	Thread.sleep(5000);
	
	
	sales.addCx();
	Generic gc=new Generic();
	gc.selectFromDrop(driver, "region", "Goa");
	
	
/*	List<WebElement> elements = driver.findElements(By.id("region"));
	 for (WebElement all : elements)
     {
        System.out.println(all.getText());

        }
	Select drpState = new Select(driver.findElement(By.id("region")));
	drpState.selectByVisibleText("Goa");*/
/*	sales.addCx();
	Thread.sleep(5000);
	Generic.selectDrop(driver,"region","Goa");
	List<WebElement> al=driver.findElements(By.id("region"));
	for(WebElement i:al){
		System.out.println(i.getText());
	}
*/
	
	}
	
	}





	


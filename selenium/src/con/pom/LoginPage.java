package con.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	@FindBy(id="username")
	private WebElement usernameText;
	
	@FindBy(id="password")
	private WebElement passText;
	
	@FindBy(xpath="//*[@type='submit']")
	private WebElement loginButton;
	
	@FindBy(id="forgotpassword")
	private WebElement forgotpasswordLink;
	
	@FindBy(id="msgs")
	private WebElement passwordmail;
	
	public LoginPage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	public void passField(String pass){
		passText.sendKeys(pass);
	}
	
	public void userNameField(String userName){
		usernameText.sendKeys(userName);
	}
	
	public void login(){
		loginButton.click();
	}
	
	public void forgotpasswordLink(){
		forgotpasswordLink.click();
	}
	
	public void passwordmailsentmsg(){
		
		passwordmail.getText();
	}

}

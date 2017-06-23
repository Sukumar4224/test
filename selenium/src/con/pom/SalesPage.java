package con.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SalesPage {
	@FindBy(id="cmobile")
	private WebElement cxMobile;
	
	public SalesPage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@id='open']/a[1]")
		private WebElement toggleMenu;
	
	public void toggle(){
		toggleMenu.click();
	}
	

	@FindBy(xpath="//*[@id='open']/div[2]/ul/li[1]/a")
		private WebElement offerLink;
	
	public void offerLink(){
		offerLink.click();
	}
	
	
	@FindBy(id="salesmanId")
		private WebElement salesmanId;
	
	public void salesman(String i){
		salesmanId.sendKeys(i);
		
	}

	@FindBy(id="optometristId")
		private WebElement optomId;
	
	public void optometrist(String optoId){
		optomId.sendKeys(optoId);
	}
	
	
	
	public void cxNumber(String cxMob){
		cxMobile.sendKeys(cxMob);
	}
	
	@FindBy(id="addc")
		private WebElement addCx;
	
	public void addCx(){
		addCx.click();
	}
	
	@FindBy(id="firstname")
		private WebElement cxFName;
	
	public void fname(String fname){
		cxFName.sendKeys(fname);
	}
	
	@FindBy(id="lastname")
		private WebElement cxLName;

	public void lname(String lname){
		cxFName.sendKeys(lname);
	}
	
	@FindBy(id="email")
		private WebElement cxMail;

	public void cxMail(String mail){
		cxFName.sendKeys(mail);
	}
		
	@FindBy(id="telephone")
		private WebElement cxNumber;

	public void checkMob(){
		cxNumber.getText();
	}	
	
	@FindBy(id="street")
	private WebElement address;

	public void addAddress(String ads){
		address.sendKeys(ads);
	}
	
	@FindBy(id="country_id")
		private WebElement country;

	public void selectCountry(){
		country.click();
	}
	
	@FindBy(id="city")
	private WebElement city;

	public void enterCity(String cty){
		city.sendKeys(cty);
	}
	
	@FindBy(id="region")
	private WebElement state;

	public void selectState(){
		state.click();
	}
	
	@FindBy(id="postcode")
	private WebElement pincode;

	public void pincode(String pin){
		pincode.sendKeys(pin);
	}
	
	
	@FindBy(id="customer_channel")
	private WebElement fromWhere;

	public void whereInfo(){
		fromWhere.click();
	}
	
	@FindBy(id="strSubmitButton")
	private WebElement addCxDetails;

	public void submitCxDetails(){
		addCxDetails.click();
	}
	
	
	@FindBy(xpath="//*[@id='close']/img")
	private WebElement closeAddCx;

	public void closeNewCxForm(){
		closeAddCx.click();
	}
	
	@FindBy(id="search")
	private WebElement productSearch;

	public void productSearch(String pid){
		productSearch.sendKeys(pid);
	}
	
	
	@FindBy(xpath="//*[@id='li0']")
	private WebElement selectProduct;

	public void selectProduct(){
		selectProduct.click();
	}
	
}


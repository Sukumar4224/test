package Customer_App;

import java.awt.Desktop.Action;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.sikuli.script.FindFailed;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;

import Utility.AppiumBaseClass;
import Utility.ReportListener;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;

public class SelfKYC  extends AppiumBaseClass{
 private String results = "";
 private String runId = "";	
 private String caseId = "214";
 private boolean saveScreenshots = false;
 private Connection connection;
 private static Statement statement;
 private static ResultSet rs;
 Dimension size;

 
 @BeforeTest
 @Parameters({ "run-id-param", "save-screenshots", "DeviceName","databaseURL","user","password" })
 public void setUp(String _runId, String _saveScreenshots,String _DeviceName, String _databaseURL,String _user,String _password) throws MalformedURLException {

  runId = _runId;
	if (_saveScreenshots.compareTo("true") == 0)
		saveScreenshots = true;
	
	 String databaseURL = _databaseURL;
     String user = _user;
     String password = _password;
     connection = null;
     try {
         Class.forName("com.mysql.jdbc.Driver");
         System.out.println("Connecting to Database...");
         connection = DriverManager.getConnection(databaseURL, user, password);
         if (connection != null) {
             System.out.println("Connected to the Database...");
         }
     } catch (SQLException ex) {
        ex.printStackTrace();
        Assert.fail();
     }
     catch (ClassNotFoundException ex) {
        ex.printStackTrace();
        Assert.fail();
     }
     
 }

 @Test(priority=1, dataProvider = "CreateCustomer")
 @Parameters({ "DeviceName"  })
	public void RunTestSelfKYC(String IDtype, String IDNo, String Nationality, String MSISDN, String Name, String Surname, String Gender,String StreetNo, String StreetName, String Province, String City, String Suburb, String PostalCode, String CountryOfBirth ) throws InterruptedException, FindFailed, IOException{
	   CreateCust(IDtype, IDNo, Nationality, MSISDN);
	   EnterOTP(MSISDN);
	   CustInfo (Name, Surname, Gender,CountryOfBirth,IDtype);
	   CustADD (StreetNo,StreetName,Province,City,Suburb,PostalCode);
	   TnC (StreetNo, StreetName, Province, City, Suburb,PostalCode);
	   ConfirmCreation(MSISDN);

		}
 
 @DataProvider(name = "CreateCustomer")
 public Object[][] createData() {
		Object[][] retObjArr = null;
		
		try
     {
			File path = new File("");
			FileInputStream file = new FileInputStream(new File(path.getAbsolutePath() + "/Excel/CustomerApp/CustomerApp.xlsx"));

         XSSFWorkbook workbook = new XSSFWorkbook(file);
         XSSFSheet sheet = workbook.getSheetAt(3);

         Iterator<Row> rowIterator = sheet.iterator();
         
         retObjArr = new Object[sheet.getPhysicalNumberOfRows() - 1][14];

         for(int rownum = 0; rownum < sheet.getPhysicalNumberOfRows() - 1; rownum++){
       	  	Row row = sheet.getRow(rownum + 1);
       	  
             retObjArr[rownum][0] = row.getCell(0).getStringCellValue();
             retObjArr[rownum][1] = row.getCell(1).getStringCellValue();
             retObjArr[rownum][2] = row.getCell(2).getStringCellValue();
             retObjArr[rownum][3] = row.getCell(3).getStringCellValue();
             retObjArr[rownum][4] = row.getCell(4).getStringCellValue();
             retObjArr[rownum][5] = row.getCell(5).getStringCellValue();
             retObjArr[rownum][6] = row.getCell(6).getStringCellValue();
             retObjArr[rownum][7] = row.getCell(7).getStringCellValue();
             retObjArr[rownum][8] = row.getCell(8).getStringCellValue();
             retObjArr[rownum][9] = row.getCell(9).getStringCellValue();
             retObjArr[rownum][10] = row.getCell(10).getStringCellValue();
             retObjArr[rownum][11] = row.getCell(11).getStringCellValue();
             retObjArr[rownum][12] = row.getCell(12).getStringCellValue();
             retObjArr[rownum][13] = row.getCell(13).getStringCellValue();
             

         }
         file.close();
     } 
     catch (Exception e) 
     {
         e.printStackTrace();
     }
     
     return(retObjArr);
	}
 
 
 
 public void CreateCust (String IDtype, String IDNo, String Nationality, String MSISDN) throws InterruptedException  {
	 
	 Thread.sleep(6000);
	 
	 System.out.println("Creating a customer");
	 int ScreenHeight = driver.manage().window().getSize().getHeight();
	 System.out.println(ScreenHeight);
	 
	 DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
     Date dateobj = new Date();

     String DateTime = ((df.format(dateobj)));
     
     Reporter.log("TEST STARTED AT: " +DateTime);
     
     
     Boolean isPresent = driver.findElements(By.xpath("//android.widget.Button[contains(@resource-id,'btn_next')]")).size() > 0;
	 System.out.println(isPresent);

	 if (isPresent == true) {

	     //Going through the teaser
		 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btn_next')]")).click();
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btn_next')]")).click();
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btn_next')]")).click();
		 Thread.sleep(2000);

   	  } else {
        
   		 //Login Screen
 		 driver.findElement(By.xpath("//android.widget.ImageButton[@index='0']")).click();
 		 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btn_next')]")).click();
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btn_next')]")).click();
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btn_next')]")).click();
		 Thread.sleep(2000);
		 
		 Reporter.log("Already Registered with the Self KYC");

   	  }
	 
     

     
	//Selecting sign up
	 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btnsignup')]")).click();
	 Thread.sleep(2000);
	 
	 driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'row_country_name')]")).click();
	 
	 if (IDtype.equalsIgnoreCase("RSA ID Lite")) {
		
		 //Step 1 Verify Customer
		 
		driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'row_country_name') and @text='South African Id']")).click();
		driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'txtRSAID')]")).click();
		driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'txtRSAID')]")).sendKeys(IDNo);
		 try {
	         driver.hideKeyboard();
	         } catch (Exception e) {
	         }
		 
		driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'txtCustomerMsisdn')]")).click();
		driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'txtCustomerMsisdn')]")).sendKeys(MSISDN);
		
	    
		
		try {
	         driver.hideKeyboard();
	         } catch (Exception e) {
	         }
		
		driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btncheckcustomer')]")).click();
		
		Reporter.log("Creating a new customer for RSA ID Lite");
		
		caseId = "10181";
		
   	  } else if (IDtype.equalsIgnoreCase("Asylum Lite")) {
        
   		//Step 1 Verify Customer
   		driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'row_country_name') and @text='Asylum']")).click();
   		driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'txtAsylumNumber')]")).click();
		driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'txtAsylumNumber')]")).sendKeys(IDNo);
		 try {
	         driver.hideKeyboard();
	         } catch (Exception e) {
	         }
		driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'txt') and @text='Select your nationality']")).click();
	
		//Scroll to wont work here
		driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'txt') and @text='"+ Nationality +"' ]")).click();

		driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'txtCustomerMsisdn')]")).click();
		driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'txtCustomerMsisdn')]")).sendKeys(MSISDN);

		try {
	         driver.hideKeyboard();
	         } catch (Exception e) {
	      }
		
		driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btncheckcustomer')]")).click();

		System.out.println("Asylum");
		Reporter.log("Creating new customer for Asylum Lite");
		
		caseId = "10183";
		
		
       } else if (IDtype.equalsIgnoreCase("Other Id")) {
        
   		//Step 1 Verify Customer
   		driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'row_country_name') and @text='Other Id']")).click();
   		driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'txtOtherIdType')]")).click();
		driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'txtOtherIdType')]")).sendKeys(IDNo);
		 try {
	         driver.hideKeyboard();
	         } catch (Exception e) {
	         }
		driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'txt') and @text='Select your nationality']")).click();
	
		//Scroll to wont work here
		driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'txt') and @text='"+ Nationality +"' ]")).click();

		driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'txtCustomerMsisdn')]")).click();
		driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'txtCustomerMsisdn')]")).sendKeys(MSISDN);

		try {
	         driver.hideKeyboard();
	         } catch (Exception e) {
	      }
		
		driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btncheckcustomer')]")).click();

		System.out.println("Asylum");
		Reporter.log("Creating new customer for Other Id");
		
		caseId = "10180";
	 
 } else if (IDtype.equalsIgnoreCase("Passport Lite")) {
     
		//Step 1 Verify Customer
		driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'row_country_name') and @text='Passport']")).click();
		driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'txtPassportNo')]")).click();
		driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'txtPassportNo')]")).sendKeys(IDNo);
		 try {
	         driver.hideKeyboard();
	         } catch (Exception e) {
	         }
		
		 //No Permit number on Passport lite
//		driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'txtPermitNo')]")).click();
//		driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'txtPermitNo')]")).sendKeys(IDNo);
	
		 try {
	         driver.hideKeyboard();
	         } catch (Exception e) {
	         }
		 
		//Insert Nationality
		driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'txt') and @text='Select your nationality']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'txt') and @text='"+ Nationality +"' ]")).click();
		
		//Scroll to Bottom of Screen
		  size = driver.manage().window().getSize();
		  int starty = (int) (size.height * 0.80);
		  int endy = (int) (size.height * 0.20);
		  int startx = size.width / 2;

		  try {
			     driver.swipe(startx, starty, startx, endy, 3000);
				 Thread.sleep(2000);
			     
		         } catch (Exception e)  {
		             e.printStackTrace(); 
		         }

		driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'txtCustomerMsisdn')]")).click();
		driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'txtCustomerMsisdn')]")).sendKeys(MSISDN);

		  try {
	           driver.hideKeyboard();
	           } catch (Exception e) {
	        }
		
		driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btncheckcustomer')]")).click();

		System.out.println("Asylum");
		Reporter.log("Creating new customer for Asylum Lite");
		
		caseId = "10182";

	  }


 }
 
 public void EnterOTP(String MSISDN)throws InterruptedException, FindFailed{
	  System.out.println("Entering OTP add customer");
	  
	  Thread.sleep(15000);
	  
	  try {
         String query = "SELECT OTP FROM HelloFin.HelloCustomerOTP where msisdn = '"+ MSISDN +"';";
         statement = connection.createStatement();
         rs = statement.executeQuery(query);
         
         if (!rs.isBeforeFirst()) {
     	    System.out.println("No Data Returned");
     	    Reporter.log("No data found on the "+ query +" ");
     	    Assert.fail();
     	  }
       
         
         results = "Method: " + new Object(){}.getClass().getEnclosingMethod().getName();

         while(rs.next()){
       	  String Otp = rs.getString("Otp");
       	  System.out.println(Otp);
       	  driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'txtCustomerVerificationCode')]")).click();
       	  driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'txtCustomerVerificationCode')]")).sendKeys(Otp);  

  		try {
  	         driver.hideKeyboard();
  	         } catch (Exception e) {
  	      }
  		
       	  driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btnverifycode')]")).click();
       	  
       	  
       	String ActualMessage = driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'lblMandatory')]")).getText();
   	    System.out.println(ActualMessage);
   	    Assert.assertTrue(ActualMessage.equalsIgnoreCase("Customer Information( * ) Mandatory fields"), "Did not get to step 2");
   	    Reporter.log("Step 2 Profile" + ActualMessage+ "");
             
         }
     } catch (SQLException ex) {
        ex.printStackTrace();
        Assert.fail();
     }
 }
 
 
 
public void CustInfo (String Name, String Surname, String Gender,String CountryOfBirth,String IDtype) throws InterruptedException  {
	 
	 System.out.println("Entering Customer Details");
	 
	 
	 driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'txtName')]")).click();
	 driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'txtName')]")).sendKeys(Name+"\n");
	 try {
         driver.hideKeyboard();
         } catch (Exception e) {
         }
	 driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'txtSurname')]")).click();
	 driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'txtSurname')]")).sendKeys(Surname);

	 try {
         driver.hideKeyboard();
         } catch (Exception e) {
         }

	 
	 if (Gender.equalsIgnoreCase("Male")) {
		
		 //Gender Select
		driver.findElement(By.xpath("//android.widget.RadioButton[@index='0']")).click();
		System.out.println("Male");

		 try {
	         driver.hideKeyboard();
	         } catch (Exception e) {
	         }

		
   	  } else if (Gender.equalsIgnoreCase("Female")) {
        
   		//Gender Select
		driver.findElement(By.xpath("//android.widget.RadioButton[@index='1']")).click();
		System.out.println("Female");

   	  }
	 
	 
	 if (IDtype.equalsIgnoreCase("RSA ID Lite")) {
		
	 Reporter.log("Date of Birth RSA ID Auto Complete");
		
   	  } else {
	 
	 //DOB
	 driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'btndob')]")).click();
	 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button1') and @text='SELECT']")).click();
	 
     }
	 
	 //Country of Birth
	 driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'txt') and @text='Select your country of birth' ]")).click();
	 driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'txt') and @text='"+ CountryOfBirth +"' ]")).click();
	 
	 //Take Photo
	 
	 //Used to Scroll to Next//
	  size = driver.manage().window().getSize();
	  int starty = (int) (size.height * 0.80);
	  int endy = (int) (size.height * 0.20);
	  int startx = size.width / 2;

	  try {
		     driver.swipe(startx, starty, startx, endy, 3000);
			 Thread.sleep(2000);
			 driver.swipe(startx, starty, startx, endy, 3000);
			 Thread.sleep(2000);
	         } catch (Exception e)  {
	             e.printStackTrace(); 
	         }
	 
	 driver.findElement(By.xpath("//android.widget.ImageButton[contains(@resource-id,'btnFirstIdPhoto') ]")).click();
	 
	 //Checks to see if element is present 
	 Boolean isPresent = driver.findElements(By.xpath("//android.widget.ImageView[contains(@resource-id,'btnCapturePhoto')]")).size() > 0;
	 System.out.println(isPresent);

	 if (isPresent == true) {

		 driver.findElement(By.xpath("//android.widget.ImageView[contains(@resource-id,'btnCapturePhoto')]")).click();
   		 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btnSave')]")).click();

   	  } else {
        
   		 //Other Device S7
 		 driver.findElement(By.xpath("//GLButton[contains(@text,'Shutter')]")).click();
 		 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btnSave')]")).click();

   	  }
	 
     driver.findElement(By.xpath("//android.widget.ImageButton[contains(@resource-id,'btnAddressPhotograph') ]")).click();
	 
	 //Checks to see if element is present 
     Boolean isPresent2 = driver.findElements(By.xpath("//android.widget.ImageView[contains(@resource-id,'btnCapturePhoto')]")).size() > 0;
	 System.out.println(isPresent2);

	 if (isPresent2 == true) {

		 driver.findElement(By.xpath("//android.widget.ImageView[contains(@resource-id,'btnCapturePhoto')]")).click();
   		 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btnSave')]")).click();

   	  } else {
        
   		 //Other Device S7
 		 driver.findElement(By.xpath("//GLButton[contains(@text,'Shutter')]")).click();
 		 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btnSave')]")).click();

   	  }


     driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btnnext')]")).click();
		  
	 Thread.sleep(2000);
	 
	 String ActualMessage = driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'mandatoryID')]")).getText();
	 System.out.println(ActualMessage);
	 Assert.assertTrue(ActualMessage.equalsIgnoreCase("Address details( * ) Mandatory fields"), "Did not get to step 3");
	 Reporter.log("Step 3  Cust Info" + ActualMessage+ "");
	 results = "Method: " + new Object(){}.getClass().getEnclosingMethod().getName();
	 
 }

 public void CustADD (String StreetNo, String StreetName, String Province, String City, String Suburb, String PostalCode) throws InterruptedException  {
	 
	 //Street Details
	 System.out.println("Entering Customer Details"); 
	 driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'txtStreetNo')]")).click();
	 driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'txtStreetNo')]")).sendKeys(StreetNo+"\n");
	 try {
         driver.hideKeyboard();
         } catch (Exception e) {
         }
	 driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'txtStreetName')]")).click();
	 driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'txtStreetName')]")).sendKeys(StreetName+"\n");
	 try {
         driver.hideKeyboard();
         } catch (Exception e) {
         }
	 driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'row_country_name')]")).click();
	 
	 //Select Province
	 driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'row_country_name') and @text='"+ Province +"']")).click();
	 
	 
	 //City
	 driver.findElement(By.xpath("//android.widget.Spinner[contains(@resource-id,'spinnerCity')]")).click();
	 driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'row_country_name') and @text='"+ City +"']")).click();
	 

	 
	//Used to Scroll to Next//
	  size = driver.manage().window().getSize();
	  int starty = (int) (size.height * 0.80);
	  int endy = (int) (size.height * 0.20);
	  int startx = size.width / 2;
	  try {
		     driver.swipe(startx, starty, startx, endy, 3000);
			 Thread.sleep(2000);
		     
	         } catch (Exception e)  {
	             e.printStackTrace(); 
	         }
	  
	//Suburb
	  
	 Boolean SuburbSelect = driver.findElements(By.xpath("//android.widget.EditText[contains(@resource-id,'autotxtSuburb')]")).size() > 0;
	 System.out.println(SuburbSelect);
	 
	 if (SuburbSelect == false) {
			
		 //Other Device S7

		 driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'autotxtSuburb')]")).click();
		 driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'autotxtSuburb')]")).sendKeys(Suburb+"\n");


   	  } else {
        
   	    driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'autotxtSuburb')]")).click();
   	    driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'autotxtSuburb')]")).sendKeys(Suburb+"\n");

   	  }
	 
	 
	 try {
         driver.hideKeyboard();
         } catch (Exception e) {
         }
	 driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'txtPostalCode')]")).click();
	 driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'txtPostalCode')]")).sendKeys(PostalCode);
	 try {
         driver.hideKeyboard();
         } catch (Exception e) {
         } 

	 //Going to next
	 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btnnext')]")).click();
	 
	 String ActualMessage = driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'mandatoryID')]")).getText();
	 System.out.println(ActualMessage);
	 Assert.assertTrue(ActualMessage.equalsIgnoreCase("Terms and Conditions"), "Did not get to step 4");
	 Reporter.log("Step 4 " + ActualMessage+ "");
	 results = "Method: " + new Object(){}.getClass().getEnclosingMethod().getName();
	 
}

   public void TnC (String StreetNo, String StreetName, String Province, String City, String Suburb, String PostalCode) throws InterruptedException  {

	 System.out.println("Accepting Terms and Conditions");
	 
	  //Used to Scroll to Next//
	  size = driver.manage().window().getSize();
	  int starty = (int) (size.height * 0.80);
	  int endy = (int) (size.height * 0.20);
	  int startx = size.width / 2;

	  try {
		     driver.swipe(startx, starty, startx, endy, 3000);
			 Thread.sleep(2000);
			 driver.swipe(startx, starty, startx, endy, 3000);
			 Thread.sleep(2000);
			 driver.swipe(startx, starty, startx, endy, 3000);
			 Thread.sleep(2000);
			 driver.swipe(startx, starty, startx, endy, 3000);
			 Thread.sleep(2000);
			 driver.swipe(startx, starty, startx, endy, 3000);
			 Thread.sleep(2000);
			 driver.swipe(startx, starty, startx, endy, 3000);
			 Thread.sleep(2000);
		     
	         } catch (Exception e)  {
	             e.printStackTrace(); 
	         }

	     Boolean isPresent = driver.findElements(By.xpath("//android.widget.RadioButton[contains(@resource-id,'radioYes')]")).size() > 0;
		 System.out.println(isPresent);

		 if (isPresent == true) {
				
			 driver.findElement(By.xpath("//android.widget.RadioButton[contains(@resource-id,'radioYes')]")).click();

	   	  } else {
	        
	   		System.out.println("Scrolling to Next");  
	   		driver.swipe(startx, starty, startx, endy, 3000);
			Thread.sleep(2000);
	   		//driver.scrollTo("YES");
	   		driver.findElement(By.xpath("//android.widget.RadioButton[contains(@resource-id,'radioYes')]")).click();

	   	  }
	  
	 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btnnext')]")).click();
	 
	 String ActualMessage = driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'mandatoryID')]")).getText();
	 System.out.println(ActualMessage);
	 Assert.assertTrue(ActualMessage.equalsIgnoreCase("Customer Signature"), "Did not get to step 5");
	 
	 
	 //Drawing Signature
	 driver.findElement(By.xpath("//android.view.View[@index='0']")).click();
	 WebElement l_dragable = driver.findElement(By.xpath("//android.view.View[@index='0']"));
     WebElement l_droppable = driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'mandatoryID')]"));
     Assert.assertEquals(l_droppable.getText(), "Customer Signature");
     TouchAction dragNDrop = new TouchAction((MobileDriver)driver).press(l_dragable).moveTo(l_droppable).release();
     dragNDrop.perform();
     l_droppable = driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'mandatoryID')]"));
     Assert.assertEquals(l_droppable.getText(), "Customer Signature");
     driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btnSave')]")).click();
     driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button1')]")).click();
     
     String ActualMessage2 = driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'message')]")).getText();
	 System.out.println(ActualMessage2);
	 Assert.assertTrue(ActualMessage2.equalsIgnoreCase("Application successfully submitted"), "Did not get to step 5");
	
	 
	 driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'button1')]")).click();
     
     results = "Method: " + new Object(){}.getClass().getEnclosingMethod().getName();
     
	 
}
   
   public void ConfirmCreation(String MSISDN)throws InterruptedException, FindFailed{
		  System.out.println("Confirming Record Creation");
		  
		  Thread.sleep(30000);
		  
		  try {
	         String query = "SELECT count(*) as Count , hc.* FROM HelloFin.HelloCustomer hc where msisdn =  '"+ MSISDN +"' and StatusId = '8' and CreationDate > date_sub(now(), interval 30 minute) order by id desc";
	         statement = connection.createStatement();
	         rs = statement.executeQuery(query);
	         
	         if (!rs.isBeforeFirst()) {
	     	    System.out.println("No Data Returned");
	     	    Reporter.log("No data found on the "+ query +" ");
	     	    Assert.fail();
	     	  }
	      
	         
	         
	         results = "Method: " + new Object(){}.getClass().getEnclosingMethod().getName();

	         while(rs.next()){
	        	 String HGID = rs.getString("hgid");
	        	 int Count = rs.getInt("Count");
	             
	          	  if (Count > 1 ) {
	            	    System.out.println("DUPLICATE TICKETS");
	            	    Reporter.log("Records created " + Count+ " for customer " + HGID + " ");
	            	    Assert.fail();
	            	  } 
	          	  
	          	  if (Count < 1 ) {
	        	    System.out.println("NO DATA INSERTED");
	        	    Reporter.log("Records created " + Count+ " for customer " + HGID + " ");
	        	    Assert.fail();
	        	  } 
	             
	          	Reporter.log("Records created " + Count+ " for customer " + HGID + " ");
	       	
	         DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	         Date dateobj = new Date();

	         String DateTime = ((df.format(dateobj)));
	         
	         Reporter.log("TEST ENDED AT: " +DateTime);
	         
	         }
	     } catch (SQLException ex) {
	        ex.printStackTrace();
	        Reporter.log("Did not confrim creation of data in the DB error: "+ ex +"");
	      //Assert.fail();
	     }
	 }
   

   @AfterMethod
  	@Parameters({ "posttotestRail" })
  	public void tearDown(ITestResult result,String posttotestRail) throws MalformedURLException, IOException {
  		APIClient client;
  		Map data;
  		
  	    DateFormat df = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
       Date dateobj = new Date();
       String TransactionDate = ((df.format(dateobj)));

  		client = new APIClient("https://hellogroup.testrail.com");
  	  	client.setUser("apitest@hellogroup.co.za");
  	  	client.setPassword("formula.1");
  	  	
  	  	data = new HashMap();
  	  	data.put("case_id", caseId);
  	  	
  	  	String parameters = ""; 
  	  	
  	  	int i = 0;
  	  	for(Object o : result.getParameters()){
  	  		parameters += "param" + i + ": " + o.toString() + "\n" ;
  	  		i++;
  	  	}
  	  	
  	  	StringWriter sw = new StringWriter();
  	      PrintWriter pw = new PrintWriter(sw);
  	      Throwable cause = result.getThrowable();
  	      if (null != cause && saveScreenshots) {
  	    	  

  	        	File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
  	            try {
  		             	File file = new File(".");
  		             	String filename = result.getTestClass() + TransactionDate;
  		             	FileUtils.copyFile(scrFile, new File(file.getAbsolutePath()+ "/test-output/Report/imgs/" + filename + ".jpg"));
  		             	ReportListener.screenshots.add(filename);

  	            } catch (IOException e) {
  		        	  	e.printStackTrace();
  		        }
  	    	  
  	          cause.printStackTrace(pw);
  	          String[] lines = sw.getBuffer().toString().split("\\n");
  	          results += "\nReason for failure:\n" + lines[0];
  	      }
  	      
  	  	if (result.getStatus() == ITestResult.FAILURE) {
  	  		System.out.println("Test failed");
  	             
  	  		data.put("status_id", new Integer(5));  
  	  		data.put("comment", "This test failed\n" + parameters + "\n" + results);	
  	  	}     
  	  	else if (result.getStatus() == ITestResult.SUCCESS){
  	  		System.out.println("Test succeeded");                
  	   		
  	  		data.put("status_id", new Integer(1));   		
  			   	data.put("comment", "This test succeeded\n" + parameters + "\n" + results);	
  	  	}
  		if (posttotestRail.equalsIgnoreCase("Yes"))  {
  			System.out.println(results);	
  			System.out.println("Post result to testrail");	
  		try {
  			JSONObject r = (JSONObject) client.sendPost("add_result_for_case/"+ runId +"/" + caseId, data);
  		} catch (APIException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  		}
  	}
	
	@AfterTest
	 public void End() {
	  driver.quit();
	 }

}
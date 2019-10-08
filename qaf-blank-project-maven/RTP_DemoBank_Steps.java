// package com.rtp.steps;
import static com.qmetry.qaf.automation.step.CommonStep.*;
import com.qmetry.qaf.automation.ui.*;
import com.qmetry.qaf.automation.ui.webdriver.*;
import org.testng.*;
import com.qmetry.qaf.automation.step.*;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.*;
import org.openqa.selenium.edge.*;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.internal.*;
import org.openqa.selenium.io.*;
import org.openqa.selenium.mobile.*;
import org.openqa.selenium.os.*;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.events.*;
import org.openqa.selenium.support.pagefactory.*;
import org.openqa.selenium.support.ui.*;
import java.util.*;
import java.lang.*;
/*import java.io.*;
import org.testng.Assert;
import org.testng.*;*/

public class RTP_DemoBank_Steps extends WebDriverTestCase 
	{
	    
   @QAFTestStep(description="User launch the DemoBank Online Banking application") 
 public void STEPDEF_214(){ 
   get("http://demo.rapidtestpro.com/login.php");
 		getDriver().manage().window().maximize();
 		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  } 
   @QAFTestStep(description="Enter the Password {PARAM0}") 
 public void STEPDEF_217(String PARAM_0){ 
   sendKeys(PARAM_0, "login.password.text");
  } 
   @QAFTestStep(description="Click on Login now button") 
 public void STEPDEF_218(){ 
  } 
   @QAFTestStep(description="Enter the Customer {PARAM0}") 
 public void STEPDEF_219(String PARAM_0){ 
   sendKeys(PARAM_0,"login.pin.text");
  } 
   @QAFTestStep(description="Click on Validate PIN button") 
 public void STEPDEF_220(){ 
  click("login.pin.button");
  } 
   @QAFTestStep(description="Enter the Account No# {PARAM0}") 
 public void STEPDEF_225(String PARAM_0){ 
   sendKeys(PARAM_0, "login.accoutno.text");
  } 
   @QAFTestStep(description="Click on Fund Transfer option in the Account details Tab") 
 public void STEPDEF_286(){ 
   click("funds.transfer");
  } 
   @QAFTestStep(description="Enter the Recevier Bank Name {PARAM0}") 
 public void STEPDEF_288(String PARAM_0){ 
   sendKeys(PARAM_0, "funds.receviersbank.name");
  } 
   @QAFTestStep(description="Enter the Recevier Name {PARAM0}") 
 public void STEPDEF_289(String PARAM_0){ 
   sendKeys(PARAM_0, "funds.receviers.name");
  } 
   @QAFTestStep(description="Enter the Recevier Account Number {PARAM0}") 
 public void STEPDEF_290(String PARAM_0){ 
   sendKeys(PARAM_0, "funds.receviers.no");
  } 
   @QAFTestStep(description="Enter the SWIFT/ABA Routing Number {PARAM0}") 
 public void STEPDEF_291(String PARAM_0){ 
   sendKeys(PARAM_0, "funds.swift.no");
  } 
   @QAFTestStep(description="Enter the Sender Account Number {PARAM0}") 
 public void STEPDEF_292(String PARAM_0){ 
   sendKeys(PARAM_0, "funds.sender.no");
  } 
   @QAFTestStep(description="Enter the Amount to Transfer USD {PARAM0}") 
 public void STEPDEF_293(String PARAM_0){ 
   sendKeys(PARAM_0, "funds.amt");
  } 
   @QAFTestStep(description="Choose Fund Transfer Option is Domestic Transfer {PARAM0}") 
 public void STEPDEF_294(String PARAM_0){ 
   QAFWebElement element =getDriver().findElement("funds.select"); 		Select s = new Select(element); 		s.selectByVisibleText(PARAM_0);
  } 
   @QAFTestStep(description="Fill the Date of Transfer {PARAM0}") 
 public void STEPDEF_295(String PARAM_0){ 
   sendKeys(PARAM_0, "funds.date");
  } 
   @QAFTestStep(description="User fill the Description for the transaction {PARAM0}") 
 public void STEPDEF_410(String PARAM_0){ 
  sendKeys(PARAM_0, "funds.desc");
  } 
   @QAFTestStep(description="Click on Fund Transfer Submit Button") 
 public void STEPDEF_411(){ 
   getDriver().findElement("funds.submit").submit();
  } 
   @QAFTestStep(description="User enter the OTP code {PARAM0}") 
 public void STEPDEF_412(String PARAM_0){ 
  sendKeys(PARAM_0, "trans.code");
  } 
   @QAFTestStep(description="Click on verificaion submit button") 
 public void STEPDEF_413(){ 
  click("trans.submit");
  } 
   @QAFTestStep(description="Verify the Tab name acc statement {PARAM0}") 
 public void STEPDEF_414(String PARAM_0){ 
  if(getText("accounts.tab1").equals(PARAM_0)) 		{ 			Assert.assertTrue(true); 		} 		else 		{ 			Assert.assertTrue(false); 		}
  } 
   @QAFTestStep(description="Verify the tab name Fund Transfer {PARAM0}") 
 public void STEPDEF_419(String PARAM_0){ 
  if(getText("accounts.tab3").equals(PARAM_0)) 		{ 			Assert.assertTrue(true); 		} 		else 		{ 			Assert.assertTrue(false); 		}
  } 
   @QAFTestStep(description="Verify the tab name account details {PARAM0}") 
 public void STEPDEF_420(String PARAM_0){ 
  if(getText("accounts.tab2").equals(PARAM_0)) 		{ 			Assert.assertTrue(true); 		} 		else 		{ 			Assert.assertTrue(false); 		}
  } 
   @QAFTestStep(description="Admin User launch the DemoBank Online Banking Application") 
 public void STEPDEF_625(){ 
  //out1 = RTP.WS.verifyEqual(findTestObject('//p[text()='User Details List']'),'test',123)
  } 
   @QAFTestStep(description="Click on admin Login now button") 
 public void STEPDEF_628(){ 
   click("adminlogin.button");
  } 
   @QAFTestStep(description="Enter the admin username {PARAM0}") 
 public void STEPDEF_629(String PARAM_0){ 
   sendKeys(PARAM_0, "adminname.input");
  } 
   @QAFTestStep(description="Enter the admin Password {PARAM0}") 
 public void STEPDEF_630(String PARAM_0){ 
  sendKeys(PARAM_0, "adminpassword.input");
  } 
   @QAFTestStep(description="Click on User Details Link") 
 public void STEPDEF_632(){ 
   click("userdetails.link");
  } 
   @QAFTestStep(description="Select customer account no {PARAM0}") 
 public void STEPDEF_633(String PARAM_0){ 
  click("xpath=//a[text()='"+PARAM_0+"']");
  } 
   @QAFTestStep(description="Select the Transaction Type {PARAM0}") 
 public void STEPDEF_634(String PARAM_0){ 
  QAFWebElement element =	getDriver().findElement("transactionType.selectbox"); 		Select s = new Select(element); 		s.selectByValue(PARAM_0);
  } 
   @QAFTestStep(description="Enter an Amount {PARAM0}") 
 public void STEPDEF_635(String PARAM_0){ 
  sendKeys(PARAM_0, "transaction.amt");
  } 
   @QAFTestStep(description="Enter the Transaction Date as per the format {PARAM0}") 
 public void STEPDEF_636(String PARAM_0){ 
   sendKeys(PARAM_0, "transaction.date");
  } 
   @QAFTestStep(description="Enter the Transaction Description {PARAM0}") 
 public void STEPDEF_637(String PARAM_0){ 
   sendKeys(PARAM_0, "transaction.desc");
  } 
   @QAFTestStep(description="Verify admin user title {PARAM0}") 
 public void STEPDEF_1901(String PARAM_0){ 
  if(getDriver().getTitle().equals(PARAM_0)) 		{ 			Assert.assertTrue(true); 		} 		else 		{ 			Assert.assertTrue(false); 		}
  } 
   @QAFTestStep(description="Click the Logout button") 
 public void STEPDEF_2131(){ 
  } 
   @QAFTestStep(description="I enter {PARAM0}") 
 public void STEPDEF_2132(String PARAM_0){ 
  } 
   @QAFTestStep(description="Enter the new step {PARAM0} and {PARAM1}") 
 public void STEPDEF_2133(String PARAM_0,String PARAM_1){ 
  } 
   @QAFTestStep(description="enter the new step {PARAM0}") 
 public void STEPDEF_2134(String PARAM_0){ 
  } 
   @QAFTestStep(description="testing {PARAM0} test {PARAM1}") 
 public void STEPDEF_2322(String PARAM_0,String PARAM_1){ 
  } 
   @QAFTestStep(description="test login with {PARAM0} and {PARAM1} and {PARAM2}") 
 public void STEPDEF_2323(String PARAM_0,String PARAM_1,String PARAM_2){ 
  } 
   @QAFTestStep(description="test dfsdf {PARAM0}") 
 public void STEPDEF_2324(String PARAM_0){ 
   QAFWebElement element =	getDriver().findElement("transactionType.selectbox"); 		Select s = new Select(element); 		s.selectByValue(PARAM_0);
  } 
   @QAFTestStep(description="test {PARAM0}") 
 public void STEPDEF_2328(String PARAM_0){ 
  } 
   @QAFTestStep(description="Start my mobile app:- iOS {PARAM0} Android {PARAM1}") 
 public void STEPDEF_2331(String PARAM_0,String PARAM_1){ 
   QAFWebElement element =	getDriver().findElement("transactionType.selectbox"); 		Select s = new Select(element); 		s.selectByValue(PARAM_0);
  } 
   @QAFTestStep(description="runthrough popups for country {PARAM0} and Binary {PARAM1}") 
 public void STEPDEF_2332(String PARAM_0,String PARAM_1){ 
  } 
   @QAFTestStep(description="Login to GM view:- UserName {PARAM0} Password {PARAM1}") 
 public void STEPDEF_2333(String PARAM_0,String PARAM_1){ 
  } 
   @QAFTestStep(description="runthrough onboarding for view :- {PARAM0}") 
 public void STEPDEF_2335(String PARAM_0){ 
  } 
   @QAFTestStep(description="Dashboard Validation for country {PARAM0} with card product name {PARAM1} and available {PARAM2} balance {PARAM3} credit and ALOP offers {PARAM4} for {PARAM5} customer") 
 public void STEPDEF_2336(String PARAM_0,String PARAM_1,String PARAM_2,String PARAM_3,String PARAM_4,String PARAM_5){ 
  } 
   @QAFTestStep(description="I click on {PARAM0}") 
 public void STEPDEF_2337(String PARAM_0){ 
  } 
   @QAFTestStep(description="Page 40 should be display with total credit limit {PARAM0} and available credit {PARAM1} for country {PARAM2}") 
 public void STEPDEF_2338(String PARAM_0,String PARAM_1,String PARAM_2){ 
  } 
   @QAFTestStep(description="Logout from M63") 
 public void STEPDEF_2339(){ 
  } 
   @QAFTestStep(description="Fluent Wait click on the Xpath :- {PARAM0} for timeout {PARAM1} and polling time {PARAM2}") 
 public void STEPDEF_2340(String PARAM_0,String PARAM_1,String PARAM_2){ 
  } 
   @QAFTestStep(description="Close my mobile app:- iOS {PARAM0} Android {PARAM1}") 
 public void STEPDEF_2341(String PARAM_0,String PARAM_1){ 
   TypeNotPresentException
  } 
   @QAFTestStep(description="uninstall my app:- iOS {PARAM0} Android {PARAM1}") 
 public void STEPDEF_2342(String PARAM_0,String PARAM_1){ 
  } 
   @QAFTestStep(description="click the next button") 
 public void STEPDEF_2344(){ 
  } 
   @QAFTestStep(description="click {PARAM0} and {PARAM1}") 
 public void STEPDEF_2345(String PARAM_0,String PARAM_1){ 
  } 
   @QAFTestStep(description="verify admin user List title {PARAM0}") 
 public void STEPDEF_2348(String PARAM_0){ 
  } 
   @QAFTestStep(description="check down {PARAM0}") 
 public void STEPDEF_2352(String PARAM_0){ 
  } 
   @QAFTestStep(description="Login to the new application {PARAM0} k") 
 public void STEPDEF_2353(String PARAM_0){ 
  } 
   @QAFTestStep(description="enter the login values {PARAM0}") 
 public void STEPDEF_2354(String PARAM_0){ 
   sendKeys(PARAM_0, "adminname.input");
  } 
   @QAFTestStep(description="check the frame") 
 public void STEPDEF_2356(){ 
    a = RTP.WS.verifyEqual(findTestObject('txtUserName'),'Focus',1)
  } 
   @QAFTestStep(description="Enter the login credentials {PARAM0} and <Password>") 
 public void STEPDEF_2357(String PARAM_0){ 
  } 
   @QAFTestStep(description="tesyt") 
 public void STEPDEF_2360(){ 
  } 
 
}